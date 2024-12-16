package com.hotdog.saas.application.entity.request.menu;

import com.hotdog.saas.application.entity.request.BaseRequestParam;
import com.hotdog.saas.domain.enums.MenuEnum;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.exception.BusinessException;

import org.apache.commons.lang3.StringUtils;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BaseMenuRequest extends BaseRequestParam {

    @Schema(description = "权限标识")
    private String permission;

    @NotNull(message = "菜单类型不能为空")
    @Schema(description = "菜单类型（1：菜单 2：按钮）")
    private Integer type;

    @Schema(description = "路由地址")
    private String path;

    @Override
    public void validate() {
        MenuEnum menuEnum = MenuEnum.codeToEnum(this.type);
        switch (menuEnum) {
            case MENU -> {
                if (StringUtils.isEmpty(path)) {
                    throw new BusinessException(ResultCodeEnum.PARAMS_INVALID, "菜单路由地址不能为空");
                }
            }

            case ACTION -> {
                if (StringUtils.isEmpty(permission)) {
                    throw new BusinessException(ResultCodeEnum.PARAMS_INVALID, "权限标识不能为空");
                }
            }

            case UNKNOWN -> throw new BusinessException(ResultCodeEnum.PARAMS_INVALID, "菜单类型非法");
        }
    }
}
