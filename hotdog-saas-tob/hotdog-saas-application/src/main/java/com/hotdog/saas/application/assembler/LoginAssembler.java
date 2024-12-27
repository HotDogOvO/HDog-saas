package com.hotdog.saas.application.assembler;

import com.hotdog.saas.application.entity.response.login.LoginDTO;
import com.hotdog.saas.domain.model.Login;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LoginAssembler {

    LoginAssembler INSTANCE = Mappers.getMapper(LoginAssembler.class);

    @Mappings(
            @Mapping(source = "id", target = "userId")
    )
    LoginDTO convert(Login login);

}
