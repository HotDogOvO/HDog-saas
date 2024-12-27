package com.hotdog.saas.application.processor.menu;

import com.hotdog.saas.application.assembler.MenuAssembler;
import com.hotdog.saas.application.entity.request.menu.CreateMenuRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.model.Menu;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MenuCreateProcessor extends AbstractMenuProcessor<CreateMenuRequest, BaseResponse<Boolean>> {

    @Override
    public BaseResponse<Boolean> initResult() {
        BaseResponse<Boolean> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doExecute(CreateMenuRequest request, BaseResponse<Boolean> response) {
        super.existsByName(request.getName());
        super.existsByPermission(request.getPermission());
        Menu menu = MenuAssembler.INSTANCE.convert(request);

        Integer saveFlag = menuRepository.save(menu);
        response.setData(checkFlag(saveFlag));
    }

}
