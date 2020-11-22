package com.wave.kafka.producer;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

public class ProducerInterceptorString implements org.apache.kafka.clients.producer.ProducerInterceptor<String, String> {



        @Override
        public void configure(Map<String, ?> configs) {
           // this.bean = (SomeBean) configs.get("some.bean");
        }

        @Override
        public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
            System.out.println("in the ProducerInterceptorString");
            return record;
        }

        @Override
        public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
        }

        @Override
        public void close() {
        }

    }