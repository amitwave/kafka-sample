package com.wave.kafka.streams;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;


public interface WaveProcessor {

    String INPUTUSER = "inputuser";

    String OUTPUTUSER = "outputuser";


    @Input(INPUTUSER)
    SubscribableChannel myInput();

    @Output(OUTPUTUSER)
    MessageChannel anOutput();


}
