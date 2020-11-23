package com.wave.kafka.streams.simple;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface UpperCaseProcessorBinder {

    String OUTPUT = "outputUpperCase";

    String INPUT = "input";

    @Input("input")
    SubscribableChannel input();

    @Output("outputUpperCase")
    MessageChannel outputUpperCase();
}
