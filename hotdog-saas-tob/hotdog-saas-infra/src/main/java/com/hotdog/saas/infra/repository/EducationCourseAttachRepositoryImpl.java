package com.hotdog.saas.infra.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.hotdog.saas.domain.enums.common.DeleteEnum;
import com.hotdog.saas.domain.model.EducationCourseAttach;
import com.hotdog.saas.domain.repository.EducationCourseAttachRepository;
import com.hotdog.saas.domain.utils.DateUtils;
import com.hotdog.saas.infra.converter.EducationCourseAttachConverter;
import com.hotdog.saas.infra.dao.EducationCourseAttachMapper;
import com.hotdog.saas.infra.entity.EducationCourseAttachDO;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class EducationCourseAttachRepositoryImpl extends AbstractBaseRepository implements EducationCourseAttachRepository {

    private final EducationCourseAttachMapper educationCourseAttachMapper;

    public EducationCourseAttachRepositoryImpl(EducationCourseAttachMapper educationCourseAttachMapper) {
        this.educationCourseAttachMapper = educationCourseAttachMapper;
    }

    @Override
    public Integer batchSave(List<EducationCourseAttach> list) {
        List<EducationCourseAttachDO> educationCourseAttachDOList = EducationCourseAttachConverter.INSTANCE.convert2DOList(list);
        LocalDateTime now = DateUtils.now();
        educationCourseAttachDOList.forEach(educationCourseAttachDO -> educationCourseAttachDO.setCreateTime(now).setUpdateTime(now));
        return educationCourseAttachMapper.insert(educationCourseAttachDOList).size();
    }

    @Override
    public List<EducationCourseAttach> findByCourseNo(String courseNo) {
        LambdaQueryWrapper<EducationCourseAttachDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EducationCourseAttachDO::getCourseNo, courseNo);
        queryWrapper.eq(EducationCourseAttachDO::getDeleted, DeleteEnum.NO.getCode());
        List<EducationCourseAttachDO> educationCourseAttachList = educationCourseAttachMapper.selectList(queryWrapper);
        return educationCourseAttachList.stream().map(EducationCourseAttachConverter.INSTANCE::convert).toList();
    }

    @Override
    public Integer batchRemove(List<Long> idList, String operator) {
        LambdaUpdateWrapper<EducationCourseAttachDO> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(EducationCourseAttachDO::getDeleted, DeleteEnum.YES.getCode())
                .set(EducationCourseAttachDO::getUpdater, operator)
                .set(EducationCourseAttachDO::getUpdateTime, DateUtils.now());
        updateWrapper.in(EducationCourseAttachDO::getId, idList);
        return educationCourseAttachMapper.update(updateWrapper);
    }
}
