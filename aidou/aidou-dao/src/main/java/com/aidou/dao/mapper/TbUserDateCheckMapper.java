package com.aidou.dao.mapper;

import com.aidou.dao.entity.TbUserDateCheck;
import com.aidou.dao.entity.TbUserDateCheckExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbUserDateCheckMapper {
    int countByExample(TbUserDateCheckExample example);

    int deleteByExample(TbUserDateCheckExample example);

    int deleteByPrimaryKey(Long uid);

    int insert(TbUserDateCheck record);

    int insertSelective(TbUserDateCheck record);

    List<TbUserDateCheck> selectByExample(TbUserDateCheckExample example);

    TbUserDateCheck selectByPrimaryKey(Long uid);

    int updateByExampleSelective(@Param("record") TbUserDateCheck record, @Param("example") TbUserDateCheckExample example);

    int updateByExample(@Param("record") TbUserDateCheck record, @Param("example") TbUserDateCheckExample example);

    int updateByPrimaryKeySelective(TbUserDateCheck record);

    int updateByPrimaryKey(TbUserDateCheck record);
}