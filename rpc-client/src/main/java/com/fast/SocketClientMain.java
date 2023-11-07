package com.fast;


import com.fast.config.RpcServiceConfig;
import com.fast.proxy.RpcClientProxy;
import com.fast.remoting.transport.RpcRequestTransport;
import com.fast.remoting.transport.socket.SocketRpcClient;
import com.fast.service.MakeFriendService;


public class SocketClientMain {
    public static void main(String[] args) {
        RpcRequestTransport rpcRequestTransport = new SocketRpcClient();
        RpcServiceConfig rpcServiceConfig = new RpcServiceConfig();
        RpcClientProxy rpcClientProxy = new RpcClientProxy(rpcRequestTransport, rpcServiceConfig);

        MakeFriendService makeFriendService = rpcClientProxy.getProxy(MakeFriendService.class);
        String friend = makeFriendService.makeFriend("石原里美");
        System.out.println(friend);
    }
}
