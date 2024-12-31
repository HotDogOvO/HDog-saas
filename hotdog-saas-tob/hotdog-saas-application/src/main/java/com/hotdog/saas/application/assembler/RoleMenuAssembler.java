package com.hotdog.saas.application.assembler;

import com.hotdog.saas.application.entity.request.role.PermissionRoleRequest;
import com.hotdog.saas.domain.model.RoleMenu;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleMenuAssembler {

    RoleMenuAssembler INSTANCE = Mappers.getMapper(RoleMenuAssembler.class);

    @Mappings(
            @Mapping(source = "id", target = "roleId")
    )
    RoleMenu convert(PermissionRoleRequest permissionRoleRequest);
}
