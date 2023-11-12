package com.fast.remoting.transport.socket;


import com.fast.config.CustomShutdownHook;
import com.fast.config.RpcServiceConfig;
import com.fast.factory.SingletonFactory;
import com.fast.provider.ServiceProvider;
import com.fast.provider.impl.ZkServiceProviderImpl;
import com.fast.remoting.constants.RpcConstants;
import com.fast.remoting.dto.RpcRequest;
import com.fast.remoting.dto.RpcResponse;
import com.fast.remoting.handler.RpcRequestHandler;
import com.fast.utils.ThreadPoolFactoryUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;


@Slf4j
public class SocketRpcServer {

    private final ExecutorService threadPool;
    private final ServiceProvider serviceProvider;
    private final RpcRequestHandler rpcRequestHandler;


    public SocketRpcServer() {
        rpcRequestHandler = SingletonFactory.getInstance(RpcRequestHandler.class);
        threadPool = ThreadPoolFactoryUtil.createCustomThreadPoolIfAbsent("socket-server-rpc-pool");
        serviceProvider = SingletonFactory.getInstance(ZkServiceProviderImpl.class);
    }

    public void registerService(RpcServiceConfig rpcServiceConfig) {
        serviceProvider.publishService(rpcServiceConfig);
    }

    public void start() {
        try (ServerSocket server = new ServerSocket()) {
            String host = InetAddress.getLocalHost().getHostAddress();
            server.bind(new InetSocketAddress(host, RpcConstants.PORT));
            CustomShutdownHook.getCustomShutdownHook().clearAll();
            Socket socket;
            while ((socket = server.accept()) != null) {
                log.info("client connected [{}]", socket.getInetAddress());
                Socket finalSocket = socket;
                threadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        log.info("server handle message from client by thread: [{}]", Thread.currentThread().getName());
                        try (ObjectInputStream objectInputStream = new ObjectInputStream(finalSocket.getInputStream());
                             ObjectOutputStream objectOutputStream = new ObjectOutputStream(finalSocket.getOutputStream())) {
                            RpcRequest rpcRequest = (RpcRequest) objectInputStream.readObject();
                            Object result = rpcRequestHandler.handle(rpcRequest);
                            objectOutputStream.writeObject(RpcResponse.success(result, rpcRequest.getRequestId()));
                            objectOutputStream.flush();
                        } catch (IOException | ClassNotFoundException e) {
                            log.error("occur exception:", e);
                        }
                    }
                });
            }
            threadPool.shutdown();
        } catch (IOException e) {
            log.error("occur IOException:", e);
        }
    }

}
