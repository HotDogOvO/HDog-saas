package com.hotdog.saas.infra.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hotdog.saas.domain.enums.common.DeleteEnum;
import com.hotdog.saas.domain.enums.education.CourseClassStatusEnum;
import com.hotdog.saas.domain.model.EducationCourseClass;
import com.hotdog.saas.domain.model.page.PageRequest;
import com.hotdog.saas.domain.model.page.PageResponse;
import com.hotdog.saas.domain.repository.EducationCourseClassRepository;
import com.hotdog.saas.domain.utils.DateUtils;
import com.hotdog.saas.infra.converter.EducationCourseClassConverter;
import com.hotdog.saas.infra.dao.EducationCourseClassMapper;
import com.hotdog.saas.infra.entity.EducationCourseClassDO;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class EducationCourseClassRepositoryImpl extends AbstractBaseRepository implements EducationCourseClassRepository {

    private final EducationCourseClassMapper educationCourseClassMapper;

    public EducationCourseClassRepositoryImpl(EducationCourseClassMapper educationCourseClassMapper) {
        this.educationCourseClassMapper = educationCourseClassMapper;
    }

    @Override
    public Long save(EducationCourseClass educationCourseClass) {
        EducationCourseClassDO educationCourseClassDO = EducationCourseClassConverter.INSTANCE.convert2DO(educationCourseClass);
        LocalDateTime now = DateUtils.now();
        educationCourseClassDO.setCreator(educationCourseClass.getOperator()).setCreateTime(now)
                .setUpdater(educationCourseClass.getOperator()).setUpdateTime(now);
        educationCourseClassMapper.insert(educationCourseClassDO);
        return educationCourseClassDO.getId();
    }

    @Override
    public PageResponse<List<EducationCourseClass>> listPage(EducationCourseClass educationCourseClass, PageRequest pageRequest) {
        Page<EducationCourseClassDO> page = new Page<>(pageRequest.getPageIndex(), pageRequest.getPageSize());
        LambdaQueryWrapper<EducationCourseClassDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EducationCourseClassDO::getWechatId, educationCourseClass.getWechatId());
        queryWrapper.eq(EducationCourseClassDO::getDeleted, DeleteEnum.NO.getCode());
        queryWrapper.orderByDesc(EducationCourseClassDO::getCreateTime);

        Page<EducationCourseClassDO> pageResult = educationCourseClassMapper.selectPage(page, queryWrapper);
        List<EducationCourseClass> list = pageResult.getRecords().stream().map(EducationCourseClassConverter.INSTANCE::convert).toList();

        PageResponse<List<EducationCourseClass>> listPageResponse = pageConverter(pageResult);
        listPageResponse.setData(list);
        return listPageResponse;
    }

    @Override
    public EducationCourseClass findByClassNo(String classNo) {
        LambdaQueryWrapper<EducationCourseClassDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EducationCourseClassDO::getClassNo, classNo);
        queryWrapper.eq(EducationCourseClassDO::getDeleted, DeleteEnum.NO.getCode());
        EducationCourseClassDO educationCourseClassDO = educationCourseClassMapper.selectOne(queryWrapper);
        return EducationCourseClassConverter.INSTANCE.convert(educationCourseClassDO);
    }

    @Override
    public List<EducationCourseClass> findByCourseNo(String courseNo) {
        LambdaQueryWrapper<EducationCourseClassDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EducationCourseClassDO::getCourseNo, courseNo);
        queryWrapper.eq(EducationCourseClassDO::getDeleted, DeleteEnum.NO.getCode());
        List<EducationCourseClassDO> educationCourseClassDOList = educationCourseClassMapper.selectList(queryWrapper);
        return EducationCourseClassConverter.INSTANCE.convert2List(educationCourseClassDOList);
    }

    @Override
    public List<EducationCourseClass> findBeforeStartTimeAndStatus(LocalDateTime startTime, CourseClassStatusEnum statusEnum) {
        LambdaQueryWrapper<EducationCourseClassDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EducationCourseClassDO::getStatus, statusEnum.getCode());
        queryWrapper.eq(EducationCourseClassDO::getDeleted, DeleteEnum.NO.getCode());
        queryWrapper.lt(EducationCourseClassDO::getClassStartTime, startTime);
        List<EducationCourseClassDO> educationCourseClassDOList = educationCourseClassMapper.selectList(queryWrapper);
        return EducationCourseClassConverter.INSTANCE.convert2List(educationCourseClassDOList);
    }

    @Override
    public Long exists(String classNo) {
        LambdaQueryWrapper<EducationCourseClassDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EducationCourseClassDO::getDeleted, DeleteEnum.NO.getCode())
                .eq(EducationCourseClassDO::getClassNo, classNo);
        return educationCourseClassMapper.selectCount(queryWrapper);
    }

    @Override
    public Long existsByName(String name, Long wechatId) {
        LambdaQueryWrapper<EducationCourseClassDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EducationCourseClassDO::getDeleted, DeleteEnum.NO.getCode())
                .eq(EducationCourseClassDO::getWechatId, wechatId)
                .eq(EducationCourseClassDO::getName, name);
        return educationCourseClassMapper.selectCount(queryWrapper);
    }

    @Override
    public Integer modify(EducationCourseClass educationCourseClass) {
        EducationCourseClassDO educationCourseClassDO = EducationCourseClassConverter.INSTANCE.convert2DO(educationCourseClass)
                .setUpdater(educationCourseClass.getOperator())
                .setUpdateTime(DateUtils.now());

        LambdaUpdateWrapper<EducationCourseClassDO> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(EducationCourseClassDO::getClassNo, educationCourseClass.getClassNo());
        return educationCourseClassMapper.update(educationCourseClassDO, updateWrapper);
    }

    @Override
    public Integer remove(String classNo, String operator) {
        EducationCourseClassDO educationCourseClassDO = new EducationCourseClassDO()
                .setClassNo(classNo)
                .setDeleted(DeleteEnum.YES.getCode())
                .setUpdater(operator)
                .setUpdateTime(DateUtils.now());

        LambdaUpdateWrapper<EducationCourseClassDO> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(EducationCourseClassDO::getClassNo, classNo);
        return educationCourseClassMapper.update(educationCourseClassDO, updateWrapper);
    }
}
