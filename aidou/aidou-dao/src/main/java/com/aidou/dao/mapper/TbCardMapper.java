package com.aidou.dao.mapper;

import com.aidou.dao.entity.TbCard;
import com.aidou.dao.entity.TbCardExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbCardMapper {
    int countByExample(TbCardExample example);

    int deleteByExample(TbCardExample example);

    int deleteByPrimaryKey(Long uid);

    int insert(TbCard record);

    int insertSelective(TbCard record);

    List<TbCard> selectByExample(TbCardExample example);

    TbCard selectByPrimaryKey(Long uid);

    int updateByExampleSelective(@Param("record") TbCard record, @Param("example") TbCardExample example);

    int updateByExample(@Param("record") TbCard record, @Param("example") TbCardExample example);

    int updateByPrimaryKeySelective(TbCard record);

    int updateByPrimaryKey(TbCard record);
}