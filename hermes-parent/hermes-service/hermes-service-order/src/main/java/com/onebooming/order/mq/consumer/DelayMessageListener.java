package com.onebooming.order.mq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Onebooming
 * @version 1.0
 * @date 2020-03-28 22:20
 * @ApiNote 过期消息监听
 */
@Component
@RabbitListener(queues = "orderListenerQueue")
public class DelayMessageListener {
    /**
     * 延时队列监听
     * @param message
     */
    @RabbitHandler
    public void getDelayMessage(String message){
        SimpleDateFormat simpleDateFormat  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowData = simpleDateFormat.format(new Date());
        System.out.println("延时队列监听时间："+nowData);
        System.out.println("监听到的消息：" + message);

        //监听到过期队列中（支付队列里的信息）信息，则进行取消订单，回滚库存的操作
    }
}
