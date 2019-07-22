package com.joe.register.server;

/**
 * 注册请求对象
 */
public class RegisterRequest {
    //ip,port,serviceName,instanId,hostName

    private String ip;
    private String hostName;
    private int port;
    private String serviceName;
    private String instanceId;

    public RegisterRequest(String ip,String hostName,int port
            ,String serviceName,String instanceId){
        this.ip = ip;
        this.hostName = hostName;
        this.port = port;
        this.serviceName = serviceName;
        this.instanceId = instanceId;
    }

    public String getIp() {
        return ip;
    }

    public String getHostName() {
        return hostName;
    }

    public int getPort() {
        return port;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    @Override
    public String toString() {
        return "RegisterRequest{" +
                "ip='" + ip + '\'' +
                ", hostName='" + hostName + '\'' +
                ", port=" + port +
                ", serviceName='" + serviceName + '\'' +
                ", instanceId='" + instanceId + '\'' +
                '}';
    }
}
