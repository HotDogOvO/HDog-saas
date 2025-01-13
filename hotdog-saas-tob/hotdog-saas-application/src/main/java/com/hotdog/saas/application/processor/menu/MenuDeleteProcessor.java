package com.hotdog.saas.application.processor.menu;

import com.hotdog.saas.application.entity.request.menu.DeleteMenuRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.domain.enums.ResultCodeEnum;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MenuDeleteProcessor extends AbstractMenuProcessor<DeleteMenuRequest, BaseResponse<Boolean>> {

    @Override
    public BaseResponse<Boolean> initResult() {
        BaseResponse<Boolean> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doExecute(DeleteMenuRequest request, BaseResponse<Boolean> response) {
        super.exists(request.getId());
        // 删除当前菜单
        Integer removeFlag = menuRepository.remove(request.getId(), request.getOperator());

        // 删除关联的子菜单
        menuRepository.removeByParentId(request.getId(), request.getOperator());

        response.setData(checkFlag(removeFlag));
    }

}
