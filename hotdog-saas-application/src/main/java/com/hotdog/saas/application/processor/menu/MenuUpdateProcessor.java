package com.hotdog.saas.application.processor.menu;

import com.hotdog.saas.application.assembler.MenuAssembler;
import com.hotdog.saas.application.assembler.RoleAssembler;
import com.hotdog.saas.application.entity.request.menu.UpdateMenuRequest;
import com.hotdog.saas.application.entity.request.role.UpdateRoleRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.model.Menu;
import com.hotdog.saas.domain.model.Role;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MenuUpdateProcessor extends AbstractMenuProcessor<UpdateMenuRequest, BaseResponse<Boolean>> {

    @Override
    public BaseResponse<Boolean> initResult() {
        BaseResponse<Boolean> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doExecute(UpdateMenuRequest request, BaseResponse<Boolean> response) {
        super.exists(request.getId());
        Menu menu = MenuAssembler.INSTANCE.convert(request);
        Integer modifyFlag = menuRepository.modify(menu);
        response.setData(checkFlag(modifyFlag));
    }

}
