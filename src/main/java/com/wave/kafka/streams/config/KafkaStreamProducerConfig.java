package com.wave.kafka.streams.config;

import com.wave.kafka.model.User;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Printed;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;

import static com.wave.kafka.streams.simple.SimpleProcessorBinding.INPUT;
import static com.wave.kafka.streams.simple.SimpleProcessorBinding.INPUTSTREAMSTRINGBUILDER;
import static com.wave.kafka.streams.user.UserProcessorBinding.INPUTUSERSTREAM;
import static com.wave.kafka.streams.user.UserProcessorBinding.INPUTUSERSTREAMSTRINGBUILDER;


//@EnableBinding(WaveProcessorBinding.class)
@Configuration
@EnableKafkaStreams
public class KafkaStreamProducerConfig {

    @Bean
    public KStream<String, String> kStream(@Qualifier("customStreamBuilder") StreamsBuilder kStreamBuilder) {
        System.out.println("in the sink 33:: ");


        KStream<String, String> stream = kStreamBuilder.stream(INPUT);


        stream.map((k, v) ->
                new KeyValue<>(k, new String("Stream-amit-customStreamBuilder-" + v).toUpperCase())
        ).to(INPUTSTREAMSTRINGBUILDER);


        return stream;
    }


    //@StreamListener("input")
    //@SendTo("outputuser")
    @Bean//("userstream")
    public KStream<String, User> userstream(@Qualifier("customUserStreamBuilder") StreamsBuilder kStreamBuilder) {

        System.out.println("in the customUserStreamBuilder:: ");
        KStream<String, User> stream = kStreamBuilder.stream(INPUTUSERSTREAM);
        stream.print(Printed.toSysOut());
        System.out.println("in the user customUserStreamBuilder :: ");
        //  stream.print(Printed.toSysOut());


        stream.map((k, v) ->
                new KeyValue<>(k, new User(v.getId(), v.getName() + "--customUserStreamBuilder", v.getDob(), v.getPreference()))
        ).to(INPUTUSERSTREAMSTRINGBUILDER);
        stream.print(Printed.toSysOut());

        return stream;
    }

}