package com.hotdog.saas.domain.model.feign.request.wechat;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BaseWechatRequestParam {

    private String accessToken;

}
