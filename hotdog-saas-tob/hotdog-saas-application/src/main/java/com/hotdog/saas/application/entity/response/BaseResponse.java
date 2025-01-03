package com.hotdog.saas.application.entity.response;

import com.hotdog.saas.domain.enums.ResultCodeEnum;

import java.io.Serial;
import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "全局统一返回值", description = "全局统一返回值")
public class BaseResponse<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "状态码")
    private String code;

    @Schema(description = "返回数据")
    private T data;

    @Schema(description = "请求信息")
    private String message;

    @Schema(description = "响应时间戳")
    private Long timestamp;

    @Schema(description = "请求url")
    private String path;

    public BaseResponse() {
        this.code = ResultCodeEnum.SUCCESS.getCode();
        this.message = ResultCodeEnum.SUCCESS.getMessage();
    }

    public BaseResponse(ResultCodeEnum resultCodeEnum) {
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
    }

    public BaseResponse(ResultCodeEnum resultCodeEnum, String message) {
        this.code = resultCodeEnum.getCode();
        this.message = message;
    }

}
