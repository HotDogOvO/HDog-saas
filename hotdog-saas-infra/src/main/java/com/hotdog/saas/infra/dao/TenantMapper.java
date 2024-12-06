package com.hotdog.saas.infra.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hotdog.saas.infra.entity.po.TenantPO;

import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 租户表 Mapper 接口
 * </p>
 *
 * @author hotdog
 * @since 2024-12-06 14:08:15
 */
@Mapper
public interface TenantMapper extends BaseMapper<TenantPO> {

}
