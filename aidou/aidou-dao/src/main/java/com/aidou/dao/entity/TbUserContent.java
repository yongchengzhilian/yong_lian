package com.aidou.dao.entity;

import java.util.Date;

public class TbUserContent {
    private Long uid;

    private String content;

    private Date created;

    private Date updated;

    private String interest;

    private String favoriteTa;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest == null ? null : interest.trim();
    }

    public String getFavoriteTa() {
        return favoriteTa;
    }

    public void setFavoriteTa(String favoriteTa) {
        this.favoriteTa = favoriteTa == null ? null : favoriteTa.trim();
    }
}