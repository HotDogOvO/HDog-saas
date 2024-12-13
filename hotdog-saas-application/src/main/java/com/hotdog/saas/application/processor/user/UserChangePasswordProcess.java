package com.hotdog.saas.application.processor.user;

import com.hotdog.saas.application.assembler.UserAssembler;
import com.hotdog.saas.application.entity.request.user.ChangePasswordRequest;
import com.hotdog.saas.application.entity.request.user.UpdateUserRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.exception.BusinessException;
import com.hotdog.saas.domain.model.User;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserChangePasswordProcess extends AbstractUserProcessor<ChangePasswordRequest, BaseResponse<Boolean>> {

    @Override
    public BaseResponse<Boolean> initResult() {
        BaseResponse<Boolean> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doExecute(ChangePasswordRequest request, BaseResponse<Boolean> response) {
        // 校验用户是否存在
        Long userId = request.getId();
        super.exists(userId);
        User user = userRepository.findById(userId);

        // 校验密码是否一致
        Boolean checkFlag = user.checkPassword(request.getOldPassword());
        if(!checkFlag) {
            throw new BusinessException("原密码不一致，请检查后重试");
        }

        // 生成新密码
        user.generatorPassword(request.getNewPassword());

        Integer modifyFlag = userRepository.modify(user);
        response.setData(checkFlag(modifyFlag));
    }

}
