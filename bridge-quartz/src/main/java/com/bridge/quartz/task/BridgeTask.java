package com.bridge.quartz.task;

import org.springframework.stereotype.Component;

/**
 * 定时任务调度测试
 * 
 * @author bridge
 */
@Component("bridgeTask")
public class BridgeTask
{
    public void Params(String params)
    {
        System.out.println("执行有参方法：" + params);
    }

    public void NoParams()
    {
        System.out.println("执行无参方法");
    }
}
