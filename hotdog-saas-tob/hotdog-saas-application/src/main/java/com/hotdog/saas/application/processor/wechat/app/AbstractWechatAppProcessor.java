package com.hotdog.saas.application.processor.wechat.app;

import com.hotdog.saas.application.entity.request.BaseRequestParam;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.processor.wechat.AbstractWechatProcessor;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.exception.BusinessException;
import com.hotdog.saas.domain.repository.WechatAppRepository;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

import io.micrometer.common.util.StringUtils;

public abstract class AbstractWechatAppProcessor<Req extends BaseRequestParam, Resp extends BaseResponse<?>> extends AbstractWechatProcessor<Req, Resp> {

    @Autowired
    protected WechatAppRepository wechatAppRepository;

    protected void existsByWechatAppId(String wechatAppId) {
        if (StringUtils.isEmpty(wechatAppId)) {
            return;
        }
        Long appIdCount = wechatAppRepository.existsByWechatAppId(wechatAppId);
        if (appIdCount > 0) {
            throw new BusinessException(ResultCodeEnum.FAIL, "小程序AppId重复");
        }
    }

    protected void exists(Long id) {
        if (Objects.isNull(id)) {
            return;
        }
        Long count = wechatAppRepository.exists(id);
        if (count == 0) {
            throw new BusinessException(ResultCodeEnum.FAIL, "小程序不存在");
        }
    }

}
