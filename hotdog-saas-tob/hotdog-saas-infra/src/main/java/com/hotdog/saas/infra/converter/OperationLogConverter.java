package com.hotdog.saas.infra.converter;

import com.hotdog.saas.domain.model.OperationLog;
import com.hotdog.saas.infra.entity.OperationLogDO;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OperationLogConverter {

    OperationLogConverter INSTANCE = Mappers.getMapper(OperationLogConverter.class);

    OperationLogDO convert2DO(OperationLog operationLog);

    List<OperationLogDO> convert2DOList(List<OperationLog> operationLogList);

    OperationLog convert(OperationLogDO operationLogDO);

}
