package com.hotdog.saas.application.facade;

import com.hotdog.saas.application.entity.request.user.CreateUserRequest;
import com.hotdog.saas.application.entity.request.user.DeleteUserRequest;
import com.hotdog.saas.application.entity.request.user.QueryUserRequest;
import com.hotdog.saas.application.entity.request.user.UpdateUserRequest;
import com.hotdog.saas.application.entity.request.user.UserPageRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.user.UserDTO;

public interface UserFacade {
    BaseResponse<Boolean> createUser(CreateUserRequest createUserRequest);

    BaseResponse<PageResponseDTO<UserDTO>> userListPage(UserPageRequest userPageRequest);

    BaseResponse<UserDTO> userDetail(QueryUserRequest queryUserRequest);

    BaseResponse<Boolean> updateUser(UpdateUserRequest updateUserRequest);

    BaseResponse<Boolean> deleteUser(DeleteUserRequest deleteUserRequest);

}
