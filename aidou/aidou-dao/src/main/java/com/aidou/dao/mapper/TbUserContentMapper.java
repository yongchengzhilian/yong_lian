package com.aidou.dao.mapper;

import com.aidou.dao.entity.TbUserContent;
import com.aidou.dao.entity.TbUserContentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbUserContentMapper {
    int countByExample(TbUserContentExample example);

    int deleteByExample(TbUserContentExample example);

    int deleteByPrimaryKey(Long uid);

    int insert(TbUserContent record);

    int insertSelective(TbUserContent record);

    List<TbUserContent> selectByExample(TbUserContentExample example);

    TbUserContent selectByPrimaryKey(Long uid);

    int updateByExampleSelective(@Param("record") TbUserContent record, @Param("example") TbUserContentExample example);

    int updateByExample(@Param("record") TbUserContent record, @Param("example") TbUserContentExample example);

    int updateByPrimaryKeySelective(TbUserContent record);

    int updateByPrimaryKey(TbUserContent record);
}