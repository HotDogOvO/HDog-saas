package com.hotdog.saas.infra.core.init.database;

import com.hotdog.saas.domain.enums.DataBaseEnum;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.exception.BusinessException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DataBaseUpdateFactory {

    @Value("${project.database}")
    private String DATABASE_TYPE;

    private final MysqlUpdateExecute mysqlUpdateExecute;

    public DataBaseUpdateFactory(MysqlUpdateExecute mysqlUpdateExecute) {
        this.mysqlUpdateExecute = mysqlUpdateExecute;
    }

    public AbstractDataBaseUpdateExecute getDataBaseUpdate() {
        DataBaseEnum dataBaseEnum = DataBaseEnum.descToEnum(StringUtils.lowerCase(DATABASE_TYPE));
        return switch (dataBaseEnum) {
            case MYSQL -> mysqlUpdateExecute;
            case UNKNOWN -> throw new BusinessException(ResultCodeEnum.DATABASE_UPDATE_FAIL, "Unknown animal type: " + dataBaseEnum);
        };
    }

}
