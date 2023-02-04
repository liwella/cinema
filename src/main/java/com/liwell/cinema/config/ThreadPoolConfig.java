package com.liwell.cinema.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/2/3
 */
@Configuration
public class ThreadPoolConfig {

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(2);
        taskExecutor.setMaxPoolSize(72);
        taskExecutor.setQueueCapacity(20);
        taskExecutor.setKeepAliveSeconds(180);
        taskExecutor.setThreadNamePrefix("cinema_executor");
        taskExecutor.setAllowCoreThreadTimeOut(false);
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        taskExecutor.initialize();
        return taskExecutor;
    }

}
