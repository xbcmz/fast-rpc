package com.fast.register;

import com.fast.register.zk.ZkServiceDiscoveryImpl;
import com.fast.register.zk.ZkServiceRegistryImpl;
import com.fast.remoting.dto.RpcRequest;
import org.junit.jupiter.api.Test;

import java.net.InetSocketAddress;

public class ZkServiceTest {

    @Test
    public void test() {

        // zk注册
        ServiceRegistry zkServiceRegistry = new ZkServiceRegistryImpl();
        zkServiceRegistry.registerService("test", new InetSocketAddress("127.0.0.1", 8080));

        // zk发
        ServiceDiscovery zkServiceDiscovery = new ZkServiceDiscoveryImpl();
        InetSocketAddress inetSocketAddress = zkServiceDiscovery.lookupService(new RpcRequest("test", "test", "test", new Object[0], new Class[0], "1.0.0", "test"));

    }

}
