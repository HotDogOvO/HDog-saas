package com.hotdog.saas.matrix.domain.enums;

public interface EnumInterface<T> {

    /**
     * 判断枚举是否存在
     * @param t 枚举code
     * @return 是否存在
     */
//    Boolean exist(Integer code);
    Boolean exist(T t);

    /**
     * 获取枚举code
     * @return 枚举code
     */
    T getCode();

    /**
     * 获取枚举详情
     * @return 枚举详情
     */
    String getDesc();

}
