package com.hotdog.saas.infra.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hotdog.saas.domain.enums.common.DeleteEnum;
import com.hotdog.saas.domain.model.EducationCourseClassEnroll;
import com.hotdog.saas.domain.model.page.PageRequest;
import com.hotdog.saas.domain.model.page.PageResponse;
import com.hotdog.saas.domain.repository.EducationCourseClassEnrollRepository;
import com.hotdog.saas.domain.utils.DateUtils;
import com.hotdog.saas.infra.converter.EducationCourseClassEnrollConverter;
import com.hotdog.saas.infra.dao.EducationCourseClassEnrollMapper;
import com.hotdog.saas.infra.entity.EducationCourseClassEnrollDO;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EducationCourseClassEnrollRepositoryImpl extends AbstractBaseRepository implements EducationCourseClassEnrollRepository {

    private final EducationCourseClassEnrollMapper educationCourseClassEnrollMapper;

    public EducationCourseClassEnrollRepositoryImpl(EducationCourseClassEnrollMapper educationCourseClassEnrollMapper) {
        this.educationCourseClassEnrollMapper = educationCourseClassEnrollMapper;
    }

    @Override
    public PageResponse<List<EducationCourseClassEnroll>> listPage(EducationCourseClassEnroll educationCourseClassEnroll, PageRequest pageRequest) {
        Page<EducationCourseClassEnrollDO> page = new Page<>(pageRequest.getPageIndex(), pageRequest.getPageSize());
        LambdaQueryWrapper<EducationCourseClassEnrollDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EducationCourseClassEnrollDO::getWechatId, educationCourseClassEnroll.getWechatId());
        queryWrapper.eq(EducationCourseClassEnrollDO::getDeleted, DeleteEnum.NO.getCode());
        queryWrapper.orderByDesc(EducationCourseClassEnrollDO::getCreateTime);

        Page<EducationCourseClassEnrollDO> pageResult = educationCourseClassEnrollMapper.selectPage(page, queryWrapper);
        List<EducationCourseClassEnroll> list = pageResult.getRecords().stream().map(EducationCourseClassEnrollConverter.INSTANCE::convert).toList();

        PageResponse<List<EducationCourseClassEnroll>> listPageResponse = pageConverter(pageResult);
        listPageResponse.setData(list);
        return listPageResponse;
    }

    @Override
    public EducationCourseClassEnroll findById(Long id) {
        LambdaQueryWrapper<EducationCourseClassEnrollDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EducationCourseClassEnrollDO::getId, id);
        queryWrapper.eq(EducationCourseClassEnrollDO::getDeleted, DeleteEnum.NO.getCode());
        EducationCourseClassEnrollDO educationCourseClassEnrollDO = educationCourseClassEnrollMapper.selectOne(queryWrapper);
        return EducationCourseClassEnrollConverter.INSTANCE.convert(educationCourseClassEnrollDO);
    }

    @Override
    public Long exists(Long id) {
        LambdaQueryWrapper<EducationCourseClassEnrollDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EducationCourseClassEnrollDO::getId, id);
        queryWrapper.eq(EducationCourseClassEnrollDO::getDeleted, DeleteEnum.NO.getCode());

        return educationCourseClassEnrollMapper.selectCount(queryWrapper);
    }

    @Override
    public Integer modify(EducationCourseClassEnroll educationCourseClassEnroll) {
        EducationCourseClassEnrollDO educationCourseClassEnrollDO = EducationCourseClassEnrollConverter.INSTANCE.convert2DO(educationCourseClassEnroll);
        educationCourseClassEnrollDO.setUpdater(educationCourseClassEnroll.getOperator())
                .setUpdateTime(DateUtils.now());
        return educationCourseClassEnrollMapper.updateById(educationCourseClassEnrollDO);
    }

    @Override
    public Integer remove(Long id, String operator) {
        EducationCourseClassEnrollDO educationCourseClassEnrollDO = new EducationCourseClassEnrollDO()
                .setId(id)
                .setDeleted(DeleteEnum.YES.getCode())
                .setUpdater(operator)
                .setUpdateTime(DateUtils.now());
        return educationCourseClassEnrollMapper.updateById(educationCourseClassEnrollDO);
    }
}
