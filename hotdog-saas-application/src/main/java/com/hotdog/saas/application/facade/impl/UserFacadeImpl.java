package com.hotdog.saas.application.facade.impl;

import com.hotdog.saas.application.entity.request.user.*;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.user.UserDTO;
import com.hotdog.saas.application.facade.UserFacade;
import com.hotdog.saas.application.processor.BaseProcess;
import com.hotdog.saas.application.processor.user.UserChangePasswordProcess;
import com.hotdog.saas.application.processor.user.UserCreateProcess;
import com.hotdog.saas.application.processor.user.UserDeleteProcess;
import com.hotdog.saas.application.processor.user.UserDetailProcess;
import com.hotdog.saas.application.processor.user.UserListProcess;
import com.hotdog.saas.application.processor.user.UserUpdateProcess;

import org.springframework.stereotype.Component;

@Component
public class UserFacadeImpl extends BaseProcess implements UserFacade {

    private final UserCreateProcess userCreateProcess;
    private final UserListProcess userListProcess;
    private final UserDetailProcess userDetailProcess;
    private final UserUpdateProcess userUpdateProcess;
    private final UserChangePasswordProcess userChangePasswordProcess;
    private final UserDeleteProcess userDeleteProcess;

    public UserFacadeImpl(UserCreateProcess userCreateProcess, UserListProcess userListProcess, UserDetailProcess userDetailProcess, UserUpdateProcess userUpdateProcess, UserChangePasswordProcess userChangePasswordProcess, UserDeleteProcess userDeleteProcess) {
        this.userCreateProcess = userCreateProcess;
        this.userListProcess = userListProcess;
        this.userDetailProcess = userDetailProcess;
        this.userUpdateProcess = userUpdateProcess;
        this.userChangePasswordProcess = userChangePasswordProcess;
        this.userDeleteProcess = userDeleteProcess;
    }

    @Override
    public BaseResponse<Boolean> createUser(CreateUserRequest createUserRequest) {
        return this.doBiz(createUserRequest, userCreateProcess, false, () -> "");
    }

    @Override
    public BaseResponse<PageResponseDTO<UserDTO>> userListPage(UserPageRequest userPageRequest) {
        return this.doBiz(userPageRequest, userListProcess, false, () -> "");
    }

    @Override
    public BaseResponse<UserDTO> userDetail(QueryUserRequest queryUserRequest) {
        return this.doBiz(queryUserRequest, userDetailProcess, false, () -> "");
    }

    @Override
    public BaseResponse<Boolean> updateUser(UpdateUserRequest updateUserRequest) {
        return this.doBiz(updateUserRequest, userUpdateProcess, false, () -> "");
    }

    @Override
    public BaseResponse<Boolean> changePassword(ChangePasswordRequest changePasswordRequest) {
        return this.doBiz(changePasswordRequest, userChangePasswordProcess, false, () -> "");
    }

    @Override
    public BaseResponse<Boolean> deleteUser(DeleteUserRequest deleteUserRequest) {
        return this.doBiz(deleteUserRequest, userDeleteProcess, false, () -> "");
    }
}
