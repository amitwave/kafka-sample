package com.wave.kafka.streams.simple;

import com.wave.kafka.model.User;
import com.wave.kafka.streams.WaveProcessorBinding;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import static com.wave.kafka.streams.WaveProcessorBinding.*;

@EnableBinding(WaveProcessorBinding.class)
public class SimpleDateSink {
    @StreamListener(INPUTSTREAMSTRING)
    public void handle(String s) {
        System.out.println("In the SimpleDateSink SINK  88 " + s);

    }

    @StreamListener(INPUTUSER)
    @SendTo(OUTPUTUSER)
    public User handle1(User s) {
        System.out.println("In the SimpleDateSink user SINK  99 " + s);

        s.setName("After setting " + s.getName());

        return s;
    }


    @StreamListener(OUTPUTUSER)
    public void handle2(User s) {
        System.out.println("In the SimpleDateSink user SINK  100 " + s);

    }


}