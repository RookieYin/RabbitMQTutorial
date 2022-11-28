package com.example.delayqueue;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: PengYin
 * @Date: 2022/11/28/14:03
 * @Description:
 */
@Configuration
public class Config {
    /**
     * 创建一个direct交换机
     * 为了方便，任务创建交换机和死信交换机使用同一个
     * 用不同的routing_key转发到各自队列
     */
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("delay.direct");
    }

    /**
     * 创建延迟任务队列
     * 这里我们不直接设置队列的过期时间，而是在发送消息的时候，给消息设置过期时间
     * 这种做法灵活性更高
     */
    @Bean
    public Queue createTaskQueue(){
        Map<String, Object> params = new HashMap<>();
        // x-dead-letter-exchange 声明了队列里的死信转发到的DLX名称，
        params.put("x-dead-letter-exchange", "delay.direct");
        // x-dead-letter-routing-key 声明了这些死信在转发时携带的 routing-key 名称。
        params.put("x-dead-letter-routing-key", "consume_task");
        return new Queue("delay_queue", false, false, false, params);
    }

    //创建死信队列
    @Bean
    public Queue consumeTaskQueue(){
        return new AnonymousQueue();
    }

    //创建binding，绑定延迟队列和direct交换机，routing_key为 create_task
    @Bean
    public Binding bindingCreate(DirectExchange directExchange, Queue createTaskQueue){
        return BindingBuilder.bind(createTaskQueue)
                .to(directExchange)
                .with("create_task");
    }
    //创建binding，绑定死信队列和direct交换机，routing_key为 consume_task
    @Bean
    public Binding bindingConsume(DirectExchange directExchange, Queue consumeTaskQueue){
        return BindingBuilder.bind(consumeTaskQueue)
                .to(directExchange)
                .with("consume_task");
    }

    //实例化Sender
    @Bean
    public Sender sender(){
        return new Sender();
    }

    //实例化Processor，处理任务
    @Bean
    public Processor processor(){
        return new Processor();
    }
}
