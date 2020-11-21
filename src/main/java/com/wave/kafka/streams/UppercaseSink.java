package com.wave.kafka.streams;

import com.wave.kafka.model.User;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import static com.wave.kafka.streams.WaveProcessor.INPUTUSER;



//@EnableBinding(KStreamProcessor.class)
//@EnableBinding(WaveProcessor.class)
//@Component
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