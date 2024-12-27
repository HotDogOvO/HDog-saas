package com.hotdog.saas.application.processor.user;

import com.hotdog.saas.application.assembler.UserAssembler;
import com.hotdog.saas.application.entity.request.user.QueryUserRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.user.UserDTO;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.model.Role;
import com.hotdog.saas.domain.model.User;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Component
public class UserDetailProcessor extends AbstractUserProcessor<QueryUserRequest, BaseResponse<UserDTO>> {

    @Override
    public BaseResponse<UserDTO> initResult() {
        BaseResponse<UserDTO> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    public void doExecute(QueryUserRequest queryUserRequest, BaseResponse<UserDTO> response) {
        Long userId = queryUserRequest.getId();
        super.exists(userId);
        User user = userRepository.findById(userId);
        UserDTO userDTO = UserAssembler.INSTANCE.convertToDTO(user);

        // 查询用户角色
        List<Role> userRoleList = super.findUserRole(userId);
        super.setUserRole(userRoleList, userDTO);

        response.setData(userDTO);
    }

}
