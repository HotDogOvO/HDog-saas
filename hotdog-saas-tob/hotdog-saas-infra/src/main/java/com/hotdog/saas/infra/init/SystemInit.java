package com.hotdog.saas.infra.init;

import com.hotdog.saas.domain.constant.Constants;
import com.hotdog.saas.infra.init.database.AbstractDataBaseUpdateExecute;
import com.hotdog.saas.infra.init.database.DataBaseUpdateFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.util.TimeZone;

@Component
public class SystemInit implements CommandLineRunner {

    private final DataBaseUpdateFactory dataBaseUpdateFactory;

    public SystemInit(DataBaseUpdateFactory dataBaseUpdateFactory) {
        this.dataBaseUpdateFactory = dataBaseUpdateFactory;
    }

    @Override
    public void run(String[] args) {
        this.setTimeZone();
        this.dataBaseUpdate();
    }

    /**
     * 数据库自动升级
     */
    private void dataBaseUpdate(){
        AbstractDataBaseUpdateExecute execute = dataBaseUpdateFactory.getDataBaseUpdate();
        execute.checkVersion();
    }

    /**
     * 设置时区
     */
    private void setTimeZone(){
        TimeZone.setDefault(TimeZone.getTimeZone(Constants.SYSTEM_TIME_ZONE));
    }
}
