package com.hotdog.saas.infra.feign.external.wechat;

import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.enums.feign.WechatAppResultCode;
import com.hotdog.saas.domain.exception.BusinessException;
import com.hotdog.saas.domain.model.feign.response.wechat.BaseWechatResponseParam;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

public interface BaseWechatFeignClient {

    default ResultCodeEnum defaultResultCode() {
        return ResultCodeEnum.FEIGN_CALL_FAIL;
    }

    default Optional<? extends BaseWechatResponseParam> request(Supplier<? extends BaseWechatResponseParam> request) {
        var response = request.get();

        if (Objects.isNull(response)) {
            throw new BusinessException(defaultResultCode());
        }
        this.checkFail(response);
        return Optional.of(response);
    }

    default void checkFail(BaseWechatResponseParam response) {
        if (!response.isSuccess()) {
            WechatAppResultCode wechatAppResultCode = WechatAppResultCode.codeToEnum(response.getErrcode());
            throw new BusinessException(defaultResultCode(), wechatAppResultCode.getMessage());
        }
    }
}
