package com.caito.microservice.syncroservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author claudio.vilas
 * @version 07/2023
 */

@Configuration
@EnableAsync
public class AsyncRequestConfiguration {

    @Bean("syncPoolTaskExecutor")
    public Executor syncPoolTaskExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        executor.setCorePoolSize(1);
        executor.setMaxPoolSize(executor.getCorePoolSize());
        executor.setQueueCapacity(2);
        executor.setThreadNamePrefix("scheduleRequest-");
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.initialize();
        return executor;
    }
}
