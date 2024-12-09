package com.hotdog.saas.domain.model;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SystemProperties {

    private Integer id;

    /**
     * 配置key
     */
    private String name;

    /**
     * 配置value
     */
    private String value;

    /**
     * 状态（0正常 1停用）
     */
    private Boolean status;

    /**
     * 是否删除（0 正常 1 删除）
     */
    private Boolean deleted;

}
