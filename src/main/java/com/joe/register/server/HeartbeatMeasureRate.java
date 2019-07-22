package com.joe.register.server;

/**
 * 计算最近一分钟内客户端心跳的次数
 */
public class HeartbeatMeasureRate {

    private int heartbeatTimes = 0;
    private long lastHeatbetTime = 0L;
    private static HeartbeatMeasureRate instance ;

    private HeartbeatMeasureRate(){
    }

    public static HeartbeatMeasureRate getInstance(){
        if(instance == null){
            synchronized(HeartbeatMeasureRate.class){
                if(instance == null)
                    instance = new HeartbeatMeasureRate();
            }
        }
        return instance;
    }

    /**
     * 一次只能有一个叠加，保证heartbeatTimes的线程安全
     */
    public synchronized void increament(){
        long current = System.currentTimeMillis();
        if(current-lastHeatbetTime>1000*60){
            lastHeatbetTime = current;
            heartbeatTimes = 0;
        }
        heartbeatTimes ++ ;
    }

    public int get() {
        return heartbeatTimes;
    }
}
