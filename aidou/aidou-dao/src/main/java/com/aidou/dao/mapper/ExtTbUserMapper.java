package com.aidou.dao.mapper;


import com.aidou.dao.entity.TbUserLastTimeVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExtTbUserMapper {


    /**
     * 获取推荐用户
     * @param sex
     * @return
     */
    List<TbUserLastTimeVo> selectLastTimeUserList(@Param("sex") Integer sex);
}