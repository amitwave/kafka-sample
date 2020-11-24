package com.wave.kafka.streams.simple;

import com.wave.kafka.model.User;
import com.wave.kafka.streams.WaveProcessorBinding;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import static com.wave.kafka.streams.WaveProcessorBinding.*;


//@EnableBinding(KStreamProcessor.class)
@EnableBinding(WaveProcessorBinding.class)
@Component
public class SimpleDateSink {
    @StreamListener(INPUTSTREAMSTRING)
    public void handle(String s) {
        System.out.println("In the UppercaseSink SINK  88 " + s);

    }

    @StreamListener(INPUTUSER)
    public void handle1(User s) {
        System.out.println("In the UppercaseSink user SINK  99 " + s);

    }


}