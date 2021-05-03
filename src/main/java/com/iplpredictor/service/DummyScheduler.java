package com.iplpredictor.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Slf4j
public class DummyScheduler {

    @Scheduled(fixedRate = 240000)
    public void fixedRateSch() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        Date now = new Date();
        String strDate = sdf.format(now);
        log.info("Fixed Rate scheduler:: " + strDate);
        System.out.println("Fixed Rate scheduler:: " + strDate);

    }
}
