package com.hotdog.saas.infra.converter;

import com.hotdog.saas.domain.model.KafkaDeadMessage;
import com.hotdog.saas.domain.model.Login;
import com.hotdog.saas.infra.entity.KafkaDeadMessageDO;
import com.hotdog.saas.infra.entity.UserDO;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface KafkaDeadMessageConverter {

    KafkaDeadMessageConverter INSTANCE = Mappers.getMapper(KafkaDeadMessageConverter.class);

    KafkaDeadMessage convert(KafkaDeadMessageDO kafkaDeadMessageDO);

    KafkaDeadMessageDO convert2DO(KafkaDeadMessage kafkaDeadMessage);
}
