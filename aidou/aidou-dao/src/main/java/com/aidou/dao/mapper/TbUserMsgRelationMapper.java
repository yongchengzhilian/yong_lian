package com.aidou.dao.mapper;

import com.aidou.dao.entity.TbUserMsgRelation;
import com.aidou.dao.entity.TbUserMsgRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbUserMsgRelationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user_msg_relation
     *
     * @mbggenerated Thu Jul 25 10:22:26 CST 2019
     */
    int countByExample(TbUserMsgRelationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user_msg_relation
     *
     * @mbggenerated Thu Jul 25 10:22:26 CST 2019
     */
    int deleteByExample(TbUserMsgRelationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user_msg_relation
     *
     * @mbggenerated Thu Jul 25 10:22:26 CST 2019
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user_msg_relation
     *
     * @mbggenerated Thu Jul 25 10:22:26 CST 2019
     */
    int insert(TbUserMsgRelation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user_msg_relation
     *
     * @mbggenerated Thu Jul 25 10:22:26 CST 2019
     */
    int insertSelective(TbUserMsgRelation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user_msg_relation
     *
     * @mbggenerated Thu Jul 25 10:22:26 CST 2019
     */
    List<TbUserMsgRelation> selectByExample(TbUserMsgRelationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user_msg_relation
     *
     * @mbggenerated Thu Jul 25 10:22:26 CST 2019
     */
    TbUserMsgRelation selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user_msg_relation
     *
     * @mbggenerated Thu Jul 25 10:22:26 CST 2019
     */
    int updateByExampleSelective(@Param("record") TbUserMsgRelation record, @Param("example") TbUserMsgRelationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user_msg_relation
     *
     * @mbggenerated Thu Jul 25 10:22:26 CST 2019
     */
    int updateByExample(@Param("record") TbUserMsgRelation record, @Param("example") TbUserMsgRelationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user_msg_relation
     *
     * @mbggenerated Thu Jul 25 10:22:26 CST 2019
     */
    int updateByPrimaryKeySelective(TbUserMsgRelation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user_msg_relation
     *
     * @mbggenerated Thu Jul 25 10:22:26 CST 2019
     */
    int updateByPrimaryKey(TbUserMsgRelation record);
}