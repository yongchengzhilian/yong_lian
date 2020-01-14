package com.aidou.api.service.impl;

import com.aidou.api.service.IdService;
import com.aidou.dao.entity.SysConfig;
import com.aidou.dao.entity.SysConfigExample;
import com.aidou.dao.mapper.SysConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Copyright@www.lanhusoft.com.
 * Author:yingjiafeng
 * Date:2019/11/13
 * Description:
 */
@Service
public class IdServiceImpl implements IdService {

    @Autowired
    private SysConfigMapper sysConfigMapper;


    @Transactional
    @Override
    public Long getUid() {
        SysConfig sysConfig = sysConfigMapper.selectByPrimaryKey(90);
        Long idIncrease = sysConfig.getIdIncrease();
        idIncrease++;
        sysConfig.setIdIncrease(idIncrease);
        sysConfig.setUpdated(new Date());
        sysConfigMapper.updateByPrimaryKeySelective(sysConfig);
        return idIncrease;
    }
}
