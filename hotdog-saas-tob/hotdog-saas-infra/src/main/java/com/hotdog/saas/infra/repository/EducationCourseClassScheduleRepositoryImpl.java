package com.hotdog.saas.infra.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hotdog.saas.domain.constant.Constants;
import com.hotdog.saas.domain.enums.common.DeleteEnum;
import com.hotdog.saas.domain.enums.education.CourseClassScheduleStatusEnum;
import com.hotdog.saas.domain.model.EducationCourseClassSchedule;
import com.hotdog.saas.domain.model.page.PageRequest;
import com.hotdog.saas.domain.model.page.PageResponse;
import com.hotdog.saas.domain.repository.EducationCourseClassScheduleRepository;
import com.hotdog.saas.domain.utils.DateUtils;
import com.hotdog.saas.infra.converter.EducationCourseClassScheduleConverter;
import com.hotdog.saas.infra.dao.EducationCourseClassScheduleMapper;
import com.hotdog.saas.infra.entity.EducationCourseClassScheduleDO;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class EducationCourseClassScheduleRepositoryImpl extends AbstractBaseRepository implements EducationCourseClassScheduleRepository {

    private final EducationCourseClassScheduleMapper educationCourseClassScheduleMapper;

    public EducationCourseClassScheduleRepositoryImpl(EducationCourseClassScheduleMapper educationCourseClassScheduleMapper) {
        this.educationCourseClassScheduleMapper = educationCourseClassScheduleMapper;
    }

    @Override
    public Long save(EducationCourseClassSchedule educationCourseClassSchedule) {
        EducationCourseClassScheduleDO educationCourseClassScheduleDO = EducationCourseClassScheduleConverter.INSTANCE.convert2DO(educationCourseClassSchedule);
        LocalDateTime now = DateUtils.now();
        educationCourseClassScheduleDO.setCreator(educationCourseClassSchedule.getOperator()).setCreateTime(now)
                .setUpdater(educationCourseClassSchedule.getOperator()).setUpdateTime(now);
        educationCourseClassScheduleMapper.insert(educationCourseClassScheduleDO);
        return educationCourseClassScheduleDO.getId();
    }

    @Override
    public PageResponse<List<EducationCourseClassSchedule>> listPage(EducationCourseClassSchedule educationCourseClassSchedule, PageRequest pageRequest) {
        Page<EducationCourseClassScheduleDO> page = new Page<>(pageRequest.getPageIndex(), pageRequest.getPageSize());
        LambdaQueryWrapper<EducationCourseClassScheduleDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EducationCourseClassScheduleDO::getClassNo, educationCourseClassSchedule.getClassNo());
        queryWrapper.eq(EducationCourseClassScheduleDO::getDeleted, DeleteEnum.NO.getCode());
        queryWrapper.orderByAsc(EducationCourseClassScheduleDO::getClassBeginTime);

        Page<EducationCourseClassScheduleDO> pageResult = educationCourseClassScheduleMapper.selectPage(page, queryWrapper);
        List<EducationCourseClassSchedule> list = pageResult.getRecords().stream().map(EducationCourseClassScheduleConverter.INSTANCE::convert).toList();

        PageResponse<List<EducationCourseClassSchedule>> listPageResponse = pageConverter(pageResult);
        listPageResponse.setData(list);
        return listPageResponse;
    }

    @Override
    public EducationCourseClassSchedule findById(Long id) {
        LambdaQueryWrapper<EducationCourseClassScheduleDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EducationCourseClassScheduleDO::getId, id);
        queryWrapper.eq(EducationCourseClassScheduleDO::getDeleted, DeleteEnum.NO.getCode());
        EducationCourseClassScheduleDO educationCourseClassScheduleDO = educationCourseClassScheduleMapper.selectOne(queryWrapper);
        return EducationCourseClassScheduleConverter.INSTANCE.convert(educationCourseClassScheduleDO);
    }

    @Override
    public List<EducationCourseClassSchedule> findByClassNo(String classNo) {
        LambdaQueryWrapper<EducationCourseClassScheduleDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EducationCourseClassScheduleDO::getClassNo, classNo);
        queryWrapper.eq(EducationCourseClassScheduleDO::getDeleted, DeleteEnum.NO.getCode());

        List<EducationCourseClassScheduleDO> educationCourseClassScheduleDOList = educationCourseClassScheduleMapper.selectList(queryWrapper);

        return EducationCourseClassScheduleConverter.INSTANCE.convert2List(educationCourseClassScheduleDOList);
    }

    @Override
    public Long exists(Long id) {
        LambdaQueryWrapper<EducationCourseClassScheduleDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EducationCourseClassScheduleDO::getId, id);
        queryWrapper.eq(EducationCourseClassScheduleDO::getDeleted, DeleteEnum.NO.getCode());

        return educationCourseClassScheduleMapper.selectCount(queryWrapper);
    }

    @Override
    public Long existsBetweenTime(String classNo, LocalDateTime beginTime, LocalDateTime endTime) {
        LambdaQueryWrapper<EducationCourseClassScheduleDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.ge(EducationCourseClassScheduleDO::getClassBeginTime, beginTime);
        queryWrapper.le(EducationCourseClassScheduleDO::getClassEndTime, endTime);
        queryWrapper.eq(EducationCourseClassScheduleDO::getClassNo, classNo);
        queryWrapper.eq(EducationCourseClassScheduleDO::getDeleted, DeleteEnum.NO.getCode());

        return educationCourseClassScheduleMapper.selectCount(queryWrapper);
    }

    @Override
    public Integer modify(EducationCourseClassSchedule educationCourseClassSchedule) {
        EducationCourseClassScheduleDO educationCourseClassScheduleDO = EducationCourseClassScheduleConverter.INSTANCE.convert2DO(educationCourseClassSchedule)
                .setUpdater(educationCourseClassSchedule.getOperator())
                .setUpdateTime(DateUtils.now());
        return educationCourseClassScheduleMapper.updateById(educationCourseClassScheduleDO);
    }

    @Override
    public Integer modifyStatusInIdList(List<Long> idList, CourseClassScheduleStatusEnum statusEnum) {
        LambdaUpdateWrapper<EducationCourseClassScheduleDO> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.in(EducationCourseClassScheduleDO::getId, idList);
        EducationCourseClassScheduleDO educationCourseClassScheduleDO = new EducationCourseClassScheduleDO()
                .setStatus(statusEnum.getCode())
                .setUpdater(Constants.SYSTEM_OPERATOR)
                .setUpdateTime(DateUtils.now());
        return educationCourseClassScheduleMapper.update(educationCourseClassScheduleDO, updateWrapper);
    }

    @Override
    public Integer remove(Long id, String operator) {
        EducationCourseClassScheduleDO educationCourseClassScheduleDO = new EducationCourseClassScheduleDO()
                .setId(id)
                .setDeleted(DeleteEnum.YES.getCode())
                .setUpdater(operator)
                .setUpdateTime(DateUtils.now());
        return educationCourseClassScheduleMapper.updateById(educationCourseClassScheduleDO);
    }
}
