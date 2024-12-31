package com.hotdog.saas.application.processor.wechat.app;

import com.hotdog.saas.application.entity.request.wechat.app.DeleteWechatAppRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.domain.enums.ResultCodeEnum;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class WechatAppDeleteProcessor extends AbstractWechatAppProcessor<DeleteWechatAppRequest, BaseResponse<Boolean>> {

    @Override
    public BaseResponse<Boolean> initResult() {
        BaseResponse<Boolean> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doExecute(DeleteWechatAppRequest request, BaseResponse<Boolean> response) {
        super.exists(request.getId());
        Integer removeFlag = wechatAppRepository.remove(request.getId(), request.getOperator());
        response.setData(checkFlag(removeFlag));
    }

}
