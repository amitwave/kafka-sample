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
        kafkaTemplate.send("test", "key"+msg.hashCode(), msg);
        kafkaTemplate.send("input", "key"+msg.hashCode(), msg);
        kafkaTemplate.send("inputStreamString", "key"+msg.hashCode(), msg);

        User user = new User();
        user.setId(new Random().nextInt());
        user.setName("Name " + user.getId());
        user.setDob(new Date().getTime());
        user.setPreference(user.getId() %2 == 0 ? Preference.COFFEE: Preference.TEA);

        kafkaUserTemplate.send("testuser", user.getId().toString(), user);
        kafkaUserTemplate.send("inputuser", user.getId().toString(), user);
        kafkaUserTemplate.send("inputuserstream", user.getId().toString(), user);
    }


}
