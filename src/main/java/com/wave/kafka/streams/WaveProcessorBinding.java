package com.wave.kafka.streams;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;


public interface WaveProcessorBinding {

    String INPUTUSER = "inputuser";

    String OUTPUTUSER = "outputuser";

    String INPUTSTREAMSTRING = "inputStreamString";
    String INPUTSTREAMSTRING1 = "inputStreamString1";


    @Input
    SubscribableChannel inputuser();

    @Output(OUTPUTUSER)
    MessageChannel outputuser();

    @Input
    SubscribableChannel inputStreamString();

    @Input(INPUTSTREAMSTRING1)
    SubscribableChannel inputStreamString1();

  //  @Output(INPUTSTREAMSTRING)
   // MessageChannel outputStreamString();
}
