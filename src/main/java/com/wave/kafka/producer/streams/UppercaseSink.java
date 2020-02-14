package com.wave.kafka.producer.streams;

import com.wave.kafka.producer.User;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Printed;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;


//@EnableBinding(Sink.class)
@Component
public class UppercaseSink {

    @StreamListener("output")
    public void handle(String s) {
        System.out.println("In the SINK  " + s);

    }

    //@StreamListener("output")
    public void handle1(User s) {
        System.out.println("In the SINK  " + s);

    }

    /*@StreamListener("output")
    public void handle1(KStream<String, String> input) {


        input.print(Printed.toSysOut());

    }*/
}