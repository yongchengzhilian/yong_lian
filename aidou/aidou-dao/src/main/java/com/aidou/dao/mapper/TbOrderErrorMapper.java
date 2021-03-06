package com.aidou.dao.mapper;

import com.aidou.dao.entity.TbOrderError;
import com.aidou.dao.entity.TbOrderErrorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbOrderErrorMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_order_error
     *
     * @mbggenerated Sat Nov 23 18:00:44 CST 2019
     */
    int countByExample(TbOrderErrorExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_order_error
     *
     * @mbggenerated Sat Nov 23 18:00:44 CST 2019
     */
    int deleteByExample(TbOrderErrorExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_order_error
     *
     * @mbggenerated Sat Nov 23 18:00:44 CST 2019
     */
    int deleteByPrimaryKey(String orderid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_order_error
     *
     * @mbggenerated Sat Nov 23 18:00:44 CST 2019
     */
    int insert(TbOrderError record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_order_error
     *
     * @mbggenerated Sat Nov 23 18:00:44 CST 2019
     */
    int insertSelective(TbOrderError record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_order_error
     *
     * @mbggenerated Sat Nov 23 18:00:44 CST 2019
     */
    List<TbOrderError> selectByExample(TbOrderErrorExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_order_error
     *
     * @mbggenerated Sat Nov 23 18:00:44 CST 2019
     */
    TbOrderError selectByPrimaryKey(String orderid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_order_error
     *
     * @mbggenerated Sat Nov 23 18:00:44 CST 2019
     */
    int updateByExampleSelective(@Param("record") TbOrderError record, @Param("example") TbOrderErrorExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_order_error
     *
     * @mbggenerated Sat Nov 23 18:00:44 CST 2019
     */
    int updateByExample(@Param("record") TbOrderError record, @Param("example") TbOrderErrorExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_order_error
     *
     * @mbggenerated Sat Nov 23 18:00:44 CST 2019
     */
    int updateByPrimaryKeySelective(TbOrderError record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_order_error
     *
     * @mbggenerated Sat Nov 23 18:00:44 CST 2019
     */
    int updateByPrimaryKey(TbOrderError record);
}