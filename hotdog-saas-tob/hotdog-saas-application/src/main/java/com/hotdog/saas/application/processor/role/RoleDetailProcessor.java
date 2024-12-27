package com.hotdog.saas.application.processor.role;

import com.hotdog.saas.application.assembler.RoleAssembler;
import com.hotdog.saas.application.entity.request.role.QueryRoleRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.role.RoleDTO;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.model.Menu;
import com.hotdog.saas.domain.model.Role;
import com.hotdog.saas.domain.model.RoleMenu;
import com.hotdog.saas.domain.repository.MenuRepository;
import com.hotdog.saas.domain.repository.RoleMenuRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
public class RoleDetailProcessor extends AbstractRoleProcessor<QueryRoleRequest, BaseResponse<RoleDTO>> {

    private final RoleMenuRepository roleMenuRepository;
    private final MenuRepository menuRepository;

    public RoleDetailProcessor(RoleMenuRepository roleMenuRepository, MenuRepository menuRepository) {
        this.roleMenuRepository = roleMenuRepository;
        this.menuRepository = menuRepository;
    }

    @Override
    public BaseResponse<RoleDTO> initResult() {
        BaseResponse<RoleDTO> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    public void doExecute(QueryRoleRequest request, BaseResponse<RoleDTO> response) {
        Long userId = request.getId();
        super.exists(userId);
        Role role = roleRepository.findById(userId);

        RoleDTO roleDTO = RoleAssembler.INSTANCE.convertToDTO(role);

        List<RoleMenu> roleMenuList = roleMenuRepository.findByRoleId(role.getId());
        Set<Long> menuIdSet = roleMenuList.stream().map(RoleMenu::getMenuId).collect(Collectors.toSet());
        List<Menu> menuList = menuRepository.findByIdList(menuIdSet);

        List<RoleDTO.RoleMenuDTO> resultRoleMenuList = menuList.stream()
                .map(x -> RoleDTO.RoleMenuDTO.builder().name(x.getName()).permission(x.getPermission()).build()).toList();

        roleDTO.setRoleMenuList(resultRoleMenuList);
        response.setData(roleDTO);
    }

}
