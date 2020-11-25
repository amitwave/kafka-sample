package com.wave.kafka.streams.simple;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;


public interface SimpleProcessorBinding {

    String INPUTUSER = "inputuser";

    String OUTPUTUSER = "outputuser";

    String INPUTSTREAMSTRING = "inputStreamString";
    String INPUTSTREAMSTRINGBUILDER = "inputStreamStringBuilder";
    String INPUTSTREAMSTRING1 = "inputStreamString1";


    @Input
    SubscribableChannel inputuser();

    @Output(OUTPUTUSER)
    MessageChannel outputuser();

    @Input
    SubscribableChannel inputStreamString();

    @Input(INPUTSTREAMSTRINGBUILDER)
    KStream<String, String> inputStreamStringBuilder();

    @Output(INPUTSTREAMSTRING1)
    KStream<String, String> outputStreamString();
}
