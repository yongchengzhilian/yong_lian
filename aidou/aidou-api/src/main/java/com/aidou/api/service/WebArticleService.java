package com.aidou.api.service;

import com.aidou.api.vo.respone.NotifyItemRespone;
import com.aidou.api.vo.respone.WebArticleRespone;

import java.util.List;


/**
 * @author yingjiafeng
 * @version 创建时间：2019/9/20 11:49
 */
public interface WebArticleService {

    /**
     * 获取文章列表
     * @return
     */
    WebArticleRespone findWebArticleList();

}
