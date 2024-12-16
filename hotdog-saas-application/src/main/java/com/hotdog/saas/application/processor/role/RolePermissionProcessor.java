package com.hotdog.saas.application.processor.role;

import com.hotdog.saas.application.assembler.RoleMenuAssembler;
import com.hotdog.saas.application.assembler.UserRoleAssembler;
import com.hotdog.saas.application.entity.request.role.PermissionRoleRequest;
import com.hotdog.saas.application.entity.request.user.PermissionUserRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.processor.user.AbstractUserProcessor;
import com.hotdog.saas.domain.constant.RedisConstants;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.exception.BusinessException;
import com.hotdog.saas.domain.foundation.RedisCacheService;
import com.hotdog.saas.domain.model.RoleMenu;
import com.hotdog.saas.domain.model.User;
import com.hotdog.saas.domain.model.UserRole;
import com.hotdog.saas.domain.repository.MenuRepository;
import com.hotdog.saas.domain.repository.RoleMenuRepository;
import com.hotdog.saas.domain.repository.RoleRepository;
import com.hotdog.saas.domain.repository.UserRoleRepository;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RolePermissionProcessor extends AbstractRoleProcessor<PermissionRoleRequest, BaseResponse<Boolean>> {

    private final MenuRepository menuRepository;
    private final RoleMenuRepository roleMenuRepository;
    private final UserRoleRepository userRoleRepository;

    public RolePermissionProcessor(MenuRepository menuRepository, RoleMenuRepository roleMenuRepository, UserRoleRepository userRoleRepository) {
        this.menuRepository = menuRepository;
        this.roleMenuRepository = roleMenuRepository;
        this.userRoleRepository = userRoleRepository;
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
    public void doExecute(PermissionRoleRequest request, BaseResponse<Boolean> response) {
        Long roleId = request.getId();

        valid(request);

        // 清空原权限
        roleMenuRepository.removeByRoleId(roleId);

        RoleMenu roleMenu = RoleMenuAssembler.INSTANCE.convert(request);
        Integer saveFlag = roleMenuRepository.save(roleMenu);

        // 清空token，重新登录
        List<UserRole> userRoleList = userRoleRepository.findByRoleId(roleId);
        List<Long> userIdList = userRoleList.stream().map(UserRole::getUserId).toList();
        super.removeToken(userIdList);

        response.setData(checkFlag(saveFlag));
    }

    private void valid(PermissionRoleRequest request){
        // 校验角色是否存在
        super.exists(request.getId());
        // 校验角色是否存在
        validMenuIdList(request.getMenuIdList());
    }

    private void validMenuIdList(List<Long> menuIdList){
        Long count = menuRepository.countByIdList(menuIdList);
        if(count != menuIdList.size()){
            throw new BusinessException("存在非法的菜单");
        }
    }

}
