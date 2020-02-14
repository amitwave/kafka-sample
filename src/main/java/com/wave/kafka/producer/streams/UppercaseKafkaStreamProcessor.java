package com.wave.kafka.producer.streams;

import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Printed;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.binder.kafka.streams.annotations.KafkaStreamsProcessor;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.messaging.handler.annotation.SendTo;


//@EnableBinding(KafkaStreamsProcessor.class)
@Configuration
@EnableKafkaStreams
public class UppercaseKafkaStreamProcessor {

    @Value("${kafka.topic.input}")
    private String inputTopic;

    @Value("${kafka.topic.output}")
    private String outputTopic;

    @Bean
    public KStream<String, String> kStream(@Qualifier("defaultKafkaStreamsBuilder") StreamsBuilder kStreamBuilder) {



        KStream<String, String> stream = kStreamBuilder.stream(inputTopic);

       /* stream.mapValues(v -> {
            System.out.println("Processing Stream :: " + v);
            return v.toUpperCase();
        })
                .to(outputTopic);*/

        stream.map((k,v) ->
            new KeyValue<>(k, new String("amit  " + v.toUpperCase()))
        ).to(outputTopic);

        return stream;
    }


   // @StreamListener("input")
   // @SendTo("output")
    //@Bean
    public KStream<String, String> kStream1(@Qualifier("defaultKafkaStreamsBuilder") KStream<String, String> stream) {

       /* stream.mapValues(v -> {
            System.out.println("Processing Stream :: " + v);
            return v.toUpperCase();
        })
                .to(outputTopic);*/

        stream.map((k,v) ->
                new KeyValue<>(k, new String("amit11  " + v.toUpperCase()))
        ).to(outputTopic);

        return stream;
    }

    @Bean
    public KStream<String, String>  kSink(@Qualifier("defaultKafkaStreamsBuilder") StreamsBuilder kStreamBuilder) {
        KStream<String, String> stream = kStreamBuilder.stream(outputTopic);



        System.out.println("in the sink :: ");
        stream.print(Printed.toSysOut());


        stream.mapValues(v -> {
            System.out.println("IN the Sink Stream :: " + v);
            return v;
        });
        return stream;
    }

}