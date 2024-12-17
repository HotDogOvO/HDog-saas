package com.hotdog.saas.application.entity.request.menu;

import com.hotdog.saas.application.entity.request.BaseRequestParam;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "菜单查询DTO", description = "菜单查询DTO")
public class MenuTreeRequest extends BaseRequestParam {

    @Override
    public void validate() {

    }
}
