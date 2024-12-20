package com.hotdog.saas.infra.dao;

import com.hotdog.saas.infra.entity.UserRoleDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户角色表 Mapper 接口
 * </p>
 *
 * @author hotdog
 * @since 2024-12-14 21:40:26
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRoleDO> {

}
