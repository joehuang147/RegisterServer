package com.joe.register.server;

/**
 * 注册返回对象
 */
public class RegisterResponse {
    //status

    private boolean status;

    public RegisterResponse(boolean status){
        this.status = status;
    }

    public boolean getStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
}
