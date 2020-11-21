package com.wave.kafka.streams;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.handler.annotation.SendTo;


//@EnableBinding(Processor.class)
public class UppercaseProcessor {

    @StreamListener(Processor.INPUT)
    @SendTo(Processor.OUTPUT)
    public String process(String s) {
        System.out.println("In the UppercaseProcessor 22 " + s);
        return s.toUpperCase();
    }




}