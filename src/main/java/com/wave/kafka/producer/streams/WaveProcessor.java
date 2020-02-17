package com.wave.kafka.producer.streams;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Processor;
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
