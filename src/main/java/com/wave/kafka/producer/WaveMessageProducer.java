package com.wave.kafka.producer;


import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import java.util.regex.Pattern;

@Component
public class WaveMessageProducer {

    @Autowired
    @Qualifier("stringTemplate")
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    @Qualifier("kafkaUserTemplate")
    private KafkaTemplate<String, User> kafkaUserTemplate;

    public void sendMessage(String msg) {

        kafkaTemplate.send("test", "key"+msg.hashCode(), msg);
        kafkaTemplate.send("input", "key"+msg.hashCode(), msg);

        User user = new User();
        user.setId(new Random().nextInt());
        user.setName("Name " + user.getId());
        user.setDob(new Date().getTime());
        user.setPreference(user.getId() %2 == 0 ? Preference.COFFEE: Preference.TEA);

        kafkaUserTemplate.send("testuser", user.getId().toString(), user);

    }


}
