package com.hotdog.saas.matrix.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class KnifeConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(this.getApiInfo());
    }

    private Info getApiInfo() {
        return new Info()
                .title("HDog-saas API")
                .description("HDog-saas平台接口文档")
                .contact(new Contact().name("热狗").email("wudonghe1996@gmail.com"))
                .version("1.0");
    }

}
