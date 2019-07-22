package com.joe.register.server;

/**
 * 心跳请求对象
 */
public class HeartbeatRequest {
    //serviceName,instanId

    private String serviceName;
    private String instanceId;

    public String getServiceName() {
        return serviceName;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    @Override
    public String toString() {
        return "HeartbeatRequest{" +
                "serviceName='" + serviceName + '\'' +
                ", instanceId='" + instanceId + '\'' +
                '}';
    }
}
