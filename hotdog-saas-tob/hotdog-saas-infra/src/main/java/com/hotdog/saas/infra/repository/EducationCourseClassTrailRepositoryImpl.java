package com.hotdog.saas.infra.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hotdog.saas.domain.enums.common.DeleteEnum;
import com.hotdog.saas.domain.model.EducationCourseClassTrail;
import com.hotdog.saas.domain.model.page.PageRequest;
import com.hotdog.saas.domain.model.page.PageResponse;
import com.hotdog.saas.domain.repository.EducationCourseClassTrailRepository;
import com.hotdog.saas.domain.utils.DateUtils;
import com.hotdog.saas.infra.converter.EducationCourseClassTrailConverter;
import com.hotdog.saas.infra.dao.EducationCourseClassTrailMapper;
import com.hotdog.saas.infra.entity.EducationCourseClassTrailDO;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class EducationCourseClassTrailRepositoryImpl extends AbstractBaseRepository implements EducationCourseClassTrailRepository {

    private final EducationCourseClassTrailMapper educationCourseClassTrailMapper;

    public EducationCourseClassTrailRepositoryImpl(EducationCourseClassTrailMapper educationCourseClassTrailMapper) {
        this.educationCourseClassTrailMapper = educationCourseClassTrailMapper;
    }

    @Override
    public PageResponse<List<EducationCourseClassTrail>> listPage(EducationCourseClassTrail educationCourseClassTrail, PageRequest pageRequest) {
        Page<EducationCourseClassTrailDO> page = new Page<>(pageRequest.getPageIndex(), pageRequest.getPageSize());
        LambdaQueryWrapper<EducationCourseClassTrailDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EducationCourseClassTrailDO::getWechatId, educationCourseClassTrail.getWechatId());
        queryWrapper.eq(EducationCourseClassTrailDO::getDeleted, DeleteEnum.NO.getCode());
        queryWrapper.eq(Objects.nonNull(educationCourseClassTrail.getStatus()), EducationCourseClassTrailDO::getStatus, educationCourseClassTrail.getStatus());
        queryWrapper.like(StringUtils.isNotEmpty(educationCourseClassTrail.getCourseNo()), EducationCourseClassTrailDO::getCourseNo, educationCourseClassTrail.getCourseNo());
        queryWrapper.like(StringUtils.isNotEmpty(educationCourseClassTrail.getClassNo()), EducationCourseClassTrailDO::getClassNo, educationCourseClassTrail.getClassNo());
        queryWrapper.orderByAsc(EducationCourseClassTrailDO::getCreateTime);

        Page<EducationCourseClassTrailDO> pageResult = educationCourseClassTrailMapper.selectPage(page, queryWrapper);
        List<EducationCourseClassTrail> list = pageResult.getRecords().stream().map(EducationCourseClassTrailConverter.INSTANCE::convert).toList();

        PageResponse<List<EducationCourseClassTrail>> listPageResponse = pageConverter(pageResult);
        listPageResponse.setData(list);
        return listPageResponse;
    }

    @Override
    public EducationCourseClassTrail findById(Long id) {
        LambdaQueryWrapper<EducationCourseClassTrailDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EducationCourseClassTrailDO::getId, id);
        queryWrapper.eq(EducationCourseClassTrailDO::getDeleted, DeleteEnum.NO.getCode());
        EducationCourseClassTrailDO educationCourseClassTrailDO = educationCourseClassTrailMapper.selectOne(queryWrapper);
        return EducationCourseClassTrailConverter.INSTANCE.convert(educationCourseClassTrailDO);
    }

    @Override
    public List<EducationCourseClassTrail> findByClassNo(String classNo) {
        LambdaQueryWrapper<EducationCourseClassTrailDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EducationCourseClassTrailDO::getClassNo, classNo);
        queryWrapper.eq(EducationCourseClassTrailDO::getDeleted, DeleteEnum.NO.getCode());
        List<EducationCourseClassTrailDO> educationCourseClassTrailDOList = educationCourseClassTrailMapper.selectList(queryWrapper);
        return EducationCourseClassTrailConverter.INSTANCE.convert2List(educationCourseClassTrailDOList);
    }

    @Override
    public List<EducationCourseClassTrail> findByCourseNo(String courseNo) {
        LambdaQueryWrapper<EducationCourseClassTrailDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EducationCourseClassTrailDO::getCourseNo, courseNo);
        queryWrapper.eq(EducationCourseClassTrailDO::getDeleted, DeleteEnum.NO.getCode());
        List<EducationCourseClassTrailDO> educationCourseClassTrailDOList = educationCourseClassTrailMapper.selectList(queryWrapper);
        return EducationCourseClassTrailConverter.INSTANCE.convert2List(educationCourseClassTrailDOList);
    }

    @Override
    public Long exists(Long id) {
        LambdaQueryWrapper<EducationCourseClassTrailDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EducationCourseClassTrailDO::getId, id);
        queryWrapper.eq(EducationCourseClassTrailDO::getDeleted, DeleteEnum.NO.getCode());
        return educationCourseClassTrailMapper.selectCount(queryWrapper);
    }

    @Override
    public Integer modify(EducationCourseClassTrail educationCourseClassTrail) {
        EducationCourseClassTrailDO educationCourseClassTrailDO = EducationCourseClassTrailConverter.INSTANCE.convert2DO(educationCourseClassTrail)
                .setUpdater(educationCourseClassTrail.getOperator())
                .setUpdateTime(DateUtils.now());

        return educationCourseClassTrailMapper.updateById(educationCourseClassTrailDO);
    }

    @Override
    public Integer remove(Long id, String operator) {
        EducationCourseClassTrailDO educationCourseClassTrailDO = new EducationCourseClassTrailDO()
                .setId(id)
                .setDeleted(DeleteEnum.YES.getCode())
                .setUpdater(operator)
                .setUpdateTime(DateUtils.now());
        return educationCourseClassTrailMapper.updateById(educationCourseClassTrailDO);
    }
}
