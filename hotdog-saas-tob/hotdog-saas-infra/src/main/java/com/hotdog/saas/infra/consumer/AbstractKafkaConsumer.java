package com.hotdog.saas.infra.consumer;

import com.alibaba.fastjson2.JSONObject;

public abstract class AbstractKafkaConsumer<T> {

    protected T decodeMessage(String message, Class<T> clazz){
        return JSONObject.parseObject(message, clazz);
    }

}
