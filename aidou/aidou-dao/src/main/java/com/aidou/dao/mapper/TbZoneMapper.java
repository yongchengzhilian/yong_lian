package com.aidou.dao.mapper;

import com.aidou.dao.entity.TbZone;
import com.aidou.dao.entity.TbZoneExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbZoneMapper {
    int countByExample(TbZoneExample example);

    int deleteByExample(TbZoneExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TbZone record);

    int insertSelective(TbZone record);

    List<TbZone> selectByExample(TbZoneExample example);

    TbZone selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbZone record, @Param("example") TbZoneExample example);

    int updateByExample(@Param("record") TbZone record, @Param("example") TbZoneExample example);

    int updateByPrimaryKeySelective(TbZone record);

    int updateByPrimaryKey(TbZone record);
}