package com.aidou.dao.entity;

import java.util.Date;

public class TbUserImg {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_user_img.id
     *
     * @mbggenerated Thu Nov 21 22:42:12 CST 2019
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_user_img.uid
     *
     * @mbggenerated Thu Nov 21 22:42:12 CST 2019
     */
    private Long uid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_user_img.img_type
     *
     * @mbggenerated Thu Nov 21 22:42:12 CST 2019
     */
    private String imgType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_user_img.is_look
     *
     * @mbggenerated Thu Nov 21 22:42:12 CST 2019
     */
    private Boolean isLook;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_user_img.created
     *
     * @mbggenerated Thu Nov 21 22:42:12 CST 2019
     */
    private Date created;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_user_img.updated
     *
     * @mbggenerated Thu Nov 21 22:42:12 CST 2019
     */
    private Date updated;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_user_img.img
     *
     * @mbggenerated Thu Nov 21 22:42:12 CST 2019
     */
    private String img;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_user_img.id
     *
     * @return the value of tb_user_img.id
     *
     * @mbggenerated Thu Nov 21 22:42:12 CST 2019
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_user_img.id
     *
     * @param id the value for tb_user_img.id
     *
     * @mbggenerated Thu Nov 21 22:42:12 CST 2019
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_user_img.uid
     *
     * @return the value of tb_user_img.uid
     *
     * @mbggenerated Thu Nov 21 22:42:12 CST 2019
     */
    public Long getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_user_img.uid
     *
     * @param uid the value for tb_user_img.uid
     *
     * @mbggenerated Thu Nov 21 22:42:12 CST 2019
     */
    public void setUid(Long uid) {
        this.uid = uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_user_img.img_type
     *
     * @return the value of tb_user_img.img_type
     *
     * @mbggenerated Thu Nov 21 22:42:12 CST 2019
     */
    public String getImgType() {
        return imgType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_user_img.img_type
     *
     * @param imgType the value for tb_user_img.img_type
     *
     * @mbggenerated Thu Nov 21 22:42:12 CST 2019
     */
    public void setImgType(String imgType) {
        this.imgType = imgType == null ? null : imgType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_user_img.is_look
     *
     * @return the value of tb_user_img.is_look
     *
     * @mbggenerated Thu Nov 21 22:42:12 CST 2019
     */
    public Boolean getIsLook() {
        return isLook;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_user_img.is_look
     *
     * @param isLook the value for tb_user_img.is_look
     *
     * @mbggenerated Thu Nov 21 22:42:12 CST 2019
     */
    public void setIsLook(Boolean isLook) {
        this.isLook = isLook;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_user_img.created
     *
     * @return the value of tb_user_img.created
     *
     * @mbggenerated Thu Nov 21 22:42:12 CST 2019
     */
    public Date getCreated() {
        return created;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_user_img.created
     *
     * @param created the value for tb_user_img.created
     *
     * @mbggenerated Thu Nov 21 22:42:12 CST 2019
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_user_img.updated
     *
     * @return the value of tb_user_img.updated
     *
     * @mbggenerated Thu Nov 21 22:42:12 CST 2019
     */
    public Date getUpdated() {
        return updated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_user_img.updated
     *
     * @param updated the value for tb_user_img.updated
     *
     * @mbggenerated Thu Nov 21 22:42:12 CST 2019
     */
    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_user_img.img
     *
     * @return the value of tb_user_img.img
     *
     * @mbggenerated Thu Nov 21 22:42:12 CST 2019
     */
    public String getImg() {
        return img;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_user_img.img
     *
     * @param img the value for tb_user_img.img
     *
     * @mbggenerated Thu Nov 21 22:42:12 CST 2019
     */
    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }
}