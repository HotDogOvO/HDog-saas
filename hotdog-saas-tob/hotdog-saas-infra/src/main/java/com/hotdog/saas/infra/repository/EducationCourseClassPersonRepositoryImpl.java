package com.hotdog.saas.infra.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hotdog.saas.domain.enums.common.DeleteEnum;
import com.hotdog.saas.domain.model.EducationCourseClassPerson;
import com.hotdog.saas.domain.model.page.PageRequest;
import com.hotdog.saas.domain.model.page.PageResponse;
import com.hotdog.saas.domain.repository.EducationCourseClassPersonRepository;
import com.hotdog.saas.domain.utils.DateUtils;
import com.hotdog.saas.infra.converter.EducationCourseClassPersonConverter;
import com.hotdog.saas.infra.dao.EducationCourseClassPersonMapper;
import com.hotdog.saas.infra.entity.EducationCourseClassPersonDO;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class EducationCourseClassPersonRepositoryImpl extends AbstractBaseRepository implements EducationCourseClassPersonRepository {

    private final EducationCourseClassPersonMapper educationCourseClassPersonMapper;

    public EducationCourseClassPersonRepositoryImpl(EducationCourseClassPersonMapper educationCourseClassPersonMapper) {
        this.educationCourseClassPersonMapper = educationCourseClassPersonMapper;
    }

    @Override
    public Long save(EducationCourseClassPerson educationCourseClassPerson) {
        EducationCourseClassPersonDO educationCourseClassPersonDO = EducationCourseClassPersonConverter.INSTANCE.convert2DO(educationCourseClassPerson);
        LocalDateTime now = DateUtils.now();
        educationCourseClassPersonDO.setCreator(educationCourseClassPerson.getOperator()).setCreateTime(now)
                .setUpdater(educationCourseClassPerson.getOperator()).setUpdateTime(now);
        educationCourseClassPersonMapper.insert(educationCourseClassPersonDO);
        return educationCourseClassPersonDO.getId();
    }

    @Override
    public PageResponse<List<EducationCourseClassPerson>> listPage(EducationCourseClassPerson educationCourseClassPerson, PageRequest pageRequest) {
        Page<EducationCourseClassPersonDO> page = new Page<>(pageRequest.getPageIndex(), pageRequest.getPageSize());
        LambdaQueryWrapper<EducationCourseClassPersonDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EducationCourseClassPersonDO::getClassNo, educationCourseClassPerson.getClassNo());
        queryWrapper.eq(EducationCourseClassPersonDO::getDeleted, DeleteEnum.NO.getCode());
        queryWrapper.orderByDesc(EducationCourseClassPersonDO::getCreateTime);

        Page<EducationCourseClassPersonDO> pageResult = educationCourseClassPersonMapper.selectPage(page, queryWrapper);
        List<EducationCourseClassPerson> list = pageResult.getRecords().stream().map(EducationCourseClassPersonConverter.INSTANCE::convert).toList();

        PageResponse<List<EducationCourseClassPerson>> listPageResponse = pageConverter(pageResult);
        listPageResponse.setData(list);
        return listPageResponse;
    }

    @Override
    public List<EducationCourseClassPerson> findByClassNo(String classNo) {
        LambdaQueryWrapper<EducationCourseClassPersonDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EducationCourseClassPersonDO::getClassNo, classNo);
        queryWrapper.eq(EducationCourseClassPersonDO::getDeleted, DeleteEnum.NO.getCode());
        List<EducationCourseClassPersonDO> educationCourseClassPersonDOList = educationCourseClassPersonMapper.selectList(queryWrapper);
        return educationCourseClassPersonDOList.stream().map(EducationCourseClassPersonConverter.INSTANCE::convert).toList();
    }

    @Override
    public Integer modify(EducationCourseClassPerson educationCourseClassPerson) {
        EducationCourseClassPersonDO educationCourseClassPersonDO = EducationCourseClassPersonConverter.INSTANCE.convert2DO(educationCourseClassPerson);
        educationCourseClassPersonDO.setUpdater(educationCourseClassPerson.getOperator())
                .setUpdateTime(DateUtils.now());
        return educationCourseClassPersonMapper.updateById(educationCourseClassPersonDO);
    }

    @Override
    public Integer remove(Long id, String operator) {
        EducationCourseClassPersonDO educationCourseClassPersonDO = new EducationCourseClassPersonDO()
                .setId(id)
                .setDeleted(DeleteEnum.YES.getCode())
                .setUpdater(operator)
                .setUpdateTime(DateUtils.now());
        return educationCourseClassPersonMapper.updateById(educationCourseClassPersonDO);
    }
}
