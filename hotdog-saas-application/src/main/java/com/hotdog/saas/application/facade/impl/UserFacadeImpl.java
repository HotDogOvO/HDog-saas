package com.hotdog.saas.application.facade.impl;

import com.hotdog.saas.application.entity.request.user.CreateUserRequest;
import com.hotdog.saas.application.entity.request.user.DeleteUserRequest;
import com.hotdog.saas.application.entity.request.user.QueryUserRequest;
import com.hotdog.saas.application.entity.request.user.UpdateUserRequest;
import com.hotdog.saas.application.entity.request.user.UserPageRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.user.UserDTO;
import com.hotdog.saas.application.facade.UserFacade;

import org.springframework.stereotype.Component;

@Component
public class UserFacadeImpl implements UserFacade {
    @Override
    public BaseResponse<Boolean> createUser(CreateUserRequest createUserRequest) {
        return null;
    }

    @Override
    public BaseResponse<PageResponseDTO<UserDTO>> userListPage(UserPageRequest userPageRequest) {
        return null;
    }

    @Override
    public BaseResponse<UserDTO> userDetail(QueryUserRequest queryUserRequest) {
        return null;
    }

    @Override
    public BaseResponse<Boolean> updateUser(UpdateUserRequest updateUserRequest) {
        return null;
    }

    @Override
    public BaseResponse<Boolean> deleteUser(DeleteUserRequest deleteUserRequest) {
        return null;
    }
}
