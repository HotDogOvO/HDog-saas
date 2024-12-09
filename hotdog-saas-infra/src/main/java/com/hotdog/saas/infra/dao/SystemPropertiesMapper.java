package com.hotdog.saas.infra.dao;

import com.hotdog.saas.infra.entity.po.SystemPropertiesPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 系统配置表 Mapper 接口
 * </p>
 *
 * @author hotdog
 * @since 2024-12-09 09:58:22
 */
@Mapper
public interface SystemPropertiesMapper extends BaseMapper<SystemPropertiesPO> {

}
