package com.example.delayqueue;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: PengYin
 * @Date: 2022/11/28/14:03
 * @Description:定时任务创建者
 */
public class Sender {
    @Autowired
    private RabbitTemplate template;

    @Autowired
    private DirectExchange direct;

    AtomicInteger count = new AtomicInteger(0);

    //定时发送三种不同routing key的消息
    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send() {
        StringBuilder builder = new StringBuilder("Task No.");
        builder.append(this.count.incrementAndGet());
        builder.append(" created at " + new Date().getTime()/1000);
        String content = builder.toString();
        template.convertAndSend(direct.getName(), "create_task", content, message -> {
            MessageProperties messageProperties = message.getMessageProperties();
            // 设置这条消息的过期时间10s
            messageProperties.setExpiration("10000");
            return message;
        });
        System.out.println(" [x] Sent '" + content + "'");
    }
}
