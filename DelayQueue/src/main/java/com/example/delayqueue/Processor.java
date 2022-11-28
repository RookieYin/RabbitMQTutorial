package com.example.delayqueue;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: PengYin
 * @Date: 2022/11/28/14:02
 * @Description: 消息接收者，处理到期任务
 */
public class Processor {
    //监听Config中创建的任务消费队列即可
    @RabbitListener(queues = "#{consumeTaskQueue.name}")
    public void receive(String in) throws InterruptedException {
        System.out.println("[x] Received '" + in + "' at " + new Date().getTime()/1000);
    }
}
