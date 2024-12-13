package com.hotdog.saas.application.processor.user;

import com.hotdog.saas.application.assembler.UserAssembler;
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
public class UserListProcessor extends AbstractUserProcessor<UserPageRequest, BaseResponse<PageResponseDTO<UserDTO>>> {

    @Override
    public BaseResponse<PageResponseDTO<UserDTO>> initResult() {
        BaseResponse<PageResponseDTO<UserDTO>> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    public void doExecute(UserPageRequest userPageRequest, BaseResponse<PageResponseDTO<UserDTO>> response) {
        userPageRequest.initPage();
        User user = UserAssembler.INSTANCE.convert(userPageRequest);
        PageRequest pageRequest = reqToPage(userPageRequest);

        PageResponse<List<User>> listPageResponse = userRepository.listPage(user, pageRequest);

        PageResponseDTO<UserDTO> userPageResponseDTO = UserAssembler.INSTANCE.convertPage(listPageResponse);
        response.setData(userPageResponseDTO);
    }

}
