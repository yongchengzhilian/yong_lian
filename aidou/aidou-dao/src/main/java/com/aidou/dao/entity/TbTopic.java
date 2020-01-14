package com.aidou.dao.entity;

import java.util.Date;

public class TbTopic {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_topic.id
     *
     * @mbggenerated Thu Dec 26 17:46:58 CST 2019
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_topic.created
     *
     * @mbggenerated Thu Dec 26 17:46:58 CST 2019
     */
    private Date created;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_topic.updated
     *
     * @mbggenerated Thu Dec 26 17:46:58 CST 2019
     */
    private Date updated;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_topic.istop
     *
     * @mbggenerated Thu Dec 26 17:46:58 CST 2019
     */
    private Boolean istop;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_topic.visits
     *
     * @mbggenerated Thu Dec 26 17:46:58 CST 2019
     */
    private Integer visits;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_topic.thumbup
     *
     * @mbggenerated Thu Dec 26 17:46:58 CST 2019
     */
    private Integer thumbup;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_topic.comment
     *
     * @mbggenerated Thu Dec 26 17:46:58 CST 2019
     */
    private Integer comment;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_topic.state
     *
     * @mbggenerated Thu Dec 26 17:46:58 CST 2019
     */
    private Integer state;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_topic.type
     *
     * @mbggenerated Thu Dec 26 17:46:58 CST 2019
     */
    private Integer type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_topic.type_name
     *
     * @mbggenerated Thu Dec 26 17:46:58 CST 2019
     */
    private String typeName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_topic.img
     *
     * @mbggenerated Thu Dec 26 17:46:58 CST 2019
     */
    private String img;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_topic.content
     *
     * @mbggenerated Thu Dec 26 17:46:58 CST 2019
     */
    private String content;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_topic.id
     *
     * @return the value of tb_topic.id
     *
     * @mbggenerated Thu Dec 26 17:46:58 CST 2019
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_topic.id
     *
     * @param id the value for tb_topic.id
     *
     * @mbggenerated Thu Dec 26 17:46:58 CST 2019
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_topic.created
     *
     * @return the value of tb_topic.created
     *
     * @mbggenerated Thu Dec 26 17:46:58 CST 2019
     */
    public Date getCreated() {
        return created;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_topic.created
     *
     * @param created the value for tb_topic.created
     *
     * @mbggenerated Thu Dec 26 17:46:58 CST 2019
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_topic.updated
     *
     * @return the value of tb_topic.updated
     *
     * @mbggenerated Thu Dec 26 17:46:58 CST 2019
     */
    public Date getUpdated() {
        return updated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_topic.updated
     *
     * @param updated the value for tb_topic.updated
     *
     * @mbggenerated Thu Dec 26 17:46:58 CST 2019
     */
    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_topic.istop
     *
     * @return the value of tb_topic.istop
     *
     * @mbggenerated Thu Dec 26 17:46:58 CST 2019
     */
    public Boolean getIstop() {
        return istop;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_topic.istop
     *
     * @param istop the value for tb_topic.istop
     *
     * @mbggenerated Thu Dec 26 17:46:58 CST 2019
     */
    public void setIstop(Boolean istop) {
        this.istop = istop;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_topic.visits
     *
     * @return the value of tb_topic.visits
     *
     * @mbggenerated Thu Dec 26 17:46:58 CST 2019
     */
    public Integer getVisits() {
        return visits;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_topic.visits
     *
     * @param visits the value for tb_topic.visits
     *
     * @mbggenerated Thu Dec 26 17:46:58 CST 2019
     */
    public void setVisits(Integer visits) {
        this.visits = visits;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_topic.thumbup
     *
     * @return the value of tb_topic.thumbup
     *
     * @mbggenerated Thu Dec 26 17:46:58 CST 2019
     */
    public Integer getThumbup() {
        return thumbup;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_topic.thumbup
     *
     * @param thumbup the value for tb_topic.thumbup
     *
     * @mbggenerated Thu Dec 26 17:46:58 CST 2019
     */
    public void setThumbup(Integer thumbup) {
        this.thumbup = thumbup;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_topic.comment
     *
     * @return the value of tb_topic.comment
     *
     * @mbggenerated Thu Dec 26 17:46:58 CST 2019
     */
    public Integer getComment() {
        return comment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_topic.comment
     *
     * @param comment the value for tb_topic.comment
     *
     * @mbggenerated Thu Dec 26 17:46:58 CST 2019
     */
    public void setComment(Integer comment) {
        this.comment = comment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_topic.state
     *
     * @return the value of tb_topic.state
     *
     * @mbggenerated Thu Dec 26 17:46:58 CST 2019
     */
    public Integer getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_topic.state
     *
     * @param state the value for tb_topic.state
     *
     * @mbggenerated Thu Dec 26 17:46:58 CST 2019
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_topic.type
     *
     * @return the value of tb_topic.type
     *
     * @mbggenerated Thu Dec 26 17:46:58 CST 2019
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_topic.type
     *
     * @param type the value for tb_topic.type
     *
     * @mbggenerated Thu Dec 26 17:46:58 CST 2019
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_topic.type_name
     *
     * @return the value of tb_topic.type_name
     *
     * @mbggenerated Thu Dec 26 17:46:58 CST 2019
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_topic.type_name
     *
     * @param typeName the value for tb_topic.type_name
     *
     * @mbggenerated Thu Dec 26 17:46:58 CST 2019
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_topic.img
     *
     * @return the value of tb_topic.img
     *
     * @mbggenerated Thu Dec 26 17:46:58 CST 2019
     */
    public String getImg() {
        return img;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_topic.img
     *
     * @param img the value for tb_topic.img
     *
     * @mbggenerated Thu Dec 26 17:46:58 CST 2019
     */
    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_topic.content
     *
     * @return the value of tb_topic.content
     *
     * @mbggenerated Thu Dec 26 17:46:58 CST 2019
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_topic.content
     *
     * @param content the value for tb_topic.content
     *
     * @mbggenerated Thu Dec 26 17:46:58 CST 2019
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}