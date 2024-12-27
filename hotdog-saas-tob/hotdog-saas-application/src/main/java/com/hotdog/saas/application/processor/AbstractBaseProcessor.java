package com.hotdog.saas.application.processor;

import com.hotdog.saas.domain.constant.RedisConstants;
import com.hotdog.saas.domain.foundation.AuthService;
import com.hotdog.saas.domain.foundation.RedisCacheService;
import com.hotdog.saas.domain.model.User;
import com.hotdog.saas.domain.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.List;

import io.micrometer.common.util.StringUtils;

public abstract class AbstractBaseProcessor {

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected RedisCacheService redisCacheService;

    @Autowired
    protected AuthService authService;

    /**
     * 检查数据库操作是否成功
     *
     * @param flag 操作条数
     * @return 成功 / 不成功
     */
    protected Boolean checkFlag(Integer flag) {
        return flag > 0;
    }

    protected void removeToken(List<Long> userIdList) {
        if (CollectionUtils.isEmpty(userIdList)) {
            return;
        }
        userIdList.forEach(userId -> {
            User user = userRepository.findById(userId);
            removeToken(user.getUsername());
        });
    }

    protected void removeToken(String username) {
        if (StringUtils.isNotEmpty(username)) {
            return;
        }
        redisCacheService.delete(RedisConstants.getUserKey(username));
    }

    protected Long getTenantId(){
        return authService.extractTenantId();
    }

}
