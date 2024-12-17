package com.hotdog.saas.infra.dao;

import com.hotdog.saas.infra.entity.LoginLogDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 登陆日志表 Mapper 接口
 * </p>
 *
 * @author hotdog
 * @since 2024-12-17 13:16:10
 */
@Mapper
public interface LoginLogMapper extends BaseMapper<LoginLogDO> {

}
