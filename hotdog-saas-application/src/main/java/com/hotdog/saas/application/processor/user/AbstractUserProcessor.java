package com.hotdog.saas.application.processor.user;

import com.hotdog.saas.application.entity.request.BaseRequestParam;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.processor.AbstractBaseProcess;
import com.hotdog.saas.application.template.BizProcessorTemplate;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.exception.BusinessException;
import com.hotdog.saas.domain.repository.TenantRepository;
import com.hotdog.saas.domain.repository.UserRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

public abstract class AbstractUserProcessor<Req extends BaseRequestParam, Resp extends BaseResponse<?>> extends AbstractBaseProcess implements BizProcessorTemplate<Req, Resp> {

    @Autowired
    protected UserRepository userRepository;

    protected void existsByUsername(String username) {
        if (StringUtils.isEmpty(username)) {
            return;
        }
        Long nameCount = userRepository.existsByUsername(username);
        if (nameCount > 0) {
            throw new BusinessException(ResultCodeEnum.FAIL, "用户名重复");
        }
    }

    protected void exists(Long id) {
        if (Objects.isNull(id)) {
            return;
        }
        Long count = userRepository.exists(id);
        if (count == 0) {
            throw new BusinessException(ResultCodeEnum.FAIL, "用户不存在");
        }
    }

}
