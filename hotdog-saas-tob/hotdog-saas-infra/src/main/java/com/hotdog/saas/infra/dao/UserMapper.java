package com.hotdog.saas.infra.dao;

import com.hotdog.saas.infra.entity.UserDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author hotdog
 * @since 2024-12-12 18:42:49
 */
@Mapper
public interface UserMapper extends BaseMapper<UserDO> {

}
