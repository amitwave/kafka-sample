package com.wave.kafka.streams.simple;

import com.wave.kafka.model.User;
import com.wave.kafka.streams.WaveProcessorBinding;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Printed;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import static com.wave.kafka.streams.WaveProcessorBinding.*;
import static com.wave.kafka.streams.WaveProcessorCombinerStream.INPUTUSERSTREAMTEA;

@EnableBinding(WaveProcessorBinding.class)
@Component
public class SimpleStreamStringListener {


   @StreamListener(INPUTSTREAMSTRING11)
   @SendTo("outputStreamString")
    public KStream<String, String>  handle(KStream<String, String> stringStream) {
        System.out.println("Stream:: In the UppercaseSink SINK  8811 ");

        stringStream.print(Printed.toSysOut());
        return stringStream;
    }

}
