package com.wave.kafka.consumer;

import com.wave.kafka.model.User;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class WaveUserMessageConsumer {

    @KafkaListener(topics = "testuser", groupId = "group-user", containerFactory = "kafkaListenerUserContainerFactory")
    public void listenUser(User message) {
        System.out.println("Received Messasge in WaveUserMessageConsumer group - group-user: 66" + message.toString());
    }

}
