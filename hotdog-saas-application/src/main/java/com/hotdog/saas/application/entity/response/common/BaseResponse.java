package com.hotdog.saas.application.entity.response.common;

import java.io.Serial;
import java.io.Serializable;

import lombok.Data;

@Data
public class BaseResponse<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    private String code;

    /**
     * 返回数据
     */
    private T data;

    /**
     * 错误信息
     */
    private String message;

    /**
     * 时间戳
     */
    private Long timestamp;

    /**
     * 请求url
     */
    private String path;

    public BaseResponse() {
        this.code = ResultCodeEnum.SUCCESS.getCode();
        this.message = ResultCodeEnum.SUCCESS.getMessage();
    }

    public BaseResponse(ResultCodeEnum resultCodeEnum) {
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
    }

}
