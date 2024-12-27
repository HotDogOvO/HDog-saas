package com.hotdog.saas.application.processor.role;

import com.hotdog.saas.application.entity.request.BaseRequestParam;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.processor.AbstractBaseProcessor;
import com.hotdog.saas.application.template.BizProcessorTemplate;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.exception.BusinessException;
import com.hotdog.saas.domain.repository.RoleRepository;
import com.hotdog.saas.domain.repository.UserRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

public abstract class AbstractRoleProcessor<Req extends BaseRequestParam, Resp extends BaseResponse<?>> extends AbstractBaseProcessor implements BizProcessorTemplate<Req, Resp> {

    @Autowired
    protected RoleRepository roleRepository;

    protected void existsByName(String name) {
        if (StringUtils.isEmpty(name)) {
            return;
        }
        Long nameCount = roleRepository.existsByName(name, getTenantId());
        if (nameCount > 0) {
            throw new BusinessException(ResultCodeEnum.FAIL, "角色名重复");
        }
    }

    protected void exists(Long id) {
        if (Objects.isNull(id)) {
            return;
        }
        Long count = roleRepository.exists(id);
        if (count == 0) {
            throw new BusinessException(ResultCodeEnum.FAIL, "角色不存在");
        }
    }

}
