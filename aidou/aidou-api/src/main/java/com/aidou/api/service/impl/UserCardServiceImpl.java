package com.aidou.api.service.impl;

import com.aidou.api.service.UserCardService;
import com.aidou.dao.entity.TbCard;
import com.aidou.dao.mapper.TbCardMapper;
import com.aidou.util.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yingjiafeng
 * @version 创建时间：2019/8/4 13:17
 */
@Service
public class UserCardServiceImpl  implements UserCardService {

    @Autowired
    private TbCardMapper  tbCardMapper;

    @Override
    public String findCadNameById(Long uid) throws BizException {
        TbCard tbCard = tbCardMapper.selectByPrimaryKey(uid);
        if (tbCard==null){
            return null;
        }
      return   tbCard.getName();
    }
}
