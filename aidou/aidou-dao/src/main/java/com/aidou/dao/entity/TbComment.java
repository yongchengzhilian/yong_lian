package com.aidou.dao.entity;

import java.util.Date;

public class TbComment {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_comment.id
     *
     * @mbggenerated Mon Dec 30 20:11:45 CST 2019
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_comment.articleid
     *
     * @mbggenerated Mon Dec 30 20:11:45 CST 2019
     */
    private Long articleid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_comment.content
     *
     * @mbggenerated Mon Dec 30 20:11:45 CST 2019
     */
    private String content;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_comment.uid
     *
     * @mbggenerated Mon Dec 30 20:11:45 CST 2019
     */
    private Long uid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_comment.parentid
     *
     * @mbggenerated Mon Dec 30 20:11:45 CST 2019
     */
    private Long parentid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_comment.created
     *
     * @mbggenerated Mon Dec 30 20:11:45 CST 2019
     */
    private Date created;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_comment.updated
     *
     * @mbggenerated Mon Dec 30 20:11:45 CST 2019
     */
    private Date updated;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_comment.to_uid
     *
     * @mbggenerated Mon Dec 30 20:11:45 CST 2019
     */
    private Long toUid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_comment.dp_status
     *
     * @mbggenerated Mon Dec 30 20:11:45 CST 2019
     */
    private Integer dpStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_comment.thumbup_count
     *
     * @mbggenerated Mon Dec 30 20:11:45 CST 2019
     */
    private Long thumbupCount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_comment.comment_count
     *
     * @mbggenerated Mon Dec 30 20:11:45 CST 2019
     */
    private Long commentCount;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_comment.id
     *
     * @return the value of tb_comment.id
     *
     * @mbggenerated Mon Dec 30 20:11:45 CST 2019
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_comment.id
     *
     * @param id the value for tb_comment.id
     *
     * @mbggenerated Mon Dec 30 20:11:45 CST 2019
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_comment.articleid
     *
     * @return the value of tb_comment.articleid
     *
     * @mbggenerated Mon Dec 30 20:11:45 CST 2019
     */
    public Long getArticleid() {
        return articleid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_comment.articleid
     *
     * @param articleid the value for tb_comment.articleid
     *
     * @mbggenerated Mon Dec 30 20:11:45 CST 2019
     */
    public void setArticleid(Long articleid) {
        this.articleid = articleid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_comment.content
     *
     * @return the value of tb_comment.content
     *
     * @mbggenerated Mon Dec 30 20:11:45 CST 2019
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_comment.content
     *
     * @param content the value for tb_comment.content
     *
     * @mbggenerated Mon Dec 30 20:11:45 CST 2019
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_comment.uid
     *
     * @return the value of tb_comment.uid
     *
     * @mbggenerated Mon Dec 30 20:11:45 CST 2019
     */
    public Long getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_comment.uid
     *
     * @param uid the value for tb_comment.uid
     *
     * @mbggenerated Mon Dec 30 20:11:45 CST 2019
     */
    public void setUid(Long uid) {
        this.uid = uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_comment.parentid
     *
     * @return the value of tb_comment.parentid
     *
     * @mbggenerated Mon Dec 30 20:11:45 CST 2019
     */
    public Long getParentid() {
        return parentid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_comment.parentid
     *
     * @param parentid the value for tb_comment.parentid
     *
     * @mbggenerated Mon Dec 30 20:11:45 CST 2019
     */
    public void setParentid(Long parentid) {
        this.parentid = parentid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_comment.created
     *
     * @return the value of tb_comment.created
     *
     * @mbggenerated Mon Dec 30 20:11:45 CST 2019
     */
    public Date getCreated() {
        return created;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_comment.created
     *
     * @param created the value for tb_comment.created
     *
     * @mbggenerated Mon Dec 30 20:11:45 CST 2019
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_comment.updated
     *
     * @return the value of tb_comment.updated
     *
     * @mbggenerated Mon Dec 30 20:11:45 CST 2019
     */
    public Date getUpdated() {
        return updated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_comment.updated
     *
     * @param updated the value for tb_comment.updated
     *
     * @mbggenerated Mon Dec 30 20:11:45 CST 2019
     */
    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_comment.to_uid
     *
     * @return the value of tb_comment.to_uid
     *
     * @mbggenerated Mon Dec 30 20:11:45 CST 2019
     */
    public Long getToUid() {
        return toUid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_comment.to_uid
     *
     * @param toUid the value for tb_comment.to_uid
     *
     * @mbggenerated Mon Dec 30 20:11:45 CST 2019
     */
    public void setToUid(Long toUid) {
        this.toUid = toUid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_comment.dp_status
     *
     * @return the value of tb_comment.dp_status
     *
     * @mbggenerated Mon Dec 30 20:11:45 CST 2019
     */
    public Integer getDpStatus() {
        return dpStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_comment.dp_status
     *
     * @param dpStatus the value for tb_comment.dp_status
     *
     * @mbggenerated Mon Dec 30 20:11:45 CST 2019
     */
    public void setDpStatus(Integer dpStatus) {
        this.dpStatus = dpStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_comment.thumbup_count
     *
     * @return the value of tb_comment.thumbup_count
     *
     * @mbggenerated Mon Dec 30 20:11:45 CST 2019
     */
    public Long getThumbupCount() {
        return thumbupCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_comment.thumbup_count
     *
     * @param thumbupCount the value for tb_comment.thumbup_count
     *
     * @mbggenerated Mon Dec 30 20:11:45 CST 2019
     */
    public void setThumbupCount(Long thumbupCount) {
        this.thumbupCount = thumbupCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_comment.comment_count
     *
     * @return the value of tb_comment.comment_count
     *
     * @mbggenerated Mon Dec 30 20:11:45 CST 2019
     */
    public Long getCommentCount() {
        return commentCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_comment.comment_count
     *
     * @param commentCount the value for tb_comment.comment_count
     *
     * @mbggenerated Mon Dec 30 20:11:45 CST 2019
     */
    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }
}