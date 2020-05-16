package com.debug.kill.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executors;


/**
 * ClassName: SchedluerConfig
 * Description: 定时任务多线程处理的通用化配置
 * Author: Leo
 * Date: 2020/5/15-18:00
 * email 1437665365@qq.com
 */
@Configuration
public class SchedluerConfig implements SchedulingConfigurer {
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        //线程池提供10个线程
        scheduledTaskRegistrar.setScheduler(Executors.newScheduledThreadPool(10));
    }
}
