package com.hotdog.saas.application.facade.impl;

import com.hotdog.saas.application.entity.request.wechat.app.CreateWechatAppRequest;
import com.hotdog.saas.application.entity.request.wechat.app.DeleteWechatAppRequest;
import com.hotdog.saas.application.entity.request.wechat.app.QueryWechatAppRequest;
import com.hotdog.saas.application.entity.request.wechat.app.UpdateWechatAppRequest;
import com.hotdog.saas.application.entity.request.wechat.app.WechatAppPageRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.wechat.app.WechatAppDTO;
import com.hotdog.saas.application.facade.WechatAppFacade;
import com.hotdog.saas.application.processor.BaseProcessor;
import com.hotdog.saas.application.processor.wechat.app.WechatAppCreateProcessor;
import com.hotdog.saas.application.processor.wechat.app.WechatAppDeleteProcessor;
import com.hotdog.saas.application.processor.wechat.app.WechatAppDetailProcessor;
import com.hotdog.saas.application.processor.wechat.app.WechatAppListProcessor;
import com.hotdog.saas.application.processor.wechat.app.WechatAppUpdateProcessor;

import org.springframework.stereotype.Component;

@Component
public class WechatAppFacadeImpl extends BaseProcessor implements WechatAppFacade {

    private final WechatAppCreateProcessor wechatAppCreateProcessor;
    private final WechatAppListProcessor wechatAppListProcessor;
    private final WechatAppDetailProcessor wechatAppDetailProcessor;
    private final WechatAppDeleteProcessor wechatAppDeleteProcessor;
    private final WechatAppUpdateProcessor wechatAppUpdateProcessor;

    public WechatAppFacadeImpl(WechatAppCreateProcessor wechatAppCreateProcessor, WechatAppListProcessor wechatAppListProcessor, WechatAppDetailProcessor wechatAppDetailProcessor, WechatAppDeleteProcessor wechatAppDeleteProcessor, WechatAppUpdateProcessor wechatAppUpdateProcessor) {
        this.wechatAppCreateProcessor = wechatAppCreateProcessor;
        this.wechatAppListProcessor = wechatAppListProcessor;
        this.wechatAppDetailProcessor = wechatAppDetailProcessor;
        this.wechatAppDeleteProcessor = wechatAppDeleteProcessor;
        this.wechatAppUpdateProcessor = wechatAppUpdateProcessor;
    }

    @Override
    public BaseResponse<Boolean> createWechatApp(CreateWechatAppRequest createWechatAppRequest) {
        return this.doBiz(createWechatAppRequest, wechatAppCreateProcessor);
    }

    @Override
    public BaseResponse<PageResponseDTO<WechatAppDTO>> wechatAppListPage(WechatAppPageRequest wechatAppPageRequest) {
        return this.doBiz(wechatAppPageRequest, wechatAppListProcessor);
    }

    @Override
    public BaseResponse<WechatAppDTO> wechatAppDetail(QueryWechatAppRequest queryWechatAppRequest) {
        return this.doBiz(queryWechatAppRequest, wechatAppDetailProcessor);
    }

    @Override
    public BaseResponse<Boolean> updateWechatApp(UpdateWechatAppRequest updateWechatAppRequest) {
        return this.doBiz(updateWechatAppRequest, wechatAppUpdateProcessor);
    }

    @Override
    public BaseResponse<Boolean> deleteWechatApp(DeleteWechatAppRequest deleteWechatAppRequest) {
        return this.doBiz(deleteWechatAppRequest, wechatAppDeleteProcessor);
    }
}
