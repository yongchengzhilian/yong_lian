package com.aidou.dao.entity;

import java.util.Date;

public class TbCard {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_card.uid
     *
     * @mbggenerated Thu Nov 21 18:49:38 CST 2019
     */
    private Long uid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_card.id_card
     *
     * @mbggenerated Thu Nov 21 18:49:38 CST 2019
     */
    private String idCard;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_card.name
     *
     * @mbggenerated Thu Nov 21 18:49:38 CST 2019
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_card.address
     *
     * @mbggenerated Thu Nov 21 18:49:38 CST 2019
     */
    private String address;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_card.created
     *
     * @mbggenerated Thu Nov 21 18:49:38 CST 2019
     */
    private Date created;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_card.updated
     *
     * @mbggenerated Thu Nov 21 18:49:38 CST 2019
     */
    private Date updated;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_card.status
     *
     * @mbggenerated Thu Nov 21 18:49:38 CST 2019
     */
    private Integer status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_card.remarks
     *
     * @mbggenerated Thu Nov 21 18:49:38 CST 2019
     */
    private String remarks;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_card.sfz_front
     *
     * @mbggenerated Thu Nov 21 18:49:38 CST 2019
     */
    private String sfzFront;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_card.sfz_back
     *
     * @mbggenerated Thu Nov 21 18:49:38 CST 2019
     */
    private String sfzBack;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_card.uid
     *
     * @return the value of tb_card.uid
     *
     * @mbggenerated Thu Nov 21 18:49:38 CST 2019
     */
    public Long getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_card.uid
     *
     * @param uid the value for tb_card.uid
     *
     * @mbggenerated Thu Nov 21 18:49:38 CST 2019
     */
    public void setUid(Long uid) {
        this.uid = uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_card.id_card
     *
     * @return the value of tb_card.id_card
     *
     * @mbggenerated Thu Nov 21 18:49:38 CST 2019
     */
    public String getIdCard() {
        return idCard;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_card.id_card
     *
     * @param idCard the value for tb_card.id_card
     *
     * @mbggenerated Thu Nov 21 18:49:38 CST 2019
     */
    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_card.name
     *
     * @return the value of tb_card.name
     *
     * @mbggenerated Thu Nov 21 18:49:38 CST 2019
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_card.name
     *
     * @param name the value for tb_card.name
     *
     * @mbggenerated Thu Nov 21 18:49:38 CST 2019
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_card.address
     *
     * @return the value of tb_card.address
     *
     * @mbggenerated Thu Nov 21 18:49:38 CST 2019
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_card.address
     *
     * @param address the value for tb_card.address
     *
     * @mbggenerated Thu Nov 21 18:49:38 CST 2019
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_card.created
     *
     * @return the value of tb_card.created
     *
     * @mbggenerated Thu Nov 21 18:49:38 CST 2019
     */
    public Date getCreated() {
        return created;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_card.created
     *
     * @param created the value for tb_card.created
     *
     * @mbggenerated Thu Nov 21 18:49:38 CST 2019
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_card.updated
     *
     * @return the value of tb_card.updated
     *
     * @mbggenerated Thu Nov 21 18:49:38 CST 2019
     */
    public Date getUpdated() {
        return updated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_card.updated
     *
     * @param updated the value for tb_card.updated
     *
     * @mbggenerated Thu Nov 21 18:49:38 CST 2019
     */
    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_card.status
     *
     * @return the value of tb_card.status
     *
     * @mbggenerated Thu Nov 21 18:49:38 CST 2019
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_card.status
     *
     * @param status the value for tb_card.status
     *
     * @mbggenerated Thu Nov 21 18:49:38 CST 2019
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_card.remarks
     *
     * @return the value of tb_card.remarks
     *
     * @mbggenerated Thu Nov 21 18:49:38 CST 2019
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_card.remarks
     *
     * @param remarks the value for tb_card.remarks
     *
     * @mbggenerated Thu Nov 21 18:49:38 CST 2019
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_card.sfz_front
     *
     * @return the value of tb_card.sfz_front
     *
     * @mbggenerated Thu Nov 21 18:49:38 CST 2019
     */
    public String getSfzFront() {
        return sfzFront;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_card.sfz_front
     *
     * @param sfzFront the value for tb_card.sfz_front
     *
     * @mbggenerated Thu Nov 21 18:49:38 CST 2019
     */
    public void setSfzFront(String sfzFront) {
        this.sfzFront = sfzFront == null ? null : sfzFront.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_card.sfz_back
     *
     * @return the value of tb_card.sfz_back
     *
     * @mbggenerated Thu Nov 21 18:49:38 CST 2019
     */
    public String getSfzBack() {
        return sfzBack;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_card.sfz_back
     *
     * @param sfzBack the value for tb_card.sfz_back
     *
     * @mbggenerated Thu Nov 21 18:49:38 CST 2019
     */
    public void setSfzBack(String sfzBack) {
        this.sfzBack = sfzBack == null ? null : sfzBack.trim();
    }
}