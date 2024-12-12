package com.hotdog.saas.web.controller;

import com.hotdog.saas.application.entity.request.user.CreateUserRequest;
import com.hotdog.saas.application.entity.request.user.DeleteUserRequest;
import com.hotdog.saas.application.entity.request.user.QueryUserRequest;
import com.hotdog.saas.application.entity.request.user.UpdateUserRequest;
import com.hotdog.saas.application.entity.request.user.UserPageRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.user.UserDTO;
import com.hotdog.saas.application.facade.UserFacade;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "用户管理")
@RestController
@RequestMapping("/api/hotdog/v1/user")
public class UserController {

    private final UserFacade userFacade;

    public UserController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @Operation(summary = "创建用户")
    @PostMapping("/create")
    public BaseResponse<Boolean> createUser(@RequestBody @Validated CreateUserRequest createUserRequest) {
        return userFacade.createUser(createUserRequest);
    }

    @Operation(summary = "查询用户分页列表")
    @PostMapping("/list/page")
    public BaseResponse<PageResponseDTO<UserDTO>> userListPage(@RequestBody @Validated UserPageRequest userPageRequest) {
        return userFacade.userListPage(userPageRequest);
    }

    @Operation(summary = "查询用户详情")
    @PostMapping("/detail")
    public BaseResponse<UserDTO> userDetail(@RequestBody @Validated QueryUserRequest queryUserRequest) {
        return userFacade.userDetail(queryUserRequest);
    }

    @Operation(summary = "更新用户")
    @PostMapping("/update")
    public BaseResponse<Boolean> updateUser(@RequestBody @Validated UpdateUserRequest updateUserRequest) {
        return userFacade.updateUser(updateUserRequest);
    }

    @Operation(summary = "删除用户")
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteUser(@RequestBody @Validated DeleteUserRequest deleteUserRequest) {
        return userFacade.deleteUser(deleteUserRequest);
    }

}
