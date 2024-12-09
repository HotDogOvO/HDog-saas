package com.hotdog.saas.infra.core.init;

import com.hotdog.saas.infra.core.init.database.AbstractDataBaseUpdateExecute;
import com.hotdog.saas.infra.core.init.database.DataBaseUpdateFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SystemInit implements CommandLineRunner {

    private final DataBaseUpdateFactory dataBaseUpdateFactory;

    public SystemInit(DataBaseUpdateFactory dataBaseUpdateFactory) {
        this.dataBaseUpdateFactory = dataBaseUpdateFactory;
    }

    @Override
    public void run(String[] args) {
        this.dataBaseUpdate();
    }

    private void dataBaseUpdate(){
        AbstractDataBaseUpdateExecute execute = dataBaseUpdateFactory.getDataBaseUpdate();
        execute.checkVersion();
    }
}
