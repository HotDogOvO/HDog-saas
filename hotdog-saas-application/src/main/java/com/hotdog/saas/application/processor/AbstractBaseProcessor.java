package com.hotdog.saas.application.processor;

public abstract class AbstractBaseProcessor {

    /**
     * 检查数据库操作是否成功
     * @param flag 操作条数
     * @return 成功 / 不成功
     */
    protected Boolean checkFlag(Integer flag){
        return flag > 0;
    }
}
