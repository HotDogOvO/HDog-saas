package com.hotdog.saas.application.processor.wechat.app;

import com.hotdog.saas.application.assembler.WechatAppAssembler;
import com.hotdog.saas.application.entity.request.wechat.app.WechatAppPageRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.wechat.app.WechatAppCardDTO;
import com.hotdog.saas.application.entity.response.wechat.app.WechatAppDTO;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.model.WechatApp;
import com.hotdog.saas.domain.model.page.PageRequest;
import com.hotdog.saas.domain.model.page.PageResponse;

import org.springframework.stereotype.Component;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class WechatAppListProcessor extends AbstractWechatAppProcessor<WechatAppPageRequest, BaseResponse<PageResponseDTO<WechatAppCardDTO>>> {

    @Override
    public BaseResponse<PageResponseDTO<WechatAppCardDTO>> initResult() {
        BaseResponse<PageResponseDTO<WechatAppCardDTO>> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    public void doExecute(WechatAppPageRequest wechatAppPageRequest, BaseResponse<PageResponseDTO<WechatAppCardDTO>> response) {
        wechatAppPageRequest.initPage();
        WechatApp wechatApp = WechatAppAssembler.INSTANCE.convert(wechatAppPageRequest);
        PageRequest pageRequest = reqToPage(wechatAppPageRequest);

        PageResponse<List<WechatApp>> listPageResponse = wechatAppRepository.listPage(wechatApp, pageRequest);

        PageResponseDTO<WechatAppCardDTO> userPageResponseDTO = WechatAppAssembler.INSTANCE.convertPage(listPageResponse);

        response.setData(userPageResponseDTO);
    }

}
