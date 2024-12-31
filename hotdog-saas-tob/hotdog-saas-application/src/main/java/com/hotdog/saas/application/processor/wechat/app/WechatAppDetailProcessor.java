package com.hotdog.saas.application.processor.wechat.app;

import com.hotdog.saas.application.assembler.WechatAppAssembler;
import com.hotdog.saas.application.entity.request.wechat.app.QueryWechatAppRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.wechat.app.WechatAppDTO;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.model.WechatApp;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class WechatAppDetailProcessor extends AbstractWechatAppProcessor<QueryWechatAppRequest, BaseResponse<WechatAppDTO>> {

    @Override
    public BaseResponse<WechatAppDTO> initResult() {
        BaseResponse<WechatAppDTO> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    public void doExecute(QueryWechatAppRequest queryWechatAppRequest, BaseResponse<WechatAppDTO> response) {
        Long userId = queryWechatAppRequest.getId();
        super.exists(userId);
        WechatApp wechatApp = wechatAppRepository.findById(userId);
        WechatAppDTO wechatAppDTO = WechatAppAssembler.INSTANCE.convertToDTO(wechatApp);
        response.setData(wechatAppDTO);
    }

}
