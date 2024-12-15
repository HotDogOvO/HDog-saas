package com.hotdog.saas.application.processor.user;

import com.hotdog.saas.application.assembler.UserAssembler;
import com.hotdog.saas.application.assembler.UserRoleAssembler;
import com.hotdog.saas.application.entity.request.user.ChangePasswordRequest;
import com.hotdog.saas.application.entity.request.user.PermissionUserRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.domain.constant.RedisConstants;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.exception.BusinessException;
import com.hotdog.saas.domain.foundation.RedisCacheService;
import com.hotdog.saas.domain.model.User;
import com.hotdog.saas.domain.model.UserRole;
import com.hotdog.saas.domain.repository.RoleRepository;
import com.hotdog.saas.domain.repository.UserRoleRepository;
import com.hotdog.saas.domain.service.PasswordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static net.sf.jsqlparser.util.validation.metadata.NamedObject.user;

@Slf4j
@Component
public class UserPermissionProcessor extends AbstractUserProcessor<PermissionUserRequest, BaseResponse<Boolean>> {

    private final UserRoleRepository userRoleRepository;
    private final RoleRepository roleRepository;
    private final RedisCacheService redisCacheService;

    public UserPermissionProcessor(UserRoleRepository userRoleRepository, RoleRepository roleRepository, RedisCacheService redisCacheService) {
        this.userRoleRepository = userRoleRepository;
        this.roleRepository = roleRepository;
        this.redisCacheService = redisCacheService;
    }

    @Override
    public BaseResponse<Boolean> initResult() {
        BaseResponse<Boolean> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doExecute(PermissionUserRequest request, BaseResponse<Boolean> response) {
        Long userId = request.getId();

        valid(request);

        // 清空原权限
        userRoleRepository.removeByUserId(userId);

        UserRole userRole = UserRoleAssembler.INSTANCE.convert(request);
        Integer saveFlag = userRoleRepository.save(userRole);

        // 清空token，重新登录
        // todo code review
        User user = userRepository.findById(userId);
        redisCacheService.delete(RedisConstants.getUserKey(user.getUsername()));

        response.setData(checkFlag(saveFlag));
    }

    private void valid(PermissionUserRequest request){
        // 校验用户是否存在
        super.exists(request.getId());
        // 校验角色是否存在
        validRoleIdList(request.getRoleIdList());
    }

    private void validRoleIdList(List<Long> roleIdList){
        Long count = roleRepository.countByIdList(roleIdList);
        if(count != roleIdList.size()){
            throw new BusinessException("存在非法的角色");
        }
    }

}
