package com.hotdog.saas.application.facade;

import com.hotdog.saas.application.entity.request.log.login.LoginLogPageRequest;
import com.hotdog.saas.application.entity.response.BaseResponse;
import com.hotdog.saas.application.entity.response.PageResponseDTO;
import com.hotdog.saas.application.entity.response.log.login.LoginLogDTO;

public interface LoginLogFacade {

    BaseResponse<PageResponseDTO<LoginLogDTO>> loginLogListPage(LoginLogPageRequest loginLogPageRequest);

}
