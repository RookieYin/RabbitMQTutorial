package com.example.topics;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: PengYin
 * @Date: 2022/11/28/0:07
 * @Description:
 */
@Configuration
public class Config {
    @Bean
    public TopicExchange topic() {
        return new TopicExchange("tut.topic");
    }

    private static class ReceiverConfig {

        @Bean
        public Receiver receiver() {
            return new Receiver();
        }

        @Bean
        public Queue autoDeleteQueue1() {
            return new AnonymousQueue();
        }

        @Bean
        public Queue autoDeleteQueue2() {
            return new AnonymousQueue();
        }

        @Bean
        public Binding binding1a(TopicExchange topic,
                                 Queue autoDeleteQueue1) {
            return BindingBuilder.bind(autoDeleteQueue1)
                    .to(topic)
                    .with("*.orange.*");
        }

        @Bean
        public Binding binding1b(TopicExchange topic,
                                 Queue autoDeleteQueue1) {
            return BindingBuilder.bind(autoDeleteQueue1)
                    .to(topic)
                    .with("*.*.rabbit");
        }

        @Bean
        public Binding binding2a(TopicExchange topic,
                                 Queue autoDeleteQueue2) {
            return BindingBuilder.bind(autoDeleteQueue2)
                    .to(topic)
                    .with("lazy.#");
        }

    }

    @Bean
    public Sender sender() {
        return new Sender();
    }
}
