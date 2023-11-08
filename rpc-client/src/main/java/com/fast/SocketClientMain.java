package com.fast;


import com.fast.proxy.RpcJdkProxy;
import com.fast.remoting.transport.RpcRequestTransport;
import com.fast.remoting.transport.socket.SocketRpcClient;
import com.fast.service.MakeFriendService;


public class SocketClientMain {
    public static void main(String[] args) throws Throwable {
        RpcRequestTransport rpcRequestTransport = new SocketRpcClient();
//        RpcServiceConfig rpcServiceConfig = new RpcServiceConfig();

//        RpcClientProxy rpcClientProxy = new RpcClientProxy(rpcRequestTransport, rpcServiceConfig);


        RpcJdkProxy rpcJdkProxy = new RpcJdkProxy(rpcRequestTransport);

        MakeFriendService makeFriendService = rpcJdkProxy.getProxy(MakeFriendService.class);
        String friend = makeFriendService.makeFriend("石原里美");
        System.out.println(friend);

//        MakeFriendService makeFriendService = rpcClientProxy.getProxy(MakeFriendService.class);
//        String friend = makeFriendService.makeFriend("石原里美");
//        System.out.println(friend);
    }
}
