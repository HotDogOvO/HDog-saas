package com.hotdog.saas.infra.repository;

import com.hotdog.saas.domain.model.OperationLog;
import com.hotdog.saas.domain.repository.OperationLogRepository;
import com.hotdog.saas.infra.converter.OperationLogConverter;
import com.hotdog.saas.infra.dao.OperationLogMapper;
import com.hotdog.saas.infra.entity.OperationLogDO;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OperationLogRepositoryImpl extends AbstractBaseRepository implements OperationLogRepository {

    private final OperationLogMapper operationLogMapper;

    public OperationLogRepositoryImpl(OperationLogMapper operationLogMapper) {
        this.operationLogMapper = operationLogMapper;
    }

    @Override
    public Integer batchSave(List<OperationLog> list) {
        List<OperationLogDO> operationLogDOList = OperationLogConverter.INSTANCE.convert2DOList(list);
        return operationLogMapper.insert(operationLogDOList).size();
    }
}
