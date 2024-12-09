package com.hotdog.saas.infra.core.init.database;

import com.hotdog.saas.domain.constant.Constants;
import com.hotdog.saas.domain.model.SystemProperties;
import com.hotdog.saas.domain.repository.SystemPropertiesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public abstract class AbstractDataBaseUpdateExecute {

    @Autowired
    protected SystemPropertiesRepository systemPropertiesRepository;

    @Value("${project.version}")
    protected String projectVersion;

    /**
     * <p>抽象方法，检测系统版本号，对应多种DB处理</p>
     */
    public abstract void checkVersion();

    /**
     * <p>抽象方法，具体执行升级的SQL</p>
     * @param dbVersion 数据库存储的版本号
     */
    public abstract void executeUpdateSql(String dbVersion);

    /**
     * <p>版本号对比</p>
     *
     * @param projectVersion pom中的版本号
     * @param dbVersion      数据库中的版本号
     * @return <li>大于0：数据库版本号大，系统异常</li>
     * <li>等于0：版本号一致，不更新</li>
     * <li>小于0：数据库版本号小，需要sql更新</li>
     */
    protected static int compareVersions(final String projectVersion, final String dbVersion) {
        String[] v1Parts = projectVersion.split("\\.");
        String[] v2Parts = dbVersion.split("\\.");

        int maxLength = Math.max(v1Parts.length, v2Parts.length);

        for (int i = 0; i < maxLength; i++) {
            int v1 = i < v1Parts.length ? Integer.parseInt(v1Parts[i]) : 0;
            int v2 = i < v2Parts.length ? Integer.parseInt(v2Parts[i]) : 0;

            if (v1 != v2) {
                return v1 - v2;
            }
        }
        return 0;
    }

    protected void updateDbVersion(String projectVersion) {
        SystemProperties update = SystemProperties.builder()
                .name(Constants.DB_COLUMN_VERSION)
                .value(projectVersion)
                .operator(Constants.SYSTEM_OPERATOR)
                .build();
        systemPropertiesRepository.edit(update);
    }
}
