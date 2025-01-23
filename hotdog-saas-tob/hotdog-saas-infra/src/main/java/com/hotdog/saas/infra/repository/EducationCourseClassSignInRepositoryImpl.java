package com.hotdog.saas.infra.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hotdog.saas.domain.enums.common.DeleteEnum;
import com.hotdog.saas.domain.model.EducationCourseClassSignIn;
import com.hotdog.saas.domain.model.page.PageRequest;
import com.hotdog.saas.domain.model.page.PageResponse;
import com.hotdog.saas.domain.repository.EducationCourseClassSignInRepository;
import com.hotdog.saas.domain.utils.DateUtils;
import com.hotdog.saas.infra.converter.EducationCourseClassSignInConverter;
import com.hotdog.saas.infra.dao.EducationCourseClassSignInMapper;
import com.hotdog.saas.infra.entity.EducationCourseClassSignInDO;
import com.hotdog.saas.infra.entity.EducationCourseClassTrailDO;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class EducationCourseClassSignInRepositoryImpl extends AbstractBaseRepository implements EducationCourseClassSignInRepository {

    private final EducationCourseClassSignInMapper educationCourseClassSignInMapper;

    public EducationCourseClassSignInRepositoryImpl(EducationCourseClassSignInMapper educationCourseClassSignInMapper) {
        this.educationCourseClassSignInMapper = educationCourseClassSignInMapper;
    }

    @Override
    public PageResponse<List<EducationCourseClassSignIn>> listPage(EducationCourseClassSignIn educationCourseClassSignIn, PageRequest pageRequest) {
        Page<EducationCourseClassSignInDO> page = new Page<>(pageRequest.getPageIndex(), pageRequest.getPageSize());
        LambdaQueryWrapper<EducationCourseClassSignInDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EducationCourseClassSignInDO::getWechatId, educationCourseClassSignIn.getWechatId());
        queryWrapper.eq(EducationCourseClassSignInDO::getDeleted, DeleteEnum.NO.getCode());
        queryWrapper.eq(Objects.nonNull(educationCourseClassSignIn.getStatus()), EducationCourseClassSignInDO::getStatus, educationCourseClassSignIn.getStatus());
        queryWrapper.like(StringUtils.isNotEmpty(educationCourseClassSignIn.getCourseNo()), EducationCourseClassSignInDO::getCourseNo, educationCourseClassSignIn.getCourseNo());
        queryWrapper.like(StringUtils.isNotEmpty(educationCourseClassSignIn.getClassNo()), EducationCourseClassSignInDO::getClassNo, educationCourseClassSignIn.getClassNo());

        queryWrapper.orderByDesc(EducationCourseClassSignInDO::getCreateTime);

        Page<EducationCourseClassSignInDO> pageResult = educationCourseClassSignInMapper.selectPage(page, queryWrapper);
        List<EducationCourseClassSignIn> list = pageResult.getRecords().stream().map(EducationCourseClassSignInConverter.INSTANCE::convert).toList();

        PageResponse<List<EducationCourseClassSignIn>> listPageResponse = pageConverter(pageResult);
        listPageResponse.setData(list);
        return listPageResponse;
    }

    @Override
    public Long exists(Long id) {
        LambdaQueryWrapper<EducationCourseClassSignInDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EducationCourseClassSignInDO::getId, id);
        queryWrapper.eq(EducationCourseClassSignInDO::getDeleted, DeleteEnum.NO.getCode());
        return educationCourseClassSignInMapper.selectCount(queryWrapper);
    }

    @Override
    public Integer modify(EducationCourseClassSignIn educationCourseClassSignIn) {
        EducationCourseClassSignInDO educationCourseClassSignInDO = EducationCourseClassSignInConverter.INSTANCE.convert2DO(educationCourseClassSignIn);
        educationCourseClassSignInDO.setUpdater(educationCourseClassSignIn.getOperator())
                .setUpdateTime(DateUtils.now());
        return educationCourseClassSignInMapper.updateById(educationCourseClassSignInDO);
    }

    @Override
    public Integer remove(Long id, String operator) {
        EducationCourseClassSignInDO educationCourseClassSignInDO = new EducationCourseClassSignInDO()
                .setId(id)
                .setDeleted(DeleteEnum.YES.getCode())
                .setUpdater(operator)
                .setUpdateTime(DateUtils.now());
        return educationCourseClassSignInMapper.updateById(educationCourseClassSignInDO);
    }
}
