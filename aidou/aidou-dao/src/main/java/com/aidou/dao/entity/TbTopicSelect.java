package com.aidou.dao.entity;

import java.util.Date;

public class TbTopicSelect {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_topic_select.id
     *
     * @mbggenerated Thu Dec 26 09:50:45 CST 2019
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_topic_select.top_id
     *
     * @mbggenerated Thu Dec 26 09:50:45 CST 2019
     */
    private Long topId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_topic_select.select_name
     *
     * @mbggenerated Thu Dec 26 09:50:45 CST 2019
     */
    private String selectName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_topic_select.vote_count
     *
     * @mbggenerated Thu Dec 26 09:50:45 CST 2019
     */
    private Integer voteCount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_topic_select.created
     *
     * @mbggenerated Thu Dec 26 09:50:45 CST 2019
     */
    private Date created;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_topic_select.updated
     *
     * @mbggenerated Thu Dec 26 09:50:45 CST 2019
     */
    private Date updated;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_topic_select.uid_text
     *
     * @mbggenerated Thu Dec 26 09:50:45 CST 2019
     */
    private String uidText;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_topic_select.id
     *
     * @return the value of tb_topic_select.id
     *
     * @mbggenerated Thu Dec 26 09:50:45 CST 2019
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_topic_select.id
     *
     * @param id the value for tb_topic_select.id
     *
     * @mbggenerated Thu Dec 26 09:50:45 CST 2019
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_topic_select.top_id
     *
     * @return the value of tb_topic_select.top_id
     *
     * @mbggenerated Thu Dec 26 09:50:45 CST 2019
     */
    public Long getTopId() {
        return topId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_topic_select.top_id
     *
     * @param topId the value for tb_topic_select.top_id
     *
     * @mbggenerated Thu Dec 26 09:50:45 CST 2019
     */
    public void setTopId(Long topId) {
        this.topId = topId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_topic_select.select_name
     *
     * @return the value of tb_topic_select.select_name
     *
     * @mbggenerated Thu Dec 26 09:50:45 CST 2019
     */
    public String getSelectName() {
        return selectName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_topic_select.select_name
     *
     * @param selectName the value for tb_topic_select.select_name
     *
     * @mbggenerated Thu Dec 26 09:50:45 CST 2019
     */
    public void setSelectName(String selectName) {
        this.selectName = selectName == null ? null : selectName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_topic_select.vote_count
     *
     * @return the value of tb_topic_select.vote_count
     *
     * @mbggenerated Thu Dec 26 09:50:45 CST 2019
     */
    public Integer getVoteCount() {
        return voteCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_topic_select.vote_count
     *
     * @param voteCount the value for tb_topic_select.vote_count
     *
     * @mbggenerated Thu Dec 26 09:50:45 CST 2019
     */
    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_topic_select.created
     *
     * @return the value of tb_topic_select.created
     *
     * @mbggenerated Thu Dec 26 09:50:45 CST 2019
     */
    public Date getCreated() {
        return created;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_topic_select.created
     *
     * @param created the value for tb_topic_select.created
     *
     * @mbggenerated Thu Dec 26 09:50:45 CST 2019
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_topic_select.updated
     *
     * @return the value of tb_topic_select.updated
     *
     * @mbggenerated Thu Dec 26 09:50:45 CST 2019
     */
    public Date getUpdated() {
        return updated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_topic_select.updated
     *
     * @param updated the value for tb_topic_select.updated
     *
     * @mbggenerated Thu Dec 26 09:50:45 CST 2019
     */
    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_topic_select.uid_text
     *
     * @return the value of tb_topic_select.uid_text
     *
     * @mbggenerated Thu Dec 26 09:50:45 CST 2019
     */
    public String getUidText() {
        return uidText;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_topic_select.uid_text
     *
     * @param uidText the value for tb_topic_select.uid_text
     *
     * @mbggenerated Thu Dec 26 09:50:45 CST 2019
     */
    public void setUidText(String uidText) {
        this.uidText = uidText == null ? null : uidText.trim();
    }
}