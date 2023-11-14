package com.fast;


import com.fast.config.RpcServiceConfig;
import com.fast.remoting.transport.netty.server.NettyRpcServer;
import com.fast.service.Impl.MakeFriendServiceImpl2;
import com.fast.service.MakeFriendService;

public class NettyServerMain {
    public static void main(String[] args) {
        MakeFriendService friendService = new MakeFriendServiceImpl2();

        NettyRpcServer nettyRpcServer = new NettyRpcServer();
        RpcServiceConfig rpcServiceConfig = new RpcServiceConfig();
        rpcServiceConfig.setService(friendService);
        nettyRpcServer.registerService(rpcServiceConfig);
        nettyRpcServer.start();
    }
}
