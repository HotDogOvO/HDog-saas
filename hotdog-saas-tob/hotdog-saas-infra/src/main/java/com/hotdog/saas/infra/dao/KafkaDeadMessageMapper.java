package com.hotdog.saas.infra.dao;

import com.hotdog.saas.infra.entity.KafkaDeadMessageDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * Kafka死信队列表 Mapper 接口
 * </p>
 *
 * @author hotdog
 * @since 2024-12-19 17:22:44
 */
@Mapper
public interface KafkaDeadMessageMapper extends BaseMapper<KafkaDeadMessageDO> {

}
