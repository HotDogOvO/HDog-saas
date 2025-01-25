package com.hotdog.saas.matrix.infra.feign.config;

import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Client;
import feign.Logger;
import feign.codec.Decoder;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;

@Slf4j
@Configuration
public class CommonFeignConfig {

    @Bean
    public Client feignClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .build();

        return new feign.okhttp.OkHttpClient(okHttpClient);
    }


    /**
     * 返回解码，下划线转驼峰
     * @return
     */
    @Bean
    public Decoder decoder() {
        JsonResponseConverter jsonResponseConverter = new JsonResponseConverter();
        return new ResponseEntityDecoder(new SpringDecoder(() ->
                new HttpMessageConverters(jsonResponseConverter)
        ));
    }

    /**
     * feign日志级别
     * @return
     */
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

}
