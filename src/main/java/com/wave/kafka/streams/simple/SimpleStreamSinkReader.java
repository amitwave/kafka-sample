package com.wave.kafka.streams.simple;

import com.wave.kafka.model.User;
import com.wave.kafka.streams.WaveProcessorBinding;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import static com.wave.kafka.streams.WaveProcessorBinding.INPUTUSER;



//@EnableBinding(KStreamProcessor.class)
@EnableBinding(WaveProcessorBinding.class)
@Component
public class SimpleStreamSinkReader {

    @StreamListener("outputUpperCase")
    public void handle(String s) {
        System.out.println("Stream:: In the UppercaseSink SINK  88 " + s);
    }

    @StreamListener(INPUTUSER)
    public void handle1(User s) {
        System.out.println("Stream:: In the UppercaseSink user SINK  99 " + s);
    }


}