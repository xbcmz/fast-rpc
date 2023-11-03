package com.fast.serviceImpl;

import com.fast.annotation.RpcService;
import com.fast.rpc.api.MakeFriendService;

@RpcService
public class MakeFriendServiceImpl2 implements MakeFriendService {

    static {
        System.out.println("富婆勾搭中。。。。。。");
    }

    @Override
    public String makeFriend(String name) {
        return "我是" + name + "，我想和你交朋友！";
    }
}
