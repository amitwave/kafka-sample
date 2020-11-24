package com.wave.kafka.streams.config;

import com.wave.kafka.model.User;
import com.wave.kafka.streams.WaveProcessorBinding;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Printed;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;


@EnableBinding(WaveProcessorBinding.class)
@Configuration
@EnableKafkaStreams
public class KafkaStreamProducerConfig {

    private String inputTopic = "input";

    private String outputTopic= "outputString";

    @Bean
    public KStream<String, String> kStream(@Qualifier("defaultKafkaStreamsBuilder") StreamsBuilder kStreamBuilder) {
        System.out.println("in the sink 33:: ");


        KStream<String, String> stream = kStreamBuilder.stream(inputTopic);


       /* stream.mapValues(v -> {
            System.out.println("Processing Stream :: " + v);
            return v.toUpperCase();
        })
                .to(outputTopic);*/

        stream.map((k,v) ->
            new KeyValue<>(k, new String("Stream-amit  " + v).toUpperCase())
        ).to("inputStreamString");


        return stream;
    }


   // @StreamListener("input")
   // @SendTo("output")
   // @Bean
    public KStream<String, String> kStream1(@Qualifier("defaultKafkaStreamsBuilder") KStream<String, String> kStream) {
        System.out.println("in the sink 22:: ");
       /* stream.mapValues(v -> {
            System.out.println("Processing Stream :: " + v);
            return v.toUpperCase();
        })
                .to(outputTopic);*/

        kStream =  kStream.map((k,v) ->
                new KeyValue<>(k, new String("amit11  " + v.toUpperCase()))
        );//.to(outputTopic);

        return kStream;
    }

    @Bean
    public KStream<String, String>  kSink(@Qualifier("defaultKafkaStreamsBuilder") StreamsBuilder kStreamBuilder) {
        System.out.println("in the sink 11:: ");
        KStream<String, String> stream = kStreamBuilder.stream(outputTopic);



        System.out.println("in the sink :: ");
        stream.print(Printed.toSysOut());


        stream.mapValues(v -> {
            System.out.println("IN the Sink Stream :: " + v);
            return v;
        });
        return stream;
    }


    //@StreamListener("input")
    //@SendTo("outputuser")
  //  @Bean("userstream")
    public KStream<String, User>  userstream(@Qualifier("customStreamBuilder") StreamsBuilder kStreamBuilder) {

        System.out.println("in the sink 333:: ");
        KStream<String, User> stream = kStreamBuilder.stream("inputuserstream");



        System.out.println("in the user Sink 333 :: ");
        stream.print(Printed.toSysOut());


        stream.mapValues(v -> {
            System.out.println("IN the Sink Stream 333:: " + v);
            return v;
        }).to("outputuserstream");
        return stream;
    }

}