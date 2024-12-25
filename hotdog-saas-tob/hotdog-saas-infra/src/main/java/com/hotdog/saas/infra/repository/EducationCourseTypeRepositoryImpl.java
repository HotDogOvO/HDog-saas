package com.hotdog.saas.infra.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hotdog.saas.domain.enums.common.DeleteEnum;
import com.hotdog.saas.domain.model.EducationCourseType;
import com.hotdog.saas.domain.model.page.PageRequest;
import com.hotdog.saas.domain.model.page.PageResponse;
import com.hotdog.saas.domain.repository.EducationCourseTypeRepository;
import com.hotdog.saas.domain.utils.DateUtils;
import com.hotdog.saas.infra.converter.EducationCourseTypeConverter;
import com.hotdog.saas.infra.dao.EducationCourseTypeMapper;
import com.hotdog.saas.infra.entity.EducationCourseTypeDO;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class EducationCourseTypeRepositoryImpl extends AbstractBaseRepository implements EducationCourseTypeRepository {

    private final EducationCourseTypeMapper educationCourseTypeMapper;

    public EducationCourseTypeRepositoryImpl(EducationCourseTypeMapper educationCourseTypeMapper) {
        this.educationCourseTypeMapper = educationCourseTypeMapper;
    }

    @Override
    public Integer save(EducationCourseType educationCourseType) {
        EducationCourseTypeDO educationCourseTypeDO = EducationCourseTypeConverter.INSTANCE.convert2DO(educationCourseType);
        LocalDateTime now = DateUtils.now();
        educationCourseTypeDO.setCreator(educationCourseType.getOperator()).setCreateTime(now)
                .setUpdater(educationCourseType.getOperator()).setUpdateTime(now);
        return educationCourseTypeMapper.insert(educationCourseTypeDO);
    }

    @Override
    public PageResponse<List<EducationCourseType>> listPage(EducationCourseType educationCourseType, PageRequest pageRequest) {
        Page<EducationCourseTypeDO> page = new Page<>(pageRequest.getPageIndex(), pageRequest.getPageSize());
        LambdaQueryWrapper<EducationCourseTypeDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EducationCourseTypeDO::getWechatId, educationCourseType.getWechatId());
        queryWrapper.eq(EducationCourseTypeDO::getDeleted, DeleteEnum.NO.getCode());
        queryWrapper.orderByDesc(EducationCourseTypeDO::getCreateTime);

        Page<EducationCourseTypeDO> pageResult = educationCourseTypeMapper.selectPage(page, queryWrapper);
        List<EducationCourseType> list = pageResult.getRecords().stream().map(EducationCourseTypeConverter.INSTANCE::convert).toList();

        PageResponse<List<EducationCourseType>> listPageResponse = pageConverter(pageResult);
        listPageResponse.setData(list);
        return listPageResponse;
    }

    @Override
    public List<EducationCourseType> list(EducationCourseType educationCourseType) {
        LambdaQueryWrapper<EducationCourseTypeDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EducationCourseTypeDO::getWechatId, educationCourseType.getWechatId());
        queryWrapper.eq(EducationCourseTypeDO::getDeleted, DeleteEnum.NO.getCode());
        queryWrapper.orderByDesc(EducationCourseTypeDO::getCreateTime);

        List<EducationCourseTypeDO> educationCourseTypeDOList = educationCourseTypeMapper.selectList(queryWrapper);
        return educationCourseTypeDOList.stream().map(EducationCourseTypeConverter.INSTANCE::convert).toList();
    }

    @Override
    public EducationCourseType findById(Long id) {
        LambdaQueryWrapper<EducationCourseTypeDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EducationCourseTypeDO::getId, id);
        EducationCourseTypeDO educationCourseTypeDO = educationCourseTypeMapper.selectOne(queryWrapper);
        return EducationCourseTypeConverter.INSTANCE.convert(educationCourseTypeDO);
    }

    @Override
    public Long exists(Long id) {
        LambdaQueryWrapper<EducationCourseTypeDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EducationCourseTypeDO::getId, id);
        return educationCourseTypeMapper.selectCount(queryWrapper);
    }

    @Override
    public Long existByTypeName(String typeName, Long wechatId) {
        LambdaQueryWrapper<EducationCourseTypeDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EducationCourseTypeDO::getName, typeName);
        queryWrapper.eq(EducationCourseTypeDO::getWechatId, wechatId);
        queryWrapper.eq(EducationCourseTypeDO::getDeleted, DeleteEnum.NO.getCode());
        return educationCourseTypeMapper.selectCount(queryWrapper);
    }

    @Override
    public Integer modify(EducationCourseType educationCourseType) {
        EducationCourseTypeDO educationCourseTypeDO = EducationCourseTypeConverter.INSTANCE.convert2DO(educationCourseType)
                .setUpdater(educationCourseType.getOperator())
                .setUpdateTime(DateUtils.now());
        return educationCourseTypeMapper.updateById(educationCourseTypeDO);
    }

    @Override
    public Integer remove(Long id, String operator) {
        EducationCourseTypeDO educationCourseTypeDO = new EducationCourseTypeDO()
                .setId(id)
                .setDeleted(DeleteEnum.YES.getCode())
                .setUpdater(operator)
                .setUpdateTime(DateUtils.now());
        return educationCourseTypeMapper.updateById(educationCourseTypeDO);
    }
}
