package com.fast.proxy;

import com.fast.remoting.dto.RpcRequest;
import com.fast.remoting.dto.RpcResponse;
import com.fast.remoting.transport.RpcRequestTransport;
import com.fast.remoting.transport.socket.SocketRpcClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

import java.util.UUID;

@Slf4j
public class RpcCglibProxy {

    private final RpcRequestTransport rpcRequestTransport;

    public RpcCglibProxy(RpcRequestTransport rpcRequestTransport) {
        this.rpcRequestTransport = rpcRequestTransport;
    }

    public <T> T getProxy(Class<T> clazz) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> {

            log.info("Cglib invoked method: [{}]", method.getName());
            RpcRequest rpcRequest = RpcRequest.builder().methodName(method.getName())
                    .parameters(objects)
                    .interfaceName(method.getDeclaringClass().getName())
                    .paramTypes(method.getParameterTypes())
                    .requestId(UUID.randomUUID().toString())
                    .group("")
                    .version("")
                    .build();
            RpcResponse<Object> rpcResponse = null;

            if (rpcRequestTransport instanceof SocketRpcClient) {
                rpcResponse = (RpcResponse<Object>) rpcRequestTransport.sendRpcRequest(rpcRequest);
            }
            return rpcResponse.getData();
        });
        return (T) enhancer.create();
    }
}
