package com.hotdog.saas.infra.dao;

import com.hotdog.saas.infra.entity.RoleMenuDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 角色菜单表 Mapper 接口
 * </p>
 *
 * @author hotdog
 * @since 2024-12-16 12:41:16
 */
@Mapper
public interface RoleMenuMapper extends BaseMapper<RoleMenuDO> {

}
