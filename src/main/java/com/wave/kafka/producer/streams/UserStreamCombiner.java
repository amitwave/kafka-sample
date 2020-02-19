package com.wave.kafka.producer.streams;

import com.wave.kafka.producer.Preference;
import com.wave.kafka.producer.User;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Predicate;
import org.apache.kafka.streams.kstream.Printed;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import static com.wave.kafka.producer.streams.WaveProcessorCombinerStream.INPUTUSERSTREAMCOFFEE;
import static com.wave.kafka.producer.streams.WaveProcessorCombinerStream.INPUTUSERSTREAMTEA;
import static com.wave.kafka.producer.streams.WaveProcessorCombinerStream.OUTPUTUSERSTREAMCOMBINER;
import static com.wave.kafka.producer.streams.WaveProcessorStream.*;



@EnableBinding(WaveProcessorCombinerStream.class)
@Component
public class UserStreamCombiner {



    @StreamListener
    @SendTo(OUTPUTUSERSTREAMCOMBINER)
    public KStream<String, User> handle1(@Input(INPUTUSERSTREAMTEA)KStream<String, User> teaStream,
                                         @Input(INPUTUSERSTREAMCOFFEE)KStream<String, User> coffeeStream) {


        KStream<String, User> combinedStream =  teaStream.merge(coffeeStream);

       /* combinedStream.mapValues(v -> {
           // System.out.println("IN the Sink Stream 333:: " + v);
             v.setName("combined " + v.getName());
             return v;
        });*/



        combinedStream.map((k,v) -> {
                    v.setName("combined " + v.getName());
                    return new KeyValue<>(k, v);
        });



        return combinedStream;

    }




}