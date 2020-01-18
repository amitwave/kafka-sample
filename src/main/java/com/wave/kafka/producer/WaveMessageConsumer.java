package com.wave.kafka.producer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class WaveMessageConsumer {


    @KafkaListener(topics = "test", groupId = "group-id")
    public void listen(String message) {
        System.out.println("Received Messasge in group - group-id: " + message);
    }

    @KafkaListener(topics = "testuser", groupId = "group-user", containerFactory = "kafkaListenerUserContainerFactory")
    public void listenUser(User message) {
        System.out.println("Received Messasge in group - group-user: " + message.toString());
    }

}
