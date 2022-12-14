package com.example.topics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TopicsApplication {

    public static void main(String[] args) {
        SpringApplication.run(TopicsApplication.class, args);
    }

}
