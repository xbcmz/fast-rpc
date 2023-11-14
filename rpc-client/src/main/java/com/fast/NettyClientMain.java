package com.fast;


import com.fast.proxy.RpcJdkProxy;
import com.fast.remoting.transport.RpcRequestTransport;
import com.fast.remoting.transport.netty.client.NettyRpcClient;
import com.fast.service.MakeFriendService;


public class NettyClientMain {
    public static void main(String[] args) {

        RpcRequestTransport rpcRequestTransport= new NettyRpcClient();
        RpcJdkProxy rpcJdkProxy = new RpcJdkProxy(rpcRequestTransport);
        MakeFriendService makeFriendService = rpcJdkProxy.getProxy(MakeFriendService.class);
        String friend = makeFriendService.makeFriend("小甜");
        System.out.println(friend);
    }
}
