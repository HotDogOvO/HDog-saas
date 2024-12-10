package com.hotdog.saas.application.entity.request;

import java.io.Serial;
import java.io.Serializable;

import lombok.Data;

@Data
public abstract class BaseRequestParam implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String appId;

    private String operator;

    private Long timestamp;

    public abstract void validate();
}
