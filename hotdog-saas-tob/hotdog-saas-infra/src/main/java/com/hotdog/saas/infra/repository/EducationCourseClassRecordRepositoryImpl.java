package com.hotdog.saas.infra.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hotdog.saas.domain.enums.common.DeleteEnum;
import com.hotdog.saas.domain.model.EducationCourseClassRecord;
import com.hotdog.saas.domain.model.page.PageRequest;
import com.hotdog.saas.domain.model.page.PageResponse;
import com.hotdog.saas.domain.repository.EducationCourseClassRecordRepository;
import com.hotdog.saas.domain.utils.DateUtils;
import com.hotdog.saas.infra.converter.EducationCourseClassRecordConverter;
import com.hotdog.saas.infra.dao.EducationCourseClassRecordMapper;
import com.hotdog.saas.infra.entity.EducationCourseClassRecordDO;

import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class EducationCourseClassRecordRepositoryImpl extends AbstractBaseRepository implements EducationCourseClassRecordRepository {

    private final EducationCourseClassRecordMapper educationCourseClassRecordMapper;

    public EducationCourseClassRecordRepositoryImpl(EducationCourseClassRecordMapper educationCourseClassRecordMapper) {
        this.educationCourseClassRecordMapper = educationCourseClassRecordMapper;
    }

    @Override
    public Integer batchSave(List<EducationCourseClassRecord> list) {
        if (CollectionUtils.isEmpty(list)) {
            return 0;
        }
        LocalDateTime now = DateUtils.now();
        List<EducationCourseClassRecordDO> educationCourseClassRecordDOList = list.stream().map(x ->
                EducationCourseClassRecordConverter.INSTANCE.convert2DO(x)
                        .setCreator(x.getOperator()).setCreateTime(now)
                        .setUpdater(x.getOperator()).setUpdateTime(now)).toList();

        return educationCourseClassRecordMapper.insert(educationCourseClassRecordDOList).size();
    }

    @Override
    public PageResponse<List<EducationCourseClassRecord>> listPage(EducationCourseClassRecord educationCourseClassRecord, PageRequest pageRequest) {
        Page<EducationCourseClassRecordDO> page = new Page<>(pageRequest.getPageIndex(), pageRequest.getPageSize());
        LambdaQueryWrapper<EducationCourseClassRecordDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EducationCourseClassRecordDO::getClassNo, educationCourseClassRecord.getClassNo());
        queryWrapper.eq(EducationCourseClassRecordDO::getDeleted, DeleteEnum.NO.getCode());
        queryWrapper.orderByDesc(EducationCourseClassRecordDO::getCreateTime);

        Page<EducationCourseClassRecordDO> pageResult = educationCourseClassRecordMapper.selectPage(page, queryWrapper);
        List<EducationCourseClassRecord> list = pageResult.getRecords().stream().map(EducationCourseClassRecordConverter.INSTANCE::convert).toList();

        PageResponse<List<EducationCourseClassRecord>> listPageResponse = pageConverter(pageResult);
        listPageResponse.setData(list);
        return listPageResponse;
    }
}
