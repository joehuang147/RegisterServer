package com.joe.register.server;

/**
 * 心跳返回对象
 */
public class HeartbeatResponse {

    private boolean status;

    public HeartbeatResponse(boolean status){
        this.status = status;
    }

    public boolean getStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
}
