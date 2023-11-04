package com.fast.remoting.dto;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
public class RpcRequest implements Serializable {
    /**
     * 请求ID
     */
    private String requestId;
    /**
     * 接口名称
     */
    private String interfaceName;
    /**
     * 方法名称
     */
    private String methodName;
    /**
     * 方法参数列表
     */
    private Object[] parameters;
    /**
     * 方法参数类型列表
     */
    private Class<?>[] paramTypes;
    /**
     * RPC版本
     */
    private String version;
    /**
     * RPC分组
     */
    private String group;

    /**
     * 获取RPC服务名称
     *
     * @return RPC服务名称
     */
    public String getRpcServiceName() {
        return this.getInterfaceName() + this.getGroup() + this.getVersion();
    }
}