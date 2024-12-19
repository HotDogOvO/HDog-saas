package com.hotdog.saas.infra.foundation.kafka.consumer;

import com.alibaba.fastjson2.JSONObject;

import org.springframework.stereotype.Component;

@Component
public abstract class AbstractKafkaConsumer<T> {

    protected T decodeMessage(String message, Class<T> clazz){
        return JSONObject.parseObject(message, clazz);
    }

}
