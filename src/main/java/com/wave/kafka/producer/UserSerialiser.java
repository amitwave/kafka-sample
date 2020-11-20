package com.wave.kafka.producer;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.wave.kafka.model.User;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class UserSerialiser implements Serializer<User> {


    @Override
    public void configure(Map map, boolean b) {

    }




    @Override
    public void close() {

    }

    @Override
    public byte[] serialize(String arg0, User arg1) {
        byte[] retVal = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            retVal = objectMapper.writeValueAsString(arg1).getBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retVal;
    }



}