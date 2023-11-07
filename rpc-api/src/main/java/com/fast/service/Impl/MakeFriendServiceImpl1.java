package com.fast.service.Impl;


import com.fast.annotation.RpcService;
import com.fast.service.MakeFriendService;

@RpcService
public class MakeFriendServiceImpl1 implements MakeFriendService {

    static {
        System.out.println("开始相亲中。。。。。。");
    }

    @Override
    public String makeFriend(String name) {
        return name + "，我想和你交朋友！";
    }
}
