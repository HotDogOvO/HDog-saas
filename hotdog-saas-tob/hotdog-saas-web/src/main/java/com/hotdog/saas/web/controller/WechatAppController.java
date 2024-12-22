package com.hotdog.saas.web.controller;

import com.hotdog.saas.application.entity.request.wechat.app.CreateWechatAppRequest;
import com.hotdog.saas.application.entity.request.wechat.app.DeleteWechatAppRequest;
import com.hotdog.saas.application.entity.request.wechat.app.QueryWechatAppRequest;
import com.hotdog.saas.application.entity.request.wechat.app.UpdateWechatAppRequest;
import com.hotdog.saas.application.entity.request.wechat.app.WechatAppPageRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.wechat.app.WechatAppDTO;
import com.hotdog.saas.application.facade.WechatAppFacade;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "小程序管理")
@RestController
@RequestMapping("/api/hotdog/v1/wechat/app")
public class WechatAppController {

    private final WechatAppFacade wechatAppFacade;

    public WechatAppController(WechatAppFacade wechatAppFacade) {
        this.wechatAppFacade = wechatAppFacade;
    }

    @Operation(summary = "创建小程序")
    @PostMapping("/create")
    public BaseResponse<Boolean> createWechatApp(@RequestBody @Validated CreateWechatAppRequest createWechatAppRequest) {
        return wechatAppFacade.createWechatApp(createWechatAppRequest);
    }

    @Operation(summary = "查询小程序分页列表")
    @PostMapping("/list/page")
    public BaseResponse<PageResponseDTO<WechatAppDTO>> wechatAppListPage(@RequestBody @Validated WechatAppPageRequest WechatAppPageRequest) {
        return wechatAppFacade.wechatAppListPage(WechatAppPageRequest);
    }

    @Operation(summary = "查询小程序详情")
    @PostMapping("/detail")
    public BaseResponse<WechatAppDTO> wechatAppDetail(@RequestBody @Validated QueryWechatAppRequest queryWechatAppRequest) {
        return wechatAppFacade.wechatAppDetail(queryWechatAppRequest);
    }

    @Operation(summary = "更新小程序")
    @PostMapping("/update")
    public BaseResponse<Boolean> updateWechatApp(@RequestBody @Validated UpdateWechatAppRequest updateWechatAppRequest) {
        return wechatAppFacade.updateWechatApp(updateWechatAppRequest);
    }

    @Operation(summary = "删除小程序")
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteWechatApp(@RequestBody @Validated DeleteWechatAppRequest deleteWechatAppRequest) {
        return wechatAppFacade.deleteWechatApp(deleteWechatAppRequest);
    }

}
