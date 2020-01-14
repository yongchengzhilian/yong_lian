package com.aidou.api.service;

import com.aidou.api.enums.LineSourceType;
import com.aidou.dao.entity.TbLine;
import com.aidou.util.exception.BizException;

import java.util.List;

/**
 * Created by yingjiafeng on 2019/5/22.
 */
public interface LineService {


    /**
     * 获取红线数量
     * @param uid
     * @return
     * @throws BizException
     */
    Integer  findRedCountByUid(Long uid)throws BizException;


    Integer  addLine(Long uid, LineSourceType lineSourceType,Integer  num) throws BizException;


    /**
     * 获取用户红线数量
     * @param uid
     * @return
     * @throws Exception
     */
    List<TbLine> findLineByUid(Long uid) throws BizException;


    /**
     * 删除用户红线
     * @param id
     * @return
     */
    void deleteLineByUid(Long id,String remarks) throws BizException;

}
