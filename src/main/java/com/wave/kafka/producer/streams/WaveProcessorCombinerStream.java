package com.wave.kafka.producer.streams;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;


public interface WaveProcessorCombinerStream {

    String OUTPUTUSERSTREAMCOMBINER = "outputuserstreamcombiner";
    String INPUTUSERSTREAMTEA = "outputuserstreamtea";
    String INPUTUSERSTREAMCOFFEE = "outputuserstreamcoffee";

    @Output(OUTPUTUSERSTREAMCOMBINER)
    KStream outputStream();


    @Input(INPUTUSERSTREAMTEA)
    KStream outputStreamtea();


    @Input(INPUTUSERSTREAMCOFFEE)
    KStream outputStreamcoffee();


}
