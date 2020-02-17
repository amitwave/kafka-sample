package com.wave.kafka.producer.streams;

import com.wave.kafka.producer.User;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Printed;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import static com.wave.kafka.producer.streams.WaveProcessor.INPUTUSER;



//@EnableBinding(KStreamProcessor.class)
@EnableBinding(WaveProcessor.class)
@Component
public class UppercaseSink {

    @StreamListener("output")
    public void handle(String s) {
        System.out.println("In the UppercaseSink SINK  88 " + s);

    }

    @StreamListener(INPUTUSER)
    public void handle1(User s) {
        System.out.println("In the UppercaseSink user SINK  99 " + s);

    }


}