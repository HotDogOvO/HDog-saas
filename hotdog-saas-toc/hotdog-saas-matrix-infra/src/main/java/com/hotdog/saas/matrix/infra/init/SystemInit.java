package com.hotdog.saas.matrix.infra.init;

import com.hotdog.saas.matrix.domain.constant.Constants;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.TimeZone;

@Component
public class SystemInit implements CommandLineRunner {

    @Override
    public void run(String[] args) {
        this.setTimeZone();
    }

    /**
     * 设置时区
     */
    private void setTimeZone() {
        TimeZone.setDefault(TimeZone.getTimeZone(Constants.SYSTEM_TIME_ZONE));
    }
}
