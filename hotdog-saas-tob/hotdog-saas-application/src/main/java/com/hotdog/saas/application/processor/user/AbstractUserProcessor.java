package com.hotdog.saas.application.processor.user;

import com.hotdog.saas.application.entity.request.BaseRequestParam;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.user.UserDTO;
import com.hotdog.saas.application.processor.AbstractBaseProcessor;
import com.hotdog.saas.application.template.BizProcessorTemplate;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.exception.BusinessException;
import com.hotdog.saas.domain.model.Role;
import com.hotdog.saas.domain.model.UserRole;
import com.hotdog.saas.domain.repository.RoleRepository;
import com.hotdog.saas.domain.repository.UserRoleRepository;

import io.micrometer.common.util.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;

public abstract class AbstractUserProcessor<Req extends BaseRequestParam, Resp extends BaseResponse<?>> extends AbstractBaseProcessor implements BizProcessorTemplate<Req, Resp> {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private RoleRepository roleRepository;


    protected void existsByUsername(String username) {
        if (StringUtils.isEmpty(username)) {
            return;
        }
        Long nameCount = userRepository.existsByUsername(username, getTenantId());
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

    protected List<Role> findUserRole(Long userId) {
        List<UserRole> userRoleList = userRoleRepository.findByUserId(userId);
        List<Long> roleIdList = userRoleList.stream().map(UserRole::getRoleId).toList();
        return roleRepository.findByIdList(roleIdList);
    }

    protected void setUserRole(List<Role> roleList, UserDTO userDTO) {
        List<UserDTO.UserRoleDTO> userRoleList = roleList.stream().map(x -> UserDTO.UserRoleDTO.builder()
                .roleId(x.getId())
                .roleName(x.getName()).build()).toList();
        userDTO.setRoleList(userRoleList);
    }

}
