package com.hotdog.saas.infra.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hotdog.saas.domain.enums.common.DeleteEnum;
import com.hotdog.saas.domain.model.EducationCourseAttach;
import com.hotdog.saas.domain.repository.EducationCourseAttachRepository;
import com.hotdog.saas.domain.utils.DateUtils;
import com.hotdog.saas.infra.converter.EducationCourseAttachConverter;
import com.hotdog.saas.infra.converter.EducationCourseConverter;
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
    public Integer save(EducationCourseAttach educationCourseAttach) {
        EducationCourseAttachDO educationCourseAttachDO = EducationCourseAttachConverter.INSTANCE.convert2DO(educationCourseAttach);
        LocalDateTime now = DateUtils.now();
        educationCourseAttachDO.setCreator(educationCourseAttach.getOperator()).setCreateTime(now)
                .setUpdater(educationCourseAttach.getOperator()).setUpdateTime(now);
        return educationCourseAttachMapper.insert(educationCourseAttachDO);
    }

    @Override
    public List<EducationCourseAttach> findByCourseNo(String courseNo) {
        LambdaQueryWrapper<EducationCourseAttachDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EducationCourseAttachDO::getCourseNo, courseNo);
        queryWrapper.eq(EducationCourseAttachDO::getDeleted, DeleteEnum.NO.getCode());
        List<EducationCourseAttachDO> educationCourseAttachList = educationCourseAttachMapper.selectList(queryWrapper);
        List<EducationCourseAttach> list = educationCourseAttachList.stream().map(EducationCourseAttachConverter.INSTANCE::convert).toList();
        return list;
    }

    @Override
    public Integer remove(Long id, String operator) {
        EducationCourseAttachDO educationCourseAttachDO = new EducationCourseAttachDO()
                .setId(id)
                .setDeleted(DeleteEnum.YES.getCode())
                .setUpdater(operator)
                .setUpdateTime(DateUtils.now());
        return educationCourseAttachMapper.updateById(educationCourseAttachDO);
    }
}
