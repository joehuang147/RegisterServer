package com.joe.register.server;

/**
 * 实例对象
 * ip、hostName、port、serviceName、instanceId、lease
 * 契约对象，可以判断实例是否存活，刷新心跳时间，获取最后心跳时间
 */
public class ServiceInstance {

    private String ip;
    private String hostName;
    private int port;
    private String serviceName;
    private String instanceId;
    private Lease lease;

    public ServiceInstance(String ip,String hostName,int port
            ,String serviceName,String instanceId){
        this.ip = ip;
        this.hostName = hostName;
        this.port = port;
        this.serviceName = serviceName;
        this.instanceId = instanceId;
        this.lease = new Lease(serviceName,instanceId);
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

    public Long getLeaseTime() {
        return lease.getLastHeartBeatTime();
    }

    public void renew() {
        this.lease.renew();
    }

    public boolean isAlive() {
        return this.lease.isAlive();
    }

    private class Lease{
        private String serviceName;
        private String instanceId;
        //多线程修改最后心跳反馈时间
        private volatile Long lastHeartBeatTime;
        private Long expireInteval = 30 * 1000L;

        public Lease(String serviceName,String instanceId){
            this.serviceName = serviceName;
            this.instanceId = instanceId;
            this.lastHeartBeatTime = System.currentTimeMillis();
        }

        public boolean isAlive(){
            if(System.currentTimeMillis() - lastHeartBeatTime > expireInteval)
                return false;
            else
                return true;
        }

        public void renew() {
            this.lastHeartBeatTime = System.currentTimeMillis();
        }

        public Long getLastHeartBeatTime() {
            return lastHeartBeatTime;
        }
    }
}


