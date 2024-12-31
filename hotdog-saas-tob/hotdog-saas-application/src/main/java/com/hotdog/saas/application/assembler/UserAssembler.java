package com.hotdog.saas.application.assembler;

import com.hotdog.saas.application.entity.request.user.CreateUserRequest;
import com.hotdog.saas.application.entity.request.user.UpdateUserRequest;
import com.hotdog.saas.application.entity.request.user.UserPageRequest;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.user.UserDTO;
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

    PageResponseDTO<UserDTO> convertPage(PageResponse<List<User>> pageResponse);

    UserDTO convertToDTO(User user);
}
