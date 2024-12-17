package com.hotdog.saas.application.facade.impl;

import com.hotdog.saas.application.entity.request.user.*;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.user.UserDTO;
import com.hotdog.saas.application.facade.UserFacade;
import com.hotdog.saas.application.processor.BaseProcessor;
import com.hotdog.saas.application.processor.user.*;

import org.springframework.stereotype.Component;

@Component
public class UserFacadeImpl extends BaseProcessor implements UserFacade {

    private final UserCreateProcessor userCreateProcessor;
    private final UserListProcessor userListProcessor;
    private final UserDetailProcessor userDetailProcessor;
    private final UserUpdateProcessor userUpdateProcessor;
    private final UserChangePasswordProcessor userChangePasswordProcess;
    private final UserPermissionProcessor userPermissionProcessor;
    private final UserDeleteProcessor userDeleteProcessor;

    public UserFacadeImpl(UserCreateProcessor userCreateProcessor, UserListProcessor userListProcessor, UserDetailProcessor userDetailProcessor, UserUpdateProcessor userUpdateProcessor, UserChangePasswordProcessor userChangePasswordProcess, UserPermissionProcessor userPermissionProcessor, UserDeleteProcessor userDeleteProcessor) {
        this.userCreateProcessor = userCreateProcessor;
        this.userListProcessor = userListProcessor;
        this.userDetailProcessor = userDetailProcessor;
        this.userUpdateProcessor = userUpdateProcessor;
        this.userChangePasswordProcess = userChangePasswordProcess;
        this.userPermissionProcessor = userPermissionProcessor;
        this.userDeleteProcessor = userDeleteProcessor;
    }

    @Override
    public BaseResponse<Boolean> createUser(CreateUserRequest createUserRequest) {
        return this.doBiz(createUserRequest, userCreateProcessor, false, () -> "");
    }

    @Override
    public BaseResponse<PageResponseDTO<UserDTO>> userListPage(UserPageRequest userPageRequest) {
        return this.doBiz(userPageRequest, userListProcessor, false, () -> "");
    }

    @Override
    public BaseResponse<UserDTO> userDetail(QueryUserRequest queryUserRequest) {
        return this.doBiz(queryUserRequest, userDetailProcessor, false, () -> "");
    }

    @Override
    public BaseResponse<Boolean> updateUser(UpdateUserRequest updateUserRequest) {
        return this.doBiz(updateUserRequest, userUpdateProcessor, false, () -> "");
    }

    @Override
    public BaseResponse<Boolean> changePassword(ChangePasswordRequest changePasswordRequest) {
        return this.doBiz(changePasswordRequest, userChangePasswordProcess, false, () -> "");
    }

    @Override
    public BaseResponse<Boolean> userPermission(PermissionUserRequest permissionUserRequest) {
        return this.doBiz(permissionUserRequest, userPermissionProcessor, false, () -> "");
    }

    @Override
    public BaseResponse<Boolean> deleteUser(DeleteUserRequest deleteUserRequest) {
        return this.doBiz(deleteUserRequest, userDeleteProcessor, false, () -> "");
    }
}
