package com.hotdog.saas.application.processor.user;

import com.hotdog.saas.application.assembler.UserAssembler;
import com.hotdog.saas.application.entity.request.user.QueryUserRequest;
import com.hotdog.saas.application.entity.request.user.UserPageRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.user.UserDTO;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.model.User;
import com.hotdog.saas.domain.model.page.PageRequest;
import com.hotdog.saas.domain.model.page.PageResponse;

import org.springframework.stereotype.Component;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserDetailProcess extends AbstractUserProcessor<QueryUserRequest, BaseResponse<UserDTO>> {

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
        response.setData(userDTO);
    }

}
