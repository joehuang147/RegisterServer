package com.joe.register.server;

import java.util.Map;

/**
 *  启动后台线程，定期将不再存活的服务实例移除
 */
public class ServiceAliveMonitor {

    private static ServiceAliveMonitor serviceMonitor = new ServiceAliveMonitor();
    //  判断过期的依据交回给Lease，相应的对象
    //  private static final Long expireHearBeatTime = 20 * 1000L;
    private static final Long monitorInterval = 10* 1000L;
    private static final boolean isDaemon = false;

    private ServiceAliveMonitor(){
        daemon.setDaemon(isDaemon);
    }

    public static ServiceAliveMonitor getInstance(){
        return serviceMonitor;
    }

    private Daemon daemon =  new Daemon();

    public void start(){
        daemon.start();
    }


    private class Daemon extends Thread{
        @Override
        public void run() {
            while(true){
                try{
                    System.out.println("开始进行心跳扫描");
                    Map<String, Map<String, ServiceInstance>> registerTable = ServiceRegistry.getInstance().getRegisterTable();
                    for(String serviceName:registerTable.keySet()){
                        Map<String, ServiceInstance> serviceInstanceMap = registerTable.get(serviceName);
                        for (String instanceId:serviceInstanceMap.keySet()){
                            System.out.println("\t开始扫描"+serviceName+"服务组的"+instanceId+"服务");
                            if(!serviceInstanceMap.get(instanceId).isAlive()) {
                                System.out.println("\t移除"+serviceName+"服务组的"+instanceId+"服务");
                                ServiceRegistry.getInstance().removeService( serviceName,instanceId);
                                //更新心跳期望次数
                                SelfProtectionPolicy.getInstance().removeInstance();
                            }
                        }
                    }
                    sleep(monitorInterval);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
