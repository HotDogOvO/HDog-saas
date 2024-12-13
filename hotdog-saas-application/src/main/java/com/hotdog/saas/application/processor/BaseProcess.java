package com.hotdog.saas.application.processor;


import com.hotdog.saas.application.entity.request.BaseRequestParam;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.template.BizProcessorTemplate;
import com.hotdog.saas.domain.constant.Constants;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BaseProcess {

    @Autowired
    private RedissonClient redissonClient;

    /**
     * 业务执行器
     *
     * @param req       请求参数
     * @param processor 业务处理器
     * @param isLock    是否需要分布式锁
     * @param lockKey   锁Key
     * @param <Req>     extends BaseRequestParam
     * @param <Resp>    extends BaseResponse
     * @return BaseResponse
     */
    protected <Req extends BaseRequestParam, Resp extends BaseResponse<?>> Resp doBiz(Req req,
                                                                                      final BizProcessorTemplate<Req, Resp> processor,
                                                                                      Boolean isLock,
                                                                                      Supplier<String> lockKey) {
        // 参数校验
        req.validate();
        // 初始化返回值
        final Resp result = processor.initResult();

        // 加锁
        RLock lock = null;
        if (Objects.nonNull(isLock) && isLock && Objects.nonNull(lockKey)) {
            lock = this.redissonClient.getLock(lockKey.get());
        }

        try {
            if (Objects.nonNull(lock)) {
                lock.lock(Constants.LOCK_TIME_SECONDS, TimeUnit.SECONDS);
            }
            // 业务执行
            processor.execute(req, result);
        } finally {
            if (Objects.nonNull(lock) && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
        return result;
    }
}
