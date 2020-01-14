package com.aidou.api.service.impl;

import com.aidou.api.service.AccountService;
import com.aidou.api.vo.request.account.AccountLogoutRequest;
import com.aidou.dao.entity.SysUserLogout;
import com.aidou.dao.entity.TbUser;
import com.aidou.dao.mapper.SysUserLogoutMapper;
import com.aidou.dao.mapper.TbUserMapper;
import com.aidou.util.entity.CurrentUser;
import com.aidou.util.enums.AppStatusEnum;
import com.aidou.util.enums.EnumUtil;
import com.aidou.util.enums.UserStatusEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Copyright@www.lanhusoft.com.
 * Author:yingjiafeng
 * Date:2019/11/9
 * Description:
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private SysUserLogoutMapper sysUserLogoutMapper;

    @Autowired
    private TbUserMapper tbUserMapper;


    @Override
    public void logout(AccountLogoutRequest accountLogoutRequest) {
        Long id = CurrentUser.get().getId();
        //修改为注销状态
        TbUser tbUser = tbUserMapper.selectByPrimaryKey(id);
        tbUser.setAccountStatus(accountLogoutRequest.getAccountStatus());
        tbUser.setAccountStatusName(EnumUtil.getAccountNameByCode(accountLogoutRequest.getAccountStatus()));
        tbUser.setUpdated(new Date());
        tbUserMapper.updateByPrimaryKey(tbUser);
        if (accountLogoutRequest.getAccountStatus()==AppStatusEnum.ACCOUNT_STATUS5.getIndex()){
            //添加注销记录
            SysUserLogout sysUserLogout=new SysUserLogout();
            sysUserLogout.setCreated(new Date());
            sysUserLogout.setUserId(id.toString());
            if (StringUtils.isNotEmpty(accountLogoutRequest.getRemarks())){
                sysUserLogout.setLogouStatusCode(AppStatusEnum.LOGOUT_STATUS6.getIndex());
                sysUserLogout.setLogouStatusVal(accountLogoutRequest.getRemarks());
            }else {
                sysUserLogout.setLogouStatusCode(accountLogoutRequest.getLogoutCode());
                sysUserLogout.setLogouStatusVal(EnumUtil.getLogoutValByCode(accountLogoutRequest.getLogoutCode()));
            }
            sysUserLogoutMapper.insertSelective(sysUserLogout);
        }
    }
}
