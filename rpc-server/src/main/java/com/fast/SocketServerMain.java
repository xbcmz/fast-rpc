package com.fast;


import com.fast.config.RpcServiceConfig;
import com.fast.remoting.transport.socket.SocketRpcServer;
import com.fast.service.Impl.MakeFriendServiceImpl1;
import com.fast.service.Impl.MakeFriendServiceImpl2;
import com.fast.service.MakeFriendService;

public class SocketServerMain {
    public static void main(String[] args) {
        MakeFriendService friendService = new MakeFriendServiceImpl2();

        SocketRpcServer socketRpcServer = new SocketRpcServer();
        RpcServiceConfig rpcServiceConfig = new RpcServiceConfig();
        rpcServiceConfig.setService(friendService);
        socketRpcServer.registerService(rpcServiceConfig);
        socketRpcServer.start();
    }
}
