package com.wave.kafka.streams.user;

import com.wave.kafka.model.User;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;


public interface UserProcessorBinding {

    String INPUTUSERSTREAM = "inputuserstream";
    String INPUTUSERSTREAMSTRINGBUILDER = "INPUTUSERSTREAMSTRINGBUILDER";
    String OUTPUTUSERSTREAM = "outputuserstream";

    String OUTPUTUSERSTREAMTEA = "outputuserstreamtea";
    String OUTPUTUSERSTREAMCOFFEE = "outputuserstreamcoffee";

    String OUTPUTUSERSTREAMCOMBINER = "outputuserstreamcombiner";

    String INPUTUSERSTREAMTEA = "outputuserstreamtea";
    String INPUTUSERSTREAMCOFFEE = "outputuserstreamcoffee";

    /*   @Output(OUTPUTUSERSTREAMCOMBINER)
       KStream outputuserstreamcombiner();


       @Input(INPUTUSERSTREAMTEA)
       KStream outputuserstreamtea();


       @Input(INPUTUSERSTREAMCOFFEE)
       KStream outputuserstreamcoffee();
   */
    @Input(INPUTUSERSTREAMSTRINGBUILDER)
    KStream<String, User> inputStreamStringBuilder();

  /*  @Input(OUTPUTUSERSTREAM)
    KStream<String, User> inputStream();


    @Output(OUTPUTUSERSTREAM)
    KStream<String, User> outputStream();


    @Output(OUTPUTUSERSTREAMTEA)
    KStream outputStreamtea();


    @Output(OUTPUTUSERSTREAMCOFFEE)
    KStream outputStreamcoffee();*/


}
