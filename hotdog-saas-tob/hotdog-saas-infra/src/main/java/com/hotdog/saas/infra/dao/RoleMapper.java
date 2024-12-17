package com.hotdog.saas.infra.dao;

import com.hotdog.saas.infra.entity.RoleDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 角色信息表 Mapper 接口
 * </p>
 *
 * @author hotdog
 * @since 2024-12-14 21:40:04
 */
@Mapper
public interface RoleMapper extends BaseMapper<RoleDO> {

}
