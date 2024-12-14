package com.hotdog.saas.application.entity.request.role;

import com.hotdog.saas.application.entity.request.PageRequestParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "角色分页DTO", description = "角色分页DTO")
public class RolePageRequest extends PageRequestParam {

}
