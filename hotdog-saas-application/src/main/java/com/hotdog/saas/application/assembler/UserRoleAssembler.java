package com.hotdog.saas.application.assembler;

import com.hotdog.saas.application.entity.request.user.CreateUserRequest;
import com.hotdog.saas.application.entity.request.user.PermissionUserRequest;
import com.hotdog.saas.application.entity.request.user.UpdateUserRequest;
import com.hotdog.saas.application.entity.request.user.UserPageRequest;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.user.UserDTO;
import com.hotdog.saas.domain.model.User;
import com.hotdog.saas.domain.model.UserRole;
import com.hotdog.saas.domain.model.page.PageResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserRoleAssembler {

    UserRoleAssembler INSTANCE = Mappers.getMapper(UserRoleAssembler.class);

    @Mappings(
            @Mapping(source = "id", target = "userId")
    )
    UserRole convert(PermissionUserRequest permissionUserRequest);
}
