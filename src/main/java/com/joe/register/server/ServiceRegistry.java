package com.joe.register.server;

import java.util.HashMap;
import java.util.Map;

/**
 * 服务注册表
 */
public class ServiceRegistry {

    private static ServiceRegistry registry = new ServiceRegistry();
    private Map<String,Map<String,ServiceInstance>> registerTable;

    private ServiceRegistry(){
        registerTable = new HashMap<String, Map<String, ServiceInstance>>();
    }

    public static ServiceRegistry getInstance(){
        return registry;
    }

    /**
     * 往注册表，增加服务实例
     */
    public synchronized void addService(ServiceInstance serviceInstance){
        Map<String,ServiceInstance> serviceMap ;
        if(registerTable.get(serviceInstance.getServiceName())==null){
            serviceMap = new HashMap<String, ServiceInstance>();
            registerTable.put(serviceInstance.getServiceName(),serviceMap);
        }else{
            serviceMap = registerTable.get(serviceInstance.getServiceName());
        }
        serviceMap.put(serviceInstance.getInstanceId(),serviceInstance);
    }

    /**
     * 删除服务实例
     */
    public synchronized boolean removeService(String serviceName,String instanceId){
        Map<String, ServiceInstance> stringServiceInstanceMap = registerTable.get(serviceName);
        if (stringServiceInstanceMap == null)
            return  false;
        stringServiceInstanceMap.remove(instanceId);
        return true;
    }

    /**
     * 更新心跳时间
     */
    public void renewHearbeat(String serviceName,String instanceId){
        registerTable.get(serviceName).get(instanceId).renew();
    }

    /**
     * 查也需要加同步锁，可以保证获取的注册表是最新的
     * @return
     */
    public synchronized Map<String, Map<String, ServiceInstance>> getRegisterTable() {
        return registerTable;
    }

}
