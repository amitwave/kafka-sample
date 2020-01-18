package com.wave.kafka.producer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Random;

@Component
public class WaveMessageProducer {

    @Autowired
    @Qualifier("stringTemplate")
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    @Qualifier("kafkaUserTemplate")
    private KafkaTemplate<String, User> kafkaUserTemplate;

    public void sendMessage(String msg) {

        kafkaTemplate.send("test", msg);

        User user = new User();
        user.setId(new Random().nextInt());
        user.setName("Name " + user.getId());
        user.setDob(new Date().getTime());

        kafkaUserTemplate.send("testuser", user);



    }


}
