package com.aidou.librec.abstracr;

import com.aidou.dao.entity.TbUser;

import java.util.List;

public abstract  class AbstractRecommendBase {


    /**
     * 用户推荐
     * @return
     */
    public  abstract List<TbUser>      recommendUserList();
}
