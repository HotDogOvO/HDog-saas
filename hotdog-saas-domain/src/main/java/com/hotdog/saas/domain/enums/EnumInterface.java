package com.hotdog.saas.domain.enums;

public interface EnumInterface {

    /**
     * 判断枚举是否存在
     * @param code 枚举code
     * @return 是否存在
     */
    Boolean exist(Integer code);

    /**
     * 获取枚举code
     * @return 枚举code
     */
    Integer getCode();

    /**
     * 获取枚举详情
     * @return 枚举详情
     */
    String getDesc();

}
