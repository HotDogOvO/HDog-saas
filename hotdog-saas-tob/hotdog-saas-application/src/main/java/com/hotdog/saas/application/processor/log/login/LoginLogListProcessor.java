package com.hotdog.saas.application.processor.log.login;

import com.alibaba.fastjson2.JSONObject;
import com.hotdog.saas.application.assembler.LoginAssembler;
import com.hotdog.saas.application.assembler.LoginLogAssembler;
import com.hotdog.saas.application.assembler.UserAssembler;
import com.hotdog.saas.application.entity.request.log.login.LoginLogPageRequest;
import com.hotdog.saas.application.entity.request.login.LoginRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.log.login.LoginLogDTO;
import com.hotdog.saas.application.entity.response.login.LoginDTO;
import com.hotdog.saas.application.entity.response.user.UserDTO;
import com.hotdog.saas.application.processor.log.AbstractLogProcessor;
import com.hotdog.saas.domain.constant.RedisConstants;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.enums.log.LogSuccessEnum;
import com.hotdog.saas.domain.enums.log.LogTypeEnum;
import com.hotdog.saas.domain.exception.BusinessException;
import com.hotdog.saas.domain.model.Login;
import com.hotdog.saas.domain.model.LoginLog;
import com.hotdog.saas.domain.model.Menu;
import com.hotdog.saas.domain.model.Role;
import com.hotdog.saas.domain.model.RoleMenu;
import com.hotdog.saas.domain.model.User;
import com.hotdog.saas.domain.model.UserRole;
import com.hotdog.saas.domain.model.page.PageRequest;
import com.hotdog.saas.domain.model.page.PageResponse;
import com.hotdog.saas.domain.repository.LoginLogRepository;
import com.hotdog.saas.domain.utils.DateUtils;
import com.hotdog.saas.domain.utils.NetworkUtils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoginLogListProcessor extends AbstractLogProcessor<LoginLogPageRequest, BaseResponse<PageResponseDTO<LoginLogDTO>>> {

    private final LoginLogRepository loginLogRepository;

    public LoginLogListProcessor(LoginLogRepository loginLogRepository) {
        this.loginLogRepository = loginLogRepository;
    }

    @Override
    public BaseResponse<PageResponseDTO<LoginLogDTO>> initResult() {
        BaseResponse<PageResponseDTO<LoginLogDTO>> result = new BaseResponse<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    @Override
    public void doExecute(LoginLogPageRequest request, BaseResponse<PageResponseDTO<LoginLogDTO>> response) {
        request.initPage();
        LoginLog loginLog = LoginLogAssembler.INSTANCE.convert(request);
        PageRequest pageRequest = reqToPage(request);

        PageResponse<List<LoginLog>> listPageResponse = loginLogRepository.listPage(loginLog, pageRequest);
        PageResponseDTO<LoginLogDTO> loginLogDTOPageResponseDTO = LoginLogAssembler.INSTANCE.convertPage(listPageResponse);

        response.setData(loginLogDTOPageResponseDTO);
    }

}
