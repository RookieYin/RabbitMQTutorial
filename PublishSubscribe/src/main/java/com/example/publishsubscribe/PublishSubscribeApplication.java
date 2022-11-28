package com.example.publishsubscribe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PublishSubscribeApplication {

    public static void main(String[] args) {
        SpringApplication.run(PublishSubscribeApplication.class, args);
    }

}
