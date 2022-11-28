package com.example.publishsubscribe;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: PengYin
 * @Date: 2022/11/27/21:28
 * @Description:
 */
@Configuration
public class Config {
    @Bean
    //创建一个名为tut.fanout的FanoutExchange交换机
    public FanoutExchange fanout() {
        return new FanoutExchange("tut.fanout");
    }

    private static class ReceiverConfig {

        @Bean
        //创建一个AnonymousQueue
        public Queue autoDeleteQueue1() {
            return new AnonymousQueue();
        }

        @Bean
        //创建另外一个创建一个AnonymousQueue
        public Queue autoDeleteQueue2() {
            return new AnonymousQueue();
        }

        @Bean
        //将fanout和autoDeleteQueue1
        public Binding binding1(FanoutExchange fanout,
                                Queue autoDeleteQueue1) {
            return BindingBuilder.bind(autoDeleteQueue1).to(fanout);
        }

        @Bean
        //将fanout和autoDeleteQueue1绑定
        public Binding binding2(FanoutExchange fanout,
                                Queue autoDeleteQueue2) {
            return BindingBuilder.bind(autoDeleteQueue2).to(fanout);
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
