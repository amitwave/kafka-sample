package com.wave.kafka.streams.user;

import com.wave.kafka.model.Preference;
import com.wave.kafka.model.User;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Predicate;
import org.apache.kafka.streams.kstream.Printed;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;

import static com.wave.kafka.streams.user.UserProcessorBinding.INPUTUSERSTREAMSTRINGBUILDER;

@EnableBinding(UserProcessorBinding.class)
//@Component
public class UserDataSplitter {


    Predicate<String, User> isTea = (k, v) -> v.getPreference().equals(Preference.TEA);
    Predicate<String, User> isCoffee = (k, v) -> v.getPreference().equals(Preference.COFFEE);

    @StreamListener//(INPUTUSERSTREAMSTRINGBUILDER)
    //@SendTo({OUTPUTUSERSTREAMTEA, OUTPUTUSERSTREAMCOFFEE})
    //public KStream<String, User>[] handle1(@Input(OUTPUTUSERSTREAM) KStream<String, User> kSink) {
    public void handle1(@Input(INPUTUSERSTREAMSTRINGBUILDER) KStream<String, User> kSink) {

        System.out.println("In the UserDataSplitter SINK  handle1 kSink1");
        kSink.print(Printed.toSysOut());

        // return kSink.branch(isTea, isCoffee);

        //  return kSink;

    }




}