package com.hotdog.saas.infra.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hotdog.saas.domain.model.EducationCourseTypeRelation;
import com.hotdog.saas.domain.repository.EducationCourseTypeRelationRepository;
import com.hotdog.saas.infra.converter.EducationCourseTypeRelationConverter;
import com.hotdog.saas.infra.dao.EducationCourseTypeRelationMapper;
import com.hotdog.saas.infra.entity.EducationCourseTypeRelationDO;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EducationCourseTypeRelationRepositoryImpl extends AbstractBaseRepository implements EducationCourseTypeRelationRepository {

    private final EducationCourseTypeRelationMapper educationCourseTypeRelationMapper;

    public EducationCourseTypeRelationRepositoryImpl(EducationCourseTypeRelationMapper educationCourseTypeRelationMapper) {
        this.educationCourseTypeRelationMapper = educationCourseTypeRelationMapper;
    }

    @Override
    public Long save(EducationCourseTypeRelation educationCourseTypeRelation) {
        EducationCourseTypeRelationDO educationCourseTypeRelationDO = EducationCourseTypeRelationConverter.INSTANCE.convert2DO(educationCourseTypeRelation);
        educationCourseTypeRelationMapper.insert(educationCourseTypeRelationDO);
        return educationCourseTypeRelationDO.getId();
    }

    @Override
    public EducationCourseTypeRelation findByCourseNo(String courseNo) {
        LambdaQueryWrapper<EducationCourseTypeRelationDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(EducationCourseTypeRelationDO::getCourseNo, courseNo);
        EducationCourseTypeRelationDO educationCourseTypeRelationDO = educationCourseTypeRelationMapper.selectOne(lambdaQueryWrapper);
        return EducationCourseTypeRelationConverter.INSTANCE.convert(educationCourseTypeRelationDO);
    }

    @Override
    public List<EducationCourseTypeRelation> findByTypeId(Long typeId) {
        LambdaQueryWrapper<EducationCourseTypeRelationDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(EducationCourseTypeRelationDO::getTypeId, typeId);
        List<EducationCourseTypeRelationDO> educationCourseTypeRelationDOList = educationCourseTypeRelationMapper.selectList(lambdaQueryWrapper);
        return educationCourseTypeRelationDOList.stream().map(EducationCourseTypeRelationConverter.INSTANCE::convert).toList();
    }

    @Override
    public Integer removeByCourseNo(String courseNo) {
        LambdaQueryWrapper<EducationCourseTypeRelationDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(EducationCourseTypeRelationDO::getCourseNo, courseNo);
        return educationCourseTypeRelationMapper.delete(lambdaQueryWrapper);
    }
}
