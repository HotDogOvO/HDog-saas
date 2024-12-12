package com.hotdog.saas.application.entity.request.user;

import com.hotdog.saas.application.entity.request.PageRequestParam;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "租户分页DTO", description = "租户分页DTO")
public class UserPageRequest extends PageRequestParam {

}
