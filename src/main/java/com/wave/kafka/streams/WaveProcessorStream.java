package com.wave.kafka.streams;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.binder.kstream.annotations.KStreamProcessor;


public interface WaveProcessorStream extends KStreamProcessor {

    String INPUTUSERSTREAM = "inputuserstream";
    String OUTPUTUSERSTREAMTEA = "outputuserstreamtea";
    String OUTPUTUSERSTREAMCOFFEE = "outputuserstreamcoffee";

    @Input(INPUTUSERSTREAM)
    KStream inputStream();


   // @Output(OUTPUTUSERSTREAM)
  //  KStream outputStream();


    @Output(OUTPUTUSERSTREAMTEA)
    KStream outputStreamtea();


    @Output(OUTPUTUSERSTREAMCOFFEE)
    KStream outputStreamcoffee();


}
