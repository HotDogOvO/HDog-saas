package com.hotdog.saas.application.assembler;

import com.hotdog.saas.application.entity.request.user.PermissionUserRequest;
import com.hotdog.saas.domain.model.UserRole;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserRoleAssembler {

    UserRoleAssembler INSTANCE = Mappers.getMapper(UserRoleAssembler.class);

    @Mappings(
            @Mapping(source = "id", target = "userId")
    )
    UserRole convert(PermissionUserRequest permissionUserRequest);
}
