package com.hotdog.saas.application.facade;

import com.hotdog.saas.application.entity.request.wechat.app.CreateWechatAppRequest;
import com.hotdog.saas.application.entity.request.wechat.app.DeleteWechatAppRequest;
import com.hotdog.saas.application.entity.request.wechat.app.QueryWechatAppRequest;
import com.hotdog.saas.application.entity.request.wechat.app.UpdateWechatAppRequest;
import com.hotdog.saas.application.entity.request.wechat.app.WechatAppPageRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.wechat.app.WechatAppDTO;

public interface WechatAppFacade {

    BaseResponse<Boolean> createWechatApp(CreateWechatAppRequest createWechatAppRequest);

    BaseResponse<PageResponseDTO<WechatAppDTO>> wechatAppListPage(WechatAppPageRequest WechatAppPageRequest);

    BaseResponse<WechatAppDTO> wechatAppDetail(QueryWechatAppRequest queryWechatAppRequest);

    BaseResponse<Boolean> updateWechatApp(UpdateWechatAppRequest updateWechatAppRequest);

    BaseResponse<Boolean> deleteWechatApp(DeleteWechatAppRequest deleteWechatAppRequest);
    
}
