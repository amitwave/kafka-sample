package com.wave.kafka.streams;

import com.wave.kafka.model.Preference;
import com.wave.kafka.model.User;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Predicate;
import org.apache.kafka.streams.kstream.Printed;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.binder.kstream.annotations.KStreamProcessor;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import static com.wave.kafka.streams.WaveProcessorStream.INPUTUSERSTREAM;
import static com.wave.kafka.streams.WaveProcessorStream.OUTPUTUSERSTREAMTEA;
import static com.wave.kafka.streams.WaveProcessorStream.OUTPUTUSERSTREAMCOFFEE;


//@EnableBinding(KStreamProcessor.class)
@EnableBinding(WaveProcessorStream.class)
@Component
public class UserStreamBrancher {


    Predicate<String, User> isTea = (k, v) -> v.getPreference().equals(Preference.TEA);
    Predicate<String, User> isCoffee =  (k, v) -> v.getPreference().equals(Preference.COFFEE);

    @StreamListener(INPUTUSERSTREAM)
    @SendTo({OUTPUTUSERSTREAMTEA, OUTPUTUSERSTREAMCOFFEE})
    public KStream<String, User>[] handle1(KStream<String, User> kSink) {

        System.out.println("Stream:: In the StreamBrancher SINK  handle1 kSink1" );
        kSink.print(Printed.toSysOut());

        return kSink.branch(isTea, isCoffee);



      //  return kSink;

    }




}