package com.wave.kafka.streams.simple;

import com.wave.kafka.model.User;
import com.wave.kafka.streams.WaveProcessorBinding;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;

import static com.wave.kafka.streams.WaveProcessorBinding.*;

@EnableBinding(WaveProcessorBinding.class)
public class SimpleDateSink {
    @StreamListener(INPUTSTREAMSTRING)
    public void handle(String s) {
        System.out.println("Stream:: 98 In producer -> inputStreamString - consumer-> sink the SimpleDateSink SINK  98 " + s);

    }

    @StreamListener(INPUTUSER)
    @SendTo(OUTPUTUSER)
    public User handle1(User s) {
        System.out.println("Stream:: 99 In producer -> inputuser - consumer-> outputuser the SimpleDateSink user SINK  99 " + s);

        s.setName("After setting " + s.getName());

        return s;
    }


    @StreamListener(OUTPUTUSER)
    public void handle2(User s) {
        System.out.println("Stream:: 100 In producer -> outputuser - consumer-> sink the SimpleDateSink user SINK  100 " + s);
    }


}