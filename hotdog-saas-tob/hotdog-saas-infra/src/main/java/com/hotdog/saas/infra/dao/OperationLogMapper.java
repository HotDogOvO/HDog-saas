package com.hotdog.saas.infra.dao;

import com.hotdog.saas.infra.entity.OperationLogDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 操作日志表 Mapper 接口
 * </p>
 *
 * @author hotdog
 * @since 2024-12-18 22:38:37
 */
@Mapper
public interface OperationLogMapper extends BaseMapper<OperationLogDO> {

}
