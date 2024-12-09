package com.hotdog.saas.application.template;

import com.hotdog.saas.application.entity.request.BaseRequestParam;
import com.hotdog.saas.application.entity.response.BaseResponse;

public interface BizProcessorTemplate<Req extends BaseRequestParam, Resp extends BaseResponse<?>> {

    /**
     * 初始化结果
     */
    Resp initResult();

    /**
     * 执行具体业务
     */
    void doExecute(Req request, Resp response);

    /**
     * 业务执行前置处理
     */
    default void beforeExecute(Req request, Resp response) {
    }

    /**
     * 业务执行后置处理
     */
    default void afterExecute(Req request, Resp response) {
    }

    /**
     * 业务执行模板
     */
    default void execute(Req request, Resp response) {
        this.beforeExecute(request, response);
        this.doExecute(request, response);
        this.afterExecute(request, response);
    }
}
