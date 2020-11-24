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


    @Input(INPUTUSER)
    SubscribableChannel myInput();

    @Output(OUTPUTUSER)
    MessageChannel anOutput();

    @Input(INPUTSTREAMSTRING)
    SubscribableChannel inputStreamString();

    @Input(INPUTSTREAMSTRING1)
    MessageChannel inputStreamString1();

  //  @Output(INPUTSTREAMSTRING)
   // MessageChannel outputStreamString();
}
