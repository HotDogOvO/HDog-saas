package com.hotdog.saas.application.assembler;

import com.hotdog.saas.application.entity.request.tenate.CreateTenantRequest;
import com.hotdog.saas.application.entity.request.tenate.TenantPageRequest;
import com.hotdog.saas.application.entity.request.tenate.UpdateTenantRequest;
import com.hotdog.saas.application.entity.request.user.CreateUserRequest;
import com.hotdog.saas.application.entity.request.user.UpdateUserRequest;
import com.hotdog.saas.application.entity.request.user.UserPageRequest;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.tenate.TenantDTO;
import com.hotdog.saas.domain.model.Tenant;
import com.hotdog.saas.domain.model.User;
import com.hotdog.saas.domain.model.page.PageResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserAssembler {

    UserAssembler INSTANCE = Mappers.getMapper(UserAssembler.class);

    User convert(CreateUserRequest createUserRequest);

    User convert(UserPageRequest userPageRequest);

    User convert(UpdateUserRequest updateUserRequest);

//    PageResponseDTO<TenantDTO> convertPage(PageResponse<List<Tenant>> pageResponse);
//
//    TenantDTO convertToDTO(Tenant tenant);
}
