package com.hotdog.saas.matrix.infra.foundation.kafka.consumer;

import com.alibaba.fastjson2.JSONObject;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Slf4j
@Component
public abstract class AbstractKafkaConsumer<T> {

    protected T decodeMessage(String message, Class<T> clazz) {
        return JSONObject.parseObject(message, clazz);
    }
}
