package com.hotdog.saas.domain.utils;

import com.hotdog.saas.domain.enums.wechat.app.BusinessTypeEnum;

/**
 * 生成业务单号工具类
 *
 * @author hotdog
 * @date 2024/12/24 17:41
 */
public class BusinessNoUtils {

    /**
     * 生成22位的业务编号
     * <li>业务前缀（2位）+ 雪花ID（20位）</li>
     * <li>线程安全</li>
     *
     * @param businessType 业务类型
     * @return 业务编号
     */
    public static synchronized String generateBusinessNo(BusinessTypeEnum businessType) {
        String bizPrefix = businessType.getBusinessNoPrefix();
        return bizPrefix + SnowFlakeIdGenerator.nextId();
    }

}