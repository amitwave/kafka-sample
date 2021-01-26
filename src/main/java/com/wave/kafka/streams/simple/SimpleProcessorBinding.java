package com.wave.kafka.streams.simple;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;


public interface SimpleProcessorBinding {

    String INPUTUSER = "inputuser";

    String OUTPUTUSER = "outputuser";

    String SIMPLE_INPUTSTREAMSTRING = "simpleinputStreamString";
    String SIMPLE_INPUTSTREAMSTRING11 = "simpleinputStreamString11";
    String INPUTSTREAMSTRINGBUILDER = "inputStreamStringBuilder";
    String INPUTSTREAMSTRING1 = "inputStreamString1";


    @Input
    SubscribableChannel inputuser();

    @Output(OUTPUTUSER)
    MessageChannel outputuser();

    @Input
    SubscribableChannel simpleinputStreamString();

    @Input(INPUTSTREAMSTRINGBUILDER)
    KStream<String, String> inputStreamStringBuilder();

    @Output(INPUTSTREAMSTRING1)
    KStream<String, String> outputStreamString();

    @Input(INPUTSTREAMSTRING1)
    KStream<String, String> intputStreamString();
}
