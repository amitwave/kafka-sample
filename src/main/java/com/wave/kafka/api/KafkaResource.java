package com.wave.kafka.api;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.wave.kafka.producer.WaveMessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/")
public class KafkaResource {

    @Autowired
    private WaveMessageProducer waveMessageProducer;

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();


    @GetMapping(value = "/health-check")
    public ResponseEntity healthcheck() throws JsonProcessingException {
        waveMessageProducer.sendMessage("hi " + new Date());
        return ResponseEntity.ok("Success " + new Date().toString());
    }

}
