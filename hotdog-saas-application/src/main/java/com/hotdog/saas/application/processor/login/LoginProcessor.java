package com.hotdog.saas.application.processor.login;

import com.hotdog.saas.application.assembler.LoginAssembler;
import com.hotdog.saas.application.entity.request.login.LoginRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.login.LoginDTO;
import com.hotdog.saas.domain.foundation.AuthService;
import com.hotdog.saas.domain.foundation.RedisCacheService;
import com.hotdog.saas.domain.constant.RedisConstants;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.exception.BusinessException;
import com.hotdog.saas.domain.model.Login;
import com.hotdog.saas.domain.model.Menu;
import com.hotdog.saas.domain.model.Role;
import com.hotdog.saas.domain.model.RoleMenu;
import com.hotdog.saas.domain.model.User;
import com.hotdog.saas.domain.model.UserRole;
import com.hotdog.saas.domain.repository.LoginRepository;
import com.hotdog.saas.domain.repository.MenuRepository;
import com.hotdog.saas.domain.repository.RoleMenuRepository;
import com.hotdog.saas.domain.repository.RoleRepository;
import com.hotdog.saas.domain.repository.UserRepository;
import com.hotdog.saas.domain.repository.UserRoleRepository;
import com.hotdog.saas.domain.service.PasswordService;
import com.hotdog.saas.domain.utils.DateUtils;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoginProcessor extends AbstractLoginProcessor<LoginRequest, BaseResponse<LoginDTO>> {

    private final LoginRepository loginRepository;

    private final RoleRepository roleRepository;

    private final UserRoleRepository userRoleRepository;

    private final MenuRepository menuRepository;

    private final RoleMenuRepository roleMenuRepository;

    private final PasswordService passwordService;

    private final AuthService authService;

    public LoginProcessor(LoginRepository loginRepository, RoleRepository roleRepository, UserRoleRepository userRoleRepository, MenuRepository menuRepository, RoleMenuRepository roleMenuRepository, PasswordService passwordService, AuthService authService) {
        this.loginRepository = loginRepository;
        this.roleRepository = roleRepository;
        this.userRoleRepository = userRoleRepository;
        this.menuRepository = menuRepository;
        this.roleMenuRepository = roleMenuRepository;
        this.passwordService = passwordService;
        this.authService = authService;
    }

    @Override
    public BaseResponse<LoginDTO> initResult() {
        BaseResponse<LoginDTO> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    public void doExecute(LoginRequest request, BaseResponse<LoginDTO> response) {
        // 校验用户是否存在
        existsLogin(request.getUsername());
        LoginDTO loginDTO = redisCacheService.get(RedisConstants.getUserKey(request.getUsername()), LoginDTO.class);
//        if (loginDTO == null) {
            // 1. 登录
            Login loginUser = loginRepository.findLoginUser(request.getUsername());

            Boolean loginFlag = passwordService.checkPassword(loginUser.getPassword(), request.getPassword(), loginUser.getSalt());
            if (!loginFlag) {
                throw new BusinessException("用户名或密码错误，请检查后重试");
            }
            loginDTO = buildLoginDTO(loginUser);

            // 2. 查询角色
            Long userId = loginUser.getId();
            List<LoginDTO.UserRoleDTO> userRoleList = getUserRole(userId);
            loginDTO.setRoleList(userRoleList);

            // 3. 查询权限
            List<Long> roleIdList = userRoleList.stream().map(LoginDTO.UserRoleDTO::getRoleId).toList();
            List<LoginDTO.RoleMenuDTO> roleMenuList = getRoleMenu(roleIdList);
            loginDTO.setMenuList(roleMenuList);

            // 4. 记录登录IP和时间
            saveLoginIpAndDate(userId, request.getLoginIp(), loginUser.getUsername());

            // 写入redis
            redisCacheService.set(RedisConstants.getUserKey(loginDTO.getUsername()), loginDTO, RedisConstants.USER_TOKEN_TTL);
//        }

        response.setData(loginDTO);
    }

    private LoginDTO buildLoginDTO(Login loginUser) {
        LoginDTO loginDTO = LoginAssembler.INSTANCE.convert(loginUser);
        loginDTO.setToken(authService.generateToken(loginUser));
        return loginDTO;
    }

    private void existsLogin(String username) {
        if (StringUtils.isEmpty(username)) {
            return;
        }
        Long nameCount = userRepository.existsByUsername(username);
        if (nameCount == 0) {
            throw new BusinessException(ResultCodeEnum.FAIL, "用户名不存在");
        }
    }

    private List<LoginDTO.UserRoleDTO> getUserRole(Long userId) {
        List<UserRole> userRoleList = userRoleRepository.findByUserId(userId);
        Set<Long> roleIdSet = userRoleList.stream().map(UserRole::getRoleId).collect(Collectors.toSet());
        List<Long> roleIdList = roleIdSet.stream().toList();
        List<Role> roleList = roleRepository.findByIdList(roleIdList);
        return roleList.stream().map(x -> LoginDTO.UserRoleDTO.builder().roleId(x.getId()).roleName(x.getName()).build()).toList();
    }

    private List<LoginDTO.RoleMenuDTO> getRoleMenu(List<Long> roleIdList) {
        Set<RoleMenu> roleMenuList = roleMenuRepository.findByRoleIdList(roleIdList);
        Set<Long> menuIdSet = roleMenuList.stream().map(RoleMenu::getMenuId).collect(Collectors.toSet());
        List<Menu> menuList = menuRepository.findByIdList(menuIdSet);
        return menuList.stream().map(x -> LoginDTO.RoleMenuDTO.builder().name(x.getName()).permission(x.getPermission()).build()).toList();
    }

    private void saveLoginIpAndDate(Long userId, String loginIp, String operator) {
        LocalDateTime now = DateUtils.now();
        User user = User.builder().id(userId)
                .loginIp(loginIp).loginDate(now)
                .updater(operator).updateTime(now)
                .build();
        userRepository.modify(user);
    }

}
