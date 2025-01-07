package com.hotdog.saas.application.entity.request.menu;

import com.hotdog.saas.domain.constant.Constants;
import com.hotdog.saas.domain.enums.menu.MenuEnum;

import org.apache.commons.lang3.StringUtils;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "创建菜单DTO", description = "创建菜单DTO")
public class CreateMenuRequest extends BaseMenuRequest {

    @NotNull(message = "父菜单ID不能为空")
    @Schema(description = "父菜单ID")
    private Long parentId;

    @NotEmpty(message = "权限名称不能为空")
    @Schema(description = "权限名称")
    private String name;

    @NotNull(message = "显示顺序不能为空")
    @Schema(description = "显示顺序")
    private Integer sort;

    @Schema(description = "菜单icon")
    private String icon;

    @Override
    public void validate() {
        super.validate();

        if(StringUtils.isEmpty(icon) && MenuEnum.MENU.getCode().equals(super.getType())){
            this.icon = Constants.DEFAULT_MENU_ICON;
        }
    }
}
