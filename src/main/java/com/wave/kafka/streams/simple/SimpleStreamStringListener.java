package com.wave.kafka.streams.simple;

import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Printed;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import static com.wave.kafka.streams.simple.SimpleProcessorBinding.INPUTSTREAMSTRING1;
import static com.wave.kafka.streams.simple.SimpleProcessorBinding.INPUTSTREAMSTRINGBUILDER;

//@EnableKafkaStreams
@EnableBinding(SimpleProcessorBinding.class)
@Component
public class SimpleStreamStringListener {


    @StreamListener//(INPUTSTREAMSTRINGBUILDER)
    @SendTo(INPUTSTREAMSTRING1)
    public KStream<String, String> handle(@Input(INPUTSTREAMSTRINGBUILDER) KStream<String, String> stringStream) {
        System.out.println("Stream:: In the UppercaseSink SINK  8811 ");

        KStream<String, String> ss = stringStream.map((k, v) ->
                new KeyValue<>(k.toUpperCase() + "-key", new String("Stream-8811-  " + v).toUpperCase())
        );
//       System.out.println("===SimpleStreamStringListener.handle");
        ss.print(Printed.toSysOut());
//       System.out.println("====/SimpleStreamStringListener.handle");
        return ss;
    }

    @StreamListener//(INPUTSTREAMSTRINGBUILDER)
    //@SendTo(INPUTSTREAMSTRING1)
    public void handle1(@Input(INPUTSTREAMSTRING1) KStream<String, String> stringStream) {
        System.out.println("Stream:: In the UppercaseSink SINK  8812 ");

        stringStream.print(Printed.toSysOut());


    }

}
