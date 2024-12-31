package com.hotdog.saas.application.processor.wechat.app;

import com.hotdog.saas.application.assembler.WechatAppAssembler;
import com.hotdog.saas.application.entity.request.wechat.app.CreateWechatAppRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.model.WechatApp;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class WechatAppCreateProcessor extends AbstractWechatAppProcessor<CreateWechatAppRequest, BaseResponse<Boolean>> {

    @Override
    public BaseResponse<Boolean> initResult() {
        BaseResponse<Boolean> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doExecute(CreateWechatAppRequest request, BaseResponse<Boolean> response) {
        // 校验用户名是否存在
        super.existsByWechatAppId(request.getWechatAppId());
        // 保存
        WechatApp wechatApp = WechatAppAssembler.INSTANCE.convert(request);
        wechatAppRepository.save(wechatApp);
        response.setData(Boolean.TRUE);
    }

}
