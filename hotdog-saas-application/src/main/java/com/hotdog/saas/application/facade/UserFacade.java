package com.hotdog.saas.application.facade;

import com.hotdog.saas.application.entity.request.user.*;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.user.UserDTO;

public interface UserFacade {
    BaseResponse<Boolean> createUser(CreateUserRequest createUserRequest);

    BaseResponse<PageResponseDTO<UserDTO>> userListPage(UserPageRequest userPageRequest);

    BaseResponse<UserDTO> userDetail(QueryUserRequest queryUserRequest);

    BaseResponse<Boolean> updateUser(UpdateUserRequest updateUserRequest);

    BaseResponse<Boolean> changePassword(ChangePasswordRequest changePasswordRequest);

    BaseResponse<Boolean> deleteUser(DeleteUserRequest deleteUserRequest);

}
