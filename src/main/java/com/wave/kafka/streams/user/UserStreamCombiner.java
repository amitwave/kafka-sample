package com.wave.kafka.streams.user;

import com.wave.kafka.model.User;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Printed;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;

import static com.wave.kafka.streams.user.UserProcessorBinding.*;


//@EnableBinding(WaveProcessorCombinerStream.class)
//@Component
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


    @StreamListener(OUTPUTUSERSTREAMCOMBINER)
    public KStream<String, User> handle2(KStream<String, User> combinedStream) {


        combinedStream.print(Printed.toSysOut());

        return combinedStream;

    }


}