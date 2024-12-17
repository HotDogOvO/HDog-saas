package com.hotdog.saas.domain.utils;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.exception.BusinessException;

import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConcurrentUtils {

    public static void asyncRunJoin(List<Runnable> tasks) {
        if (CollectionUtils.isEmpty(tasks)) {
            return;
        }
        CompletableFuture<?>[] futures = tasks.stream()
                .map(task -> CompletableFuture.runAsync(task)
                        .exceptionally(e -> {
                            log.error("并发工具类执行异常，{}", e.getMessage(), e);
                            throw new BusinessException(ResultCodeEnum.FAIL, e.getMessage());
                        }))
                .toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(futures).join();
    }

    public static void async(Runnable task) {
        CompletableFuture.runAsync(task, SystemThreadPool.getExecutor())
                .exceptionally(e -> {
                    log.error("并发工具类执行异常: {}", e.getMessage(), e);
                    throw new BusinessException(ResultCodeEnum.FAIL, e.getMessage());
                });
    }

    public static <T> CompletableFuture<T> async(Supplier<T> result) {
        return CompletableFuture.supplyAsync(result, SystemThreadPool.getExecutor())
                .exceptionally(e -> {
                    log.error("并发工具类执行异常: {}", e.getMessage(), e);
                    throw new BusinessException(ResultCodeEnum.FAIL, e.getMessage());
                });
    }

    private static class SystemThreadPool {
        private static final ExecutorService SYSTEM_EXECUTOR = new ThreadPoolExecutor(
                5,
                40,
                10L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024),
                new ThreadFactoryBuilder().setNameFormat("system-thread-pool-%d").build(),
                new ThreadPoolExecutor.AbortPolicy());

        public static ExecutorService getExecutor() {
            return SYSTEM_EXECUTOR;
        }
    }
}
