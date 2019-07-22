package com.joe.register.server;

public class SelfProtectionPolicy {

    private int expectHeartbeatRate = 0;
    private int expectHeartbeatThreshold = 0;
    //默认每分钟有两次心跳
    private final int defaultHeartbeatPerMinute = 2;
    private final float precectionRate = 0.85f;

    private static SelfProtectionPolicy instance;
    private SelfProtectionPolicy(){}
    public static SelfProtectionPolicy getInstance(){
        if(instance == null){
            synchronized(SelfProtectionPolicy.class){
                if(instance==null)
                    instance = new SelfProtectionPolicy();
            }
        }
        return instance;
    }

    public int getExpectHeartbeatRate() {
        return expectHeartbeatRate;
    }

    public int getExpectHeartbeatThreshold() {
        return expectHeartbeatThreshold;
    }

    /**
     * 增加节点的时候，更新期望心跳次数
     */
    public boolean incrementInstance(){
        return incrementInstance(defaultHeartbeatPerMinute);
    }
    public synchronized boolean incrementInstance(int heartbeatPerMinute){
        expectHeartbeatRate += heartbeatPerMinute;
        expectHeartbeatThreshold = (new Float(Math.floor(expectHeartbeatRate * precectionRate))).intValue();
        return true;
    }

    /**
     * 减少节点的时候，更新期望心跳次数
     */
    public boolean removeInstance(){
        return removeInstance(defaultHeartbeatPerMinute);
    }
    public boolean removeInstance(int heartbeatPerMinute){
        expectHeartbeatRate -= heartbeatPerMinute;
        expectHeartbeatThreshold = (new Float(Math.floor(expectHeartbeatRate * precectionRate))).intValue();
        return true;
    }
}
