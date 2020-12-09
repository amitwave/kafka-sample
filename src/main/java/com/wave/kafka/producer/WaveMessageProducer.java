package com.wave.kafka.producer;


import com.wave.kafka.model.Preference;
import com.wave.kafka.model.User;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Random;

import static com.wave.kafka.streams.simple.SimpleProcessorBinding.*;
import static com.wave.kafka.streams.user.UserProcessorBinding.INPUTUSERSTREAM;

@Component
public class WaveMessageProducer {

    @Autowired
    @Qualifier("stringTemplate")
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    @Qualifier("kafkaUserTemplate")
    private KafkaTemplate<String, User> kafkaUserTemplate;

    public void sendMessage(String msg) {

        kafkaTemplate.setProducerListener(new ProducerListener<String, String>() {
            @Override
            public void onSuccess(ProducerRecord<String, String> producerRecord, RecordMetadata recordMetadata) {
                System.out.println("Topic = " + recordMetadata.topic());
            }

            @Override
            public void onSuccess(String topic, Integer partition, String key, String value, RecordMetadata recordMetadata) {

            }

            @Override
            public void onError(ProducerRecord<String, String> producerRecord, Exception exception) {

            }

            @Override
            public void onError(String topic, Integer partition, String key, String value, Exception exception) {

            }
        });
        kafkaTemplate.send("test", "test--key" + msg.hashCode(), msg);
        kafkaTemplate.send(INPUT, INPUT + "--key" + msg.hashCode(), msg);
        kafkaTemplate.send(SIMPLE_INPUTSTREAMSTRING, SIMPLE_INPUTSTREAMSTRING + "--key" + msg.hashCode(), msg);
        // kafkaTemplate.send(INPUTSTREAMSTRINGBUILDER, "key"+msg.hashCode(), msg);

        User user = new User();
        user.setId(new Random().nextInt());
        user.setName("Name " + user.getId());
        user.setDob(new Date().getTime());
        user.setPreference(user.getId() % 2 == 0 ? Preference.COFFEE : Preference.TEA);

        kafkaUserTemplate.send("testuser", "testuser--" + user.getId().toString(), user);
        kafkaUserTemplate.send(INPUTUSER, INPUTUSER + "--" + user.getId().toString(), user);
        kafkaUserTemplate.send(INPUTUSERSTREAM, INPUTUSERSTREAM + "--" + user.getId().toString(), user);
    }


}
