package com.hotdog.saas.infra.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hotdog.saas.domain.enums.common.DeleteEnum;
import com.hotdog.saas.domain.model.EducationCourse;
import com.hotdog.saas.domain.model.page.PageRequest;
import com.hotdog.saas.domain.model.page.PageResponse;
import com.hotdog.saas.domain.repository.EducationCourseRepository;
import com.hotdog.saas.domain.utils.DateUtils;
import com.hotdog.saas.infra.converter.EducationCourseConverter;
import com.hotdog.saas.infra.dao.EducationCourseMapper;
import com.hotdog.saas.infra.entity.EducationCourseDO;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class EducationCourseRepositoryImpl extends AbstractBaseRepository implements EducationCourseRepository {

    private final EducationCourseMapper educationCourseMapper;

    public EducationCourseRepositoryImpl(EducationCourseMapper educationCourseMapper) {
        this.educationCourseMapper = educationCourseMapper;
    }

    @Override
    public Integer save(EducationCourse educationCourse) {
        EducationCourseDO educationCourseDO = EducationCourseConverter.INSTANCE.convert2DO(educationCourse);
        LocalDateTime now = DateUtils.now();
        educationCourseDO.setCreator(educationCourse.getOperator()).setCreateTime(now)
                .setUpdater(educationCourse.getOperator()).setUpdateTime(now);

        return educationCourseMapper.insert(educationCourseDO);
    }

    @Override
    public PageResponse<List<EducationCourse>> listPage(EducationCourse educationCourse, PageRequest pageRequest) {
        Page<EducationCourseDO> page = new Page<>(pageRequest.getPageIndex(), pageRequest.getPageSize());
        LambdaQueryWrapper<EducationCourseDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EducationCourseDO::getWechatId, educationCourse.getWechatId());
        queryWrapper.eq(EducationCourseDO::getDeleted, DeleteEnum.NO.getCode());
        queryWrapper.orderByDesc(EducationCourseDO::getCreateTime);

        Page<EducationCourseDO> pageResult = educationCourseMapper.selectPage(page, queryWrapper);
        List<EducationCourse> list = pageResult.getRecords().stream().map(EducationCourseConverter.INSTANCE::convert).toList();

        PageResponse<List<EducationCourse>> listPageResponse = pageConverter(pageResult);
        listPageResponse.setData(list);
        return listPageResponse;
    }

    @Override
    public List<EducationCourse> list(EducationCourse educationCourse) {
        LambdaQueryWrapper<EducationCourseDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EducationCourseDO::getWechatId, educationCourse.getWechatId());
        queryWrapper.eq(EducationCourseDO::getDeleted, DeleteEnum.NO.getCode());
        queryWrapper.orderByDesc(EducationCourseDO::getCreateTime);

        List<EducationCourseDO> educationCourseDOList = educationCourseMapper.selectList(queryWrapper);
        return educationCourseDOList.stream().map(EducationCourseConverter.INSTANCE::convert).toList();
    }

    @Override
    public EducationCourse findByCourseNo(String courseNo) {
        LambdaQueryWrapper<EducationCourseDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EducationCourseDO::getCourseNo, courseNo);
        queryWrapper.eq(EducationCourseDO::getDeleted, DeleteEnum.NO.getCode());
        EducationCourseDO educationCourseDO = educationCourseMapper.selectOne(queryWrapper);
        return EducationCourseConverter.INSTANCE.convert(educationCourseDO);
    }

    @Override
    public Long exists(String courseNo, Long tenantId) {
        LambdaQueryWrapper<EducationCourseDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EducationCourseDO::getDeleted, DeleteEnum.NO.getCode())
                .eq(EducationCourseDO::getTenantId, tenantId)
                .eq(EducationCourseDO::getCourseNo, courseNo);
        return educationCourseMapper.selectCount(queryWrapper);
    }

    @Override
    public Long existsByName(String name, Long tenantId) {
        LambdaQueryWrapper<EducationCourseDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EducationCourseDO::getDeleted, DeleteEnum.NO.getCode())
                .eq(EducationCourseDO::getTenantId, tenantId)
                .eq(EducationCourseDO::getName, name);
        return educationCourseMapper.selectCount(queryWrapper);
    }

    @Override
    public Integer modify(EducationCourse educationCourse) {
        EducationCourseDO educationCourseDO = EducationCourseConverter.INSTANCE.convert2DO(educationCourse)
                .setUpdater(educationCourse.getOperator())
                .setUpdateTime(DateUtils.now());

        LambdaUpdateWrapper<EducationCourseDO> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(EducationCourseDO::getCourseNo, educationCourse.getCourseNo());
        return educationCourseMapper.update(educationCourseDO, updateWrapper);
    }

    @Override
    public Integer remove(String courseNo, String operator) {
        EducationCourseDO educationCourseDO = new EducationCourseDO()
                .setCourseNo(courseNo)
                .setDeleted(DeleteEnum.YES.getCode())
                .setUpdater(operator)
                .setUpdateTime(DateUtils.now());

        LambdaUpdateWrapper<EducationCourseDO> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(EducationCourseDO::getCourseNo, courseNo);
        return educationCourseMapper.update(educationCourseDO, updateWrapper);
    }
}
