package com.aidou.dao.mapper;

import com.aidou.dao.entity.SysUserLogout;
import com.aidou.dao.entity.SysUserLogoutExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysUserLogoutMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_logout
     *
     * @mbggenerated Sat Nov 09 17:49:52 CST 2019
     */
    int countByExample(SysUserLogoutExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_logout
     *
     * @mbggenerated Sat Nov 09 17:49:52 CST 2019
     */
    int deleteByExample(SysUserLogoutExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_logout
     *
     * @mbggenerated Sat Nov 09 17:49:52 CST 2019
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_logout
     *
     * @mbggenerated Sat Nov 09 17:49:52 CST 2019
     */
    int insert(SysUserLogout record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_logout
     *
     * @mbggenerated Sat Nov 09 17:49:52 CST 2019
     */
    int insertSelective(SysUserLogout record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_logout
     *
     * @mbggenerated Sat Nov 09 17:49:52 CST 2019
     */
    List<SysUserLogout> selectByExample(SysUserLogoutExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_logout
     *
     * @mbggenerated Sat Nov 09 17:49:52 CST 2019
     */
    SysUserLogout selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_logout
     *
     * @mbggenerated Sat Nov 09 17:49:52 CST 2019
     */
    int updateByExampleSelective(@Param("record") SysUserLogout record, @Param("example") SysUserLogoutExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_logout
     *
     * @mbggenerated Sat Nov 09 17:49:52 CST 2019
     */
    int updateByExample(@Param("record") SysUserLogout record, @Param("example") SysUserLogoutExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_logout
     *
     * @mbggenerated Sat Nov 09 17:49:52 CST 2019
     */
    int updateByPrimaryKeySelective(SysUserLogout record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_logout
     *
     * @mbggenerated Sat Nov 09 17:49:52 CST 2019
     */
    int updateByPrimaryKey(SysUserLogout record);
}