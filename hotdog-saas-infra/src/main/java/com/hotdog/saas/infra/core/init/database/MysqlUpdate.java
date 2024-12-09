package com.hotdog.saas.infra.core.init.database;

import com.hotdog.saas.domain.constant.Constants;
import com.hotdog.saas.domain.enums.ResultCodeEnum;
import com.hotdog.saas.domain.exception.BusinessException;
import com.hotdog.saas.domain.model.SystemProperties;
import com.hotdog.saas.infra.dao.DataBaseUpdateMapper;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MysqlUpdate extends AbstractDataBaseUpdateExecute {

    private final DataBaseUpdateMapper dataBaseUpdateMapper;

    public MysqlUpdate(DataBaseUpdateMapper dataBaseUpdateMapper) {
        this.dataBaseUpdateMapper = dataBaseUpdateMapper;
    }

    @Override
    public void checkVersion() {
        log.info("当前数据库为：Mysql，开始进行版本号检测");
        SystemProperties dbVersionProperties = systemPropertiesRepository.findByName(Constants.DB_COLUMN_VERSION);
        String dbVersion = dbVersionProperties.getValue();
        int diff = compareVersions(projectVersion, dbVersion);

        log.info("当前数据库为：Mysql，版本对比完成，系统版本：{}，数据库版本：{}，对比结果：{}", projectVersion, dbVersion, diff);
        if (diff > 0) {
            log.info("开始进行数据库更新");
            this.executeUpdateSql(dbVersion);
            // 更新数据库版本
            updateDbVersion(projectVersion);
        } else if (diff == 0) {
            log.info("版本号一致，跳过更新");
        } else {
            // 小于0，系统异常
            throw new BusinessException(ResultCodeEnum.DATABASE_UPDATE_FAIL);
        }
    }

    @Override
    public void executeUpdateSql(String dbVersion) {
        if(compareVersions(dbVersion, "1.0.0") < 0){

        }
    }
}
