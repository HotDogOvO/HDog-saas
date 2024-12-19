package com.hotdog.saas.domain.model.message;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class CanalLogMessage {

    /**
     * 修改后的对象
     */
    private List<Map<String, String>> data;

    /**
     * 修改前的对象
     * 只有type = UPDATE时才有值
     */
    private List<Map<String, String>> old;

    /**
     * 修改的时间点
     */
    private Long es;

    /**
     * 操作类型
     */
    private String type;

    /**
     * 操作表名
     */
    private String table;
}
