package com.hotdog.saas.infra.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hotdog.saas.domain.enums.common.DeleteEnum;
import com.hotdog.saas.domain.model.WechatApp;
import com.hotdog.saas.domain.model.page.PageRequest;
import com.hotdog.saas.domain.model.page.PageResponse;
import com.hotdog.saas.domain.repository.WechatAppRepository;
import com.hotdog.saas.domain.utils.DateUtils;
import com.hotdog.saas.infra.converter.WechatAppConverter;
import com.hotdog.saas.infra.dao.WechatAppMapper;
import com.hotdog.saas.infra.entity.WechatAppDO;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class WechatAppRepositoryImpl extends AbstractBaseRepository implements WechatAppRepository {

    private final WechatAppMapper wechatAppMapper;

    public WechatAppRepositoryImpl(WechatAppMapper wechatAppMapper) {
        this.wechatAppMapper = wechatAppMapper;
    }

    @Override
    public Long save(WechatApp wechatApp) {
        WechatAppDO wechatAppDO = WechatAppConverter.INSTANCE.convert2DO(wechatApp);
        LocalDateTime now = DateUtils.now();
        wechatAppDO.setCreator(wechatApp.getOperator()).setCreateTime(now)
                .setUpdater(wechatApp.getOperator()).setUpdateTime(now);
        wechatAppMapper.insert(wechatAppDO);
        return wechatAppDO.getId();
    }

    @Override
    public PageResponse<List<WechatApp>> listPage(WechatApp wechatApp, PageRequest pageRequest) {
        Page<WechatAppDO> page = new Page<>(pageRequest.getPageIndex(), pageRequest.getPageSize());
        LambdaQueryWrapper<WechatAppDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(WechatAppDO::getTenantId, wechatApp.getTenantId());
        queryWrapper.eq(WechatAppDO::getDeleted, DeleteEnum.NO.getCode());
        queryWrapper.orderByDesc(WechatAppDO::getCreateTime);

        Page<WechatAppDO> pageResult = wechatAppMapper.selectPage(page, queryWrapper);
        List<WechatApp> list = pageResult.getRecords().stream().map(WechatAppConverter.INSTANCE::convert).toList();

        PageResponse<List<WechatApp>> listPageResponse = pageConverter(pageResult);
        listPageResponse.setData(list);
        return listPageResponse;
    }

    @Override
    public WechatApp findById(Long id) {
        WechatAppDO wechatAppDO = wechatAppMapper.selectById(id);
        return WechatAppConverter.INSTANCE.convert(wechatAppDO);
    }

    @Override
    public Long exists(Long id) {
        LambdaQueryWrapper<WechatAppDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(WechatAppDO::getId, id).eq(WechatAppDO::getDeleted, DeleteEnum.NO.getCode());
        return wechatAppMapper.selectCount(queryWrapper);

    }

    @Override
    public Long existsByWechatAppId(String wechatAppId) {
        LambdaQueryWrapper<WechatAppDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(WechatAppDO::getWechatAppId, wechatAppId)
                .eq(WechatAppDO::getDeleted, DeleteEnum.NO.getCode());
        return wechatAppMapper.selectCount(queryWrapper);
    }

    @Override
    public Integer modify(WechatApp wechatApp) {
        WechatAppDO wechatAppDO = WechatAppConverter.INSTANCE.convert2DO(wechatApp)
                .setUpdater(wechatApp.getOperator())
                .setUpdateTime(DateUtils.now());
        return wechatAppMapper.updateById(wechatAppDO);
    }

    @Override
    public Integer remove(Long id, String operator) {
        WechatAppDO wechatAppDO = new WechatAppDO()
                .setId(id)
                .setDeleted(DeleteEnum.YES.getCode())
                .setUpdater(operator)
                .setUpdateTime(DateUtils.now());
        return wechatAppMapper.updateById(wechatAppDO);
    }
}
