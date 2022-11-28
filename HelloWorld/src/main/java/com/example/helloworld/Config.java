package com.example.helloworld;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: PengYin
 * @Date: 2022/11/27/16:31
 * @Description:
 */
@Configuration
public class Config {
    @Bean
    public Queue hello(){
        return new Queue("hello");
    }

    @Bean
    public Receiver receiver(){
        return new Receiver();
    }

    @Bean
    public Sender sender(){
        return new Sender();
    }
}
