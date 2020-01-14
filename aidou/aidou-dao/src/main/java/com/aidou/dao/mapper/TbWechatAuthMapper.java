package com.aidou.dao.mapper;

import com.aidou.dao.entity.TbWechatAuth;
import com.aidou.dao.entity.TbWechatAuthExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbWechatAuthMapper {
    int countByExample(TbWechatAuthExample example);

    int deleteByExample(TbWechatAuthExample example);

    int deleteByPrimaryKey(Long userId);

    int insert(TbWechatAuth record);

    int insertSelective(TbWechatAuth record);

    List<TbWechatAuth> selectByExample(TbWechatAuthExample example);

    TbWechatAuth selectByPrimaryKey(Long userId);

    int updateByExampleSelective(@Param("record") TbWechatAuth record, @Param("example") TbWechatAuthExample example);

    int updateByExample(@Param("record") TbWechatAuth record, @Param("example") TbWechatAuthExample example);

    int updateByPrimaryKeySelective(TbWechatAuth record);

    int updateByPrimaryKey(TbWechatAuth record);
}