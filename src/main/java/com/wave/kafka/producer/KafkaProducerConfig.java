package com.wave.kafka.producer;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.wave.kafka.model.User;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerde;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Bean(name = "stringMessage")
    public ProducerFactory<String, String> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, ProducerInterceptorString.class.getName());
        return new DefaultKafkaProducerFactory<>(configProps);
    }


    @Bean(name = "stringTemplate")
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean(name = "userProducerFactory")
    public ProducerFactory<String, User> producerUserFactory() {
        ObjectMapper mapper = new ObjectMapper();
        Serde<User> userJsonSerde = new JsonSerde<>(User.class, mapper);


        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, userJsonSerde.serializer().getClass());
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean(name = "kafkaUserTemplate")
    public KafkaTemplate<String, User> kafkaUserTemplate() {
        return new KafkaTemplate<String, User>(producerUserFactory());
    }


}