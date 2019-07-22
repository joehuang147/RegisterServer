package com.joe.register.server;

import java.util.UUID;

/**
 * 程序调用的接口
 * 模拟客户端的调用
 */
public class RegisterServer {
    
    public static void main(String[] args){

        boolean registFlag = false;
        ServiceAliveMonitor serviceAliveMonitor = ServiceAliveMonitor.getInstance();
        serviceAliveMonitor.start();

        String ip = "192.168.31.201";
        int port = 9099;
        String hostName = "storeService1";
        String serviceName = "storeService";
        String instanceId = UUID.randomUUID().toString().replace("-","");


        RegisterRequest registerRequest = new RegisterRequest(ip, hostName, port, serviceName, instanceId);
        RegisterController registerController = new RegisterController();
        System.out.println("要对服务【"+registerRequest+"】进行注册");
        RegisterResponse registerResponse = registerController.register(registerRequest);
        System.out.println("服务【"+registerRequest+"】注册"+ (registerResponse.getStatus()?"成功!":"失败!"));

    }
    
}
