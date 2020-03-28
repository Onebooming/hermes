package com.onebooming.seckill.timer;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Onebooming
 * @version 1.0
 * @date 2020-03-28 23:08
 * @ApiNote
 */
@Component
public class SeckillGoodsPushTask {
    /**
     *
     */
    @Scheduled(cron = "0/30 * * * * ?")
    public void loadGoodsPushRedis(){
        System.out.println("task demo");
    }
}
