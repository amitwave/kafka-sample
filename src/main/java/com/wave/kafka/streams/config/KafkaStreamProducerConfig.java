package com.wave.kafka.streams.config;

import com.wave.kafka.model.User;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;

import static com.wave.kafka.streams.simple.SimpleProcessorBinding.INPUTSTREAMSTRINGBUILDER;
import static com.wave.kafka.streams.user.UserProcessorBinding.OUTPUTUSERSTREAM;


//@EnableBinding(WaveProcessorBinding.class)
@Configuration
@EnableKafkaStreams
public class KafkaStreamProducerConfig {

    private String inputTopic = "input";

    @Bean
    public KStream<String, String> kStream(@Qualifier("defaultKafkaStreamsBuilder") StreamsBuilder kStreamBuilder) {
        System.out.println("in the sink 33:: ");


        KStream<String, String> stream = kStreamBuilder.stream(inputTopic);


        stream.map((k, v) ->
                new KeyValue<>(k, new String("Stream-amit-customStreamBuilder1-" + v).toUpperCase())
        ).to(INPUTSTREAMSTRINGBUILDER);


        return stream;
    }




    //@StreamListener("input")
    //@SendTo("outputuser")
    @Bean//("userstream")
    public KStream<String, User> userstream(@Qualifier("customStreamBuilder") StreamsBuilder kStreamBuilder) {

        System.out.println("in the sink 333:: ");
        KStream<String, User> stream = kStreamBuilder.stream("inputuserstream");


        System.out.println("in the user Sink 333 :: ");
        //  stream.print(Printed.toSysOut());


        stream.mapValues(v -> {
            System.out.println("IN the Sink Stream 333:: " + v);
            return v;
        }).to(OUTPUTUSERSTREAM);
        return stream;
    }

}