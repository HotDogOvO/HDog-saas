package com.hotdog.saas.infra.dao;

import com.hotdog.saas.infra.entity.WechatAppDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 微信小程序表 Mapper 接口
 * </p>
 *
 * @author hotdog
 * @since 2024-12-20 13:50:26
 */
@Mapper
public interface WechatAppMapper extends BaseMapper<WechatAppDO> {

}
