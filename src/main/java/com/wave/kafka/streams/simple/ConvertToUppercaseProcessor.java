package com.wave.kafka.streams.simple;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.handler.annotation.SendTo;


@EnableBinding(UpperCaseProcessorBinder.class)
public class ConvertToUppercaseProcessor {

    @StreamListener("input")
    @SendTo("outputUpperCase")
    public String process(String s) {
        System.out.println("Stream:: In the UppercaseProcessor 22 " + s);
        return s.toUpperCase();
    }




}