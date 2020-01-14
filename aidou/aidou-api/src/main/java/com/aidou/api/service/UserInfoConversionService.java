package com.aidou.api.service;

import com.aidou.api.vo.user.ReommendUserVo;
import com.aidou.dao.entity.TbUser;

import java.util.List;

/**
 * @author yingjiafeng
 * @version 创建时间：2019/7/9 14:07
 */
public interface UserInfoConversionService {

    /**
     * TbUser 转换成 ReommendUserVo
     * @param userList
     * @return
     */
    List<ReommendUserVo>  tbUserConversionReommendUserVo(List<TbUser>  userList);

}
