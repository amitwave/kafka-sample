package com.wave.kafka.producer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class WaveMessageConsumer {


    @KafkaListener(topics = "test", groupId = "group-id", containerFactory = "kafkaListenerContainerFactory")
    public void listen(String message) {
        System.out.println("Received Messasge in group - group-id: " + message);
    }



}
