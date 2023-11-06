package com.fast.register;

import com.fast.register.zk.ZkServiceDiscoveryImpl;
import com.fast.register.zk.ZkServiceRegistryImpl;
import com.fast.remoting.dto.RpcRequest;
import org.junit.jupiter.api.Test;

import java.net.InetSocketAddress;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ZkServiceTest {

    @Test
    public void test() {

        // zk注册
        ServiceRegistry zkServiceRegistry = new ZkServiceRegistryImpl();
        InetSocketAddress givenInetSocketAddress = new InetSocketAddress("127.0.0.1", 8090);
        zkServiceRegistry.registerService("test", givenInetSocketAddress);

        // zk发
        ServiceDiscovery zkServiceDiscovery = new ZkServiceDiscoveryImpl();
        InetSocketAddress acquiredInetSocketAddress = zkServiceDiscovery.lookupService(new RpcRequest("test", "test", "test", new Object[0], new Class[0], "", ""));

        assertEquals(givenInetSocketAddress.toString(), acquiredInetSocketAddress.toString());

    }

}
