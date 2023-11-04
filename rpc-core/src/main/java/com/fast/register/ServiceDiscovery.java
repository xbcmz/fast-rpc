package com.fast.register;

import com.fast.extension.SPI;
import com.fast.remoting.dto.RpcRequest;

import java.net.InetSocketAddress;

/**
 * 服务发现接口
 */
@SPI
public interface ServiceDiscovery {
    /**
     * 根据rpcServiceName查找服务
     *
     * @param rpcRequest rpc服务对象
     * @return 服务地址
     */
    InetSocketAddress lookupService(RpcRequest rpcRequest);
}