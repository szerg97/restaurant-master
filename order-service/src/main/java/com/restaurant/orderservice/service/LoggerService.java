package com.restaurant.orderservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.LocalDateTime;


@Service
@Slf4j
@ConditionalOnProperty("logging.enabled")
public class LoggerService {

    //@Scheduled(fixedRate = 2000)
    public void logCurrentTime(){
        String format = MessageFormat.format("The current time is {0}", LocalDateTime.now());
        log.info(format);
    }
}
