package com.hotdog.saas.infra.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hotdog.saas.domain.enums.kafka.KafkaDeadMessageStatusEnum;
import com.hotdog.saas.domain.model.KafkaDeadMessage;
import com.hotdog.saas.domain.repository.KafkaDeadMessageRepository;
import com.hotdog.saas.infra.converter.KafkaDeadMessageConverter;
import com.hotdog.saas.infra.dao.KafkaDeadMessageMapper;
import com.hotdog.saas.infra.entity.KafkaDeadMessageDO;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class KafkaDeadMessageRepositoryImpl extends AbstractBaseRepository implements KafkaDeadMessageRepository {

    private final KafkaDeadMessageMapper kafkaDeadMessageMapper;

    public KafkaDeadMessageRepositoryImpl(KafkaDeadMessageMapper kafkaDeadMessageMapper) {
        this.kafkaDeadMessageMapper = kafkaDeadMessageMapper;
    }

    @Override
    public void save(KafkaDeadMessage kafkaDeadMessage) {
        KafkaDeadMessageDO kafkaDeadMessageDO = KafkaDeadMessageConverter.INSTANCE.convert2DO(kafkaDeadMessage);
        kafkaDeadMessageMapper.insert(kafkaDeadMessageDO);
    }

    @Override
    public List<KafkaDeadMessage> list(List<KafkaDeadMessageStatusEnum> statusList) {
        LambdaQueryWrapper<KafkaDeadMessageDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(KafkaDeadMessageDO::getStatus, statusList);
        List<KafkaDeadMessageDO> kafkaDeadMessageDOList = kafkaDeadMessageMapper.selectList(lambdaQueryWrapper);
        return kafkaDeadMessageDOList.stream().map(KafkaDeadMessageConverter.INSTANCE::convert).toList();
    }
}
