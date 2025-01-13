package com.hotdog.saas.application.assembler;

import com.hotdog.saas.application.entity.request.role.CreateRoleRequest;
import com.hotdog.saas.application.entity.request.role.OptionsRoleRequest;
import com.hotdog.saas.application.entity.request.role.RolePageRequest;
import com.hotdog.saas.application.entity.request.role.UpdateRoleRequest;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.role.RoleDTO;
import com.hotdog.saas.application.entity.response.role.RoleOptionsDTO;
import com.hotdog.saas.domain.model.Role;
import com.hotdog.saas.domain.model.page.PageResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RoleAssembler {

    RoleAssembler INSTANCE = Mappers.getMapper(RoleAssembler.class);

    Role convert(CreateRoleRequest createRoleRequest);

    Role convert(OptionsRoleRequest optionsRoleRequest);

    Role convert(RolePageRequest RolePageRequest);

    Role convert(UpdateRoleRequest updateRoleRequest);

    PageResponseDTO<RoleDTO> convertPage(PageResponse<List<Role>> pageResponse);

    List<RoleOptionsDTO> convertOptions(List<Role> roleList);

    RoleDTO convertToDTO(Role Role);
}
