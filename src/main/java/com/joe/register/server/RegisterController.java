package com.joe.register.server;

import java.util.Map;

/**
 * 接收客户端的注册、心跳、获取注册表的请求
 */
public class RegisterController {

    public RegisterResponse register(RegisterRequest request){
        ServiceInstance serviceInstance = new ServiceInstance(request.getIp(), request.getHostName(), request.getPort()
                , request.getServiceName(), request.getInstanceId());
        try{
            ServiceRegistry.getInstance().addService(serviceInstance);
            //更新心跳期望值
            SelfProtectionPolicy.getInstance().incrementInstance();
            return new RegisterResponse(true);
        }catch(Exception e){
            return new RegisterResponse(false);
        }
    }

    public HeartbeatResponse heartbeat(HeartbeatRequest request){
        try{
            ServiceRegistry.getInstance().renewHearbeat(request.getServiceName(),request.getInstanceId());
            HeartbeatMeasureRate.getInstance().increament();
            return new HeartbeatResponse(true);
        }catch(Exception e){
            return new HeartbeatResponse(false);
        }
    }

    public Map<String, Map<String,ServiceInstance>> fetchRegistry(){
        return ServiceRegistry.getInstance().getRegisterTable();
    }


    /**
     * 接到客户端的下线请求
     */
    public boolean cancel(String serviceName,String instanceId){
        return ServiceRegistry.getInstance().removeService(serviceName,instanceId);
    }


}
