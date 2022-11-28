package com.example.routing;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: PengYin
 * @Date: 2022/11/27/23:01
 * @Description:
 */
@Configuration
public class Config {
    @Bean
    public DirectExchange direct() {
        return new DirectExchange("tut.direct");
    }

    private static class ReceiverConfig {

        @Bean
        public Queue autoDeleteQueue1() {
            return new AnonymousQueue();
        }

        @Bean
        public Queue autoDeleteQueue2() {
            return new AnonymousQueue();
        }
        // 创建4个binding
        // 将routingKey为orange和black的消息转发给队列1
        // 将routingKey为green和black的消息转发给队列2
        @Bean
        public Binding binding1a(DirectExchange direct,
                                 Queue autoDeleteQueue1) {
            return BindingBuilder.bind(autoDeleteQueue1)
                    .to(direct)
                    .with("orange");
        }

        @Bean
        //创建另外一个binding，将routingKey为black的消息转发给队列1
        public Binding binding1b(DirectExchange direct,
                                 Queue autoDeleteQueue1) {
            return BindingBuilder.bind(autoDeleteQueue1)
                    .to(direct)
                    .with("black");
        }

        @Bean
        //创建第三个binding，将routingKey为green的消息转发给队列2
        public Binding binding2a(DirectExchange direct,
                                 Queue autoDeleteQueue2) {
            return BindingBuilder.bind(autoDeleteQueue2)
                    .to(direct)
                    .with("green");
        }

        @Bean
        //创建第四个binding，将routingKey为black的消息转发给队列2
        public Binding binding2b(DirectExchange direct,
                                 Queue autoDeleteQueue2) {
            return BindingBuilder.bind(autoDeleteQueue2)
                    .to(direct)
                    .with("black");
        }

        @Bean
        public Receiver receiver() {
            return new Receiver();
        }
    }

    @Bean
    public Sender sender() {
        return new Sender();
    }
}
