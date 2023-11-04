package com.fast.register;

import com.fast.extension.SPI;

import java.net.InetSocketAddress;


/**
 * 服务注册接口
 */
@SPI
public interface ServiceRegistry {
    /**
     * 注册服务
     *
     * @param rpcServiceName    rpc服务名称
     * @param inetSocketAddress 服务地址
     */
    void registerService(String rpcServiceName, InetSocketAddress inetSocketAddress);
}