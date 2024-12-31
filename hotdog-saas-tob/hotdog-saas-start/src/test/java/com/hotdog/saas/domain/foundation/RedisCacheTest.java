package com.hotdog.saas.domain.foundation;

import com.hotdog.saas.Application;
import com.hotdog.saas.domain.constant.RedisConstants;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@SpringBootTest(classes = Application.class)
public class RedisCacheTest {

    @Autowired
    private RedisCacheService redisCacheService;

    public static final String KEY = RedisConstants.EDUCATION_COURSE_START_TASK_PREFIX;

    @Test
    public void setTest() {
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime now = LocalDateTime.now();
        long timestampMillis = now.atZone(zoneId).toInstant().toEpochMilli();
        redisCacheService.zSet(KEY, "test3", timestampMillis);
    }

    @Test
    public void getTest() {
        // set
        List<Long> test = redisCacheService.zGet(KEY, Long.class);
        System.out.println(test);
    }

    @Test
    public void deleteTest() {
        redisCacheService.zDelete(KEY, "test2");
    }

    @Test
    public void deleteRangeTest(){
        redisCacheService.zDeleteRange(KEY, 0L, 1735612246765L);
    }

}
