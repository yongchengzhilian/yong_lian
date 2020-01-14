package com.aidou.dao.mapper;

import com.aidou.dao.entity.TbUserRelation;
import com.aidou.dao.entity.TbUserRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbUserRelationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user_relation
     *
     * @mbggenerated Thu Nov 14 15:06:09 CST 2019
     */
    int countByExample(TbUserRelationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user_relation
     *
     * @mbggenerated Thu Nov 14 15:06:09 CST 2019
     */
    int deleteByExample(TbUserRelationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user_relation
     *
     * @mbggenerated Thu Nov 14 15:06:09 CST 2019
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user_relation
     *
     * @mbggenerated Thu Nov 14 15:06:09 CST 2019
     */
    int insert(TbUserRelation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user_relation
     *
     * @mbggenerated Thu Nov 14 15:06:09 CST 2019
     */
    int insertSelective(TbUserRelation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user_relation
     *
     * @mbggenerated Thu Nov 14 15:06:09 CST 2019
     */
    List<TbUserRelation> selectByExample(TbUserRelationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user_relation
     *
     * @mbggenerated Thu Nov 14 15:06:09 CST 2019
     */
    TbUserRelation selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user_relation
     *
     * @mbggenerated Thu Nov 14 15:06:09 CST 2019
     */
    int updateByExampleSelective(@Param("record") TbUserRelation record, @Param("example") TbUserRelationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user_relation
     *
     * @mbggenerated Thu Nov 14 15:06:09 CST 2019
     */
    int updateByExample(@Param("record") TbUserRelation record, @Param("example") TbUserRelationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user_relation
     *
     * @mbggenerated Thu Nov 14 15:06:09 CST 2019
     */
    int updateByPrimaryKeySelective(TbUserRelation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user_relation
     *
     * @mbggenerated Thu Nov 14 15:06:09 CST 2019
     */
    int updateByPrimaryKey(TbUserRelation record);
}