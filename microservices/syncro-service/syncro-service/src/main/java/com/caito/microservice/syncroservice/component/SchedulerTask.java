package com.caito.microservice.syncroservice.component;

import com.caito.microservice.syncroservice.config.AsyncRequestConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

/**
 * @author claudio.vilas
 * @version 07/2023
 */

@Configuration
@Service
@Slf4j
@RequiredArgsConstructor
@EnableScheduling
public class SchedulerTask {
    private final AsyncRequestConfiguration asyncRequestConfiguration;

    @Async("syncPoolTaskExecutor")
    @Scheduled(cron = "${cronoSyncRequest}")
    public void cronSynqRequestToApi(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor = this.getThreadPoolExcecutor();
        this.printPoolTaskEsxecutorInfo();

        if (threadPoolTaskExecutor.getActiveCount() == 0 &&
            threadPoolTaskExecutor.getQueueSize() == 0){
            log.info("inicio el servicio obtener lista de pedidos");
        }
        log.info("---> finalizo servicio syncro");
    }



    private ThreadPoolTaskExecutor getThreadPoolExcecutor(){
        return (ThreadPoolTaskExecutor) asyncRequestConfiguration.syncPoolTaskExecutor();
    }

    private void printPoolTaskEsxecutorInfo(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor = getThreadPoolExcecutor();
        log.info("Activo: " + threadPoolTaskExecutor.getActiveCount() + " -cola:" +
                threadPoolTaskExecutor.getQueueSize());
    }
}
