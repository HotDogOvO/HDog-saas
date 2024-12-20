package com.hotdog.saas.application.processor.wechat.app;

import com.hotdog.saas.application.assembler.UserAssembler;
import com.hotdog.saas.application.assembler.WechatAppAssembler;
import com.hotdog.saas.application.entity.request.user.UpdateUserRequest;
import com.hotdog.saas.application.entity.request.wechat.app.UpdateWechatAppRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.model.User;
import com.hotdog.saas.domain.model.WechatApp;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class WechatAppUpdateProcessor extends AbstractWechatAppProcessor<UpdateWechatAppRequest, BaseResponse<Boolean>> {

    @Override
    public BaseResponse<Boolean> initResult() {
        BaseResponse<Boolean> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doExecute(UpdateWechatAppRequest request, BaseResponse<Boolean> response) {
        super.exists(request.getId());
        // 保存
        WechatApp wechatApp = WechatAppAssembler.INSTANCE.convert(request);
        Integer modifyFlag = wechatAppRepository.modify(wechatApp);
        response.setData(checkFlag(modifyFlag));
    }

}
