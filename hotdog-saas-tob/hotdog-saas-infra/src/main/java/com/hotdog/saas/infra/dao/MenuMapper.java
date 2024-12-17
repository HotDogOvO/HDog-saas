package com.hotdog.saas.infra.dao;

import com.hotdog.saas.infra.entity.MenuDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author hotdog
 * @since 2024-12-16 09:45:03
 */
@Mapper
public interface MenuMapper extends BaseMapper<MenuDO> {

}
