package com.fast;


import com.fast.proxy.RpcJdkProxy;
import com.fast.remoting.transport.RpcRequestTransport;
import com.fast.remoting.transport.socket.SocketRpcClient;
import com.fast.service.MakeFriendService;


public class SocketClientMain {
    public static void main(String[] args) {
        RpcRequestTransport rpcRequestTransport = new SocketRpcClient();

//        MakeFriendService makeFriendService = new RpcCglibProxy(rpcRequestTransport).getProxy(MakeFriendService.class);

        RpcJdkProxy rpcJdkProxy = new RpcJdkProxy(rpcRequestTransport);
        MakeFriendService makeFriendService = rpcJdkProxy.getProxy(MakeFriendService.class);
        String friend = makeFriendService.makeFriend("SKH🌹");
        System.out.println(friend);
    }
}
