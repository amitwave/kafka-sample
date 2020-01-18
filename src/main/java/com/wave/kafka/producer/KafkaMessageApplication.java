package com.wave.kafka.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
public class KafkaMessageApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaMessageApplication.class, args);
    }
}
