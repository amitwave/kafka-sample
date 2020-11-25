package com.wave.kafka.streams.user;

import com.wave.kafka.model.Preference;
import com.wave.kafka.model.User;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Predicate;
import org.apache.kafka.streams.kstream.Printed;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;

import static com.wave.kafka.streams.user.UserProcessorBinding.OUTPUTUSERSTREAM;

//@EnableBinding(UserProcessorBinding.class)
public class UserDataSplitter {


    Predicate<String, User> isTea = (k, v) -> v.getPreference().equals(Preference.TEA);
    Predicate<String, User> isCoffee = (k, v) -> v.getPreference().equals(Preference.COFFEE);

    @StreamListener(OUTPUTUSERSTREAM)
    //@SendTo({OUTPUTUSERSTREAMTEA, OUTPUTUSERSTREAMCOFFEE})
    public KStream<String, User>[] handle1(@Input(OUTPUTUSERSTREAM) KStream<String, User> kSink) {

        System.out.println("In the UppercaseSink SINK  handle1 kSink1");
        kSink.print(Printed.toSysOut());

        return kSink.branch(isTea, isCoffee);

        //  return kSink;

    }




}