package com.hotdog.saas.domain.repository;

import com.hotdog.saas.domain.model.OperationLog;

import java.util.List;

public interface OperationLogRepository {

    Integer batchSave(List<OperationLog> list);
}
