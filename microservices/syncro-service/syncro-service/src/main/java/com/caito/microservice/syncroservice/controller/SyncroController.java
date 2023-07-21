package com.caito.microservice.syncroservice.controller;

import com.caito.microservice.syncroservice.component.SchedulerTask;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author claudio.vilas
 * @version 07/2023
 */

@RestController
@RequestMapping("/api/${application.version}/caito/syncro")
@Slf4j
@Tag(name = "syncro")
@RequiredArgsConstructor
public class SyncroController {
    private final SchedulerTask schedulerTask;

    @PostMapping("scheduler/synctoapi/exec")
    @Operation(description = "ejecuta manualmente el crontab de sincronizr solicitudes",
               summary = "ejecuta manualmente el crontab de sincronizr solicitudes")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok")
    })
    public ResponseEntity<String> syncro(){
        log.info("**** endpoint sincronizacion de solicitudes  ****");
        ResponseEntity response = ResponseEntity.ok().build();
        try {
            schedulerTask.cronSynqRequestToApi();
        }catch (Exception e){
            response = ResponseEntity.badRequest().build();
        }
        return response;
    }
}
