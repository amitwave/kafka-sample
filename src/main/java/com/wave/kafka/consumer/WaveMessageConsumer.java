package com.wave.kafka.consumer;

import com.wave.kafka.model.User;
import com.wave.kafka.util.JsonUtils;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class WaveMessageConsumer {


    @KafkaListener(topics = "test", groupId = "group-id", containerFactory = "kafkaListenerContainerFactory")
    public void listen(String message) {
        System.out.println("Received Messasge in group - group-id: " + message);
    }

    @KafkaListener(topics = "testuserstring", groupId = "group-user-id", containerFactory = "kafkaListenerContainerFactory")
    public void listenUser(String testuserstring) {

        System.out.println("Received Messasge in group - group-user-id: " + testuserstring);

        User user = JsonUtils.fromString(testuserstring, User.class);
        System.out.println("Received User after deserialisation: " + user);
    }

}
