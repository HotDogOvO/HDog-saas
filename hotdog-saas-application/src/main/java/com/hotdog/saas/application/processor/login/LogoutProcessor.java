package com.hotdog.saas.application.processor.login;

import com.hotdog.saas.application.entity.request.login.LogoutRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.login.LoginDTO;
import com.hotdog.saas.domain.cache.RedisCacheService;
import com.hotdog.saas.domain.constant.RedisConstants;
import com.hotdog.saas.domain.enums.ResultCodeEnum;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LogoutProcessor extends AbstractLoginProcessor<LogoutRequest, BaseResponse<Boolean>>{

    private final RedisCacheService redisCacheService;

    public LogoutProcessor(RedisCacheService redisCacheService) {
        this.redisCacheService = redisCacheService;
    }

    @Override
    public BaseResponse<Boolean> initResult() {
        BaseResponse<Boolean> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    public void doExecute(LogoutRequest request, BaseResponse<Boolean> response) {
        // todo 校验
        redisCacheService.delete(RedisConstants.getUserKey(request.getToken()));
        response.setData(Boolean.TRUE);
    }
}
