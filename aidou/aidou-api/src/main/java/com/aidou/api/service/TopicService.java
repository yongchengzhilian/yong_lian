package com.aidou.api.service;

import com.aidou.api.vo.PageExtAction;
import com.aidou.api.vo.request.*;
import com.aidou.api.vo.respone.TopicItemRespone;
import com.aidou.util.entity.AidouResult;

import java.util.List;

public interface TopicService {
    /**
     * 创建话题
     * @param topicRequest
     */
    void createTopic(TopicRequest topicRequest);

    /**
     * 关闭或者打开话题
     * @param topicRequest
     */
    void openCloseTopic(TopicStatusRequest topicRequest);

    /**
     * 话题列表
     * @return
     * @param topicListRequest
     */
    List<TopicItemRespone> selectList(TopicListRequest topicListRequest);

    /**
     * 添加浏览量
     * @param uid
     * @param topicId
     */
    void   addVisits(Long  uid,Long topicId);

    /**
     * 添加 点赞
     * @param uid
     * @param topicId
     */
    void  addThumbup(Long  uid,Long topicId);

    /**
     * 添加评论
     * @param topicId
     */
    void  addComment(Long topicId,Long  commId);

    /**
     * 删除评论
     * @param topicId  话题ID
     * @param commId  评论ID
     */
    void delComment( Long topicId,Long  commId);

    /**
     * 把数据从redis 刷入到 DB
     */
    void refreshDataByCachaToDb();

    /**
     * 点赞
     * @param topicRequest
     */
    void createdLike(TopicLikeRequest topicRequest);

    /**
     * 取消点赞
     * @param topicId
     */
    void clearThumbup(Long topicId);

    /**
     * 话题评论
     * @param commentRRequest
     */
    void commentTopic(CommentRRequest commentRRequest);

    /**
     * 评论列表
     * @param articleCommentSearchRequest
     * @return
     */
    AidouResult commentList(ArticleCommentSearchRequest articleCommentSearchRequest);


    /**
     * 话题投票
     * @param topicVoteRequest
     */
    void topicVote(TopicVoteRequest topicVoteRequest);

    /**
     * 我的消息列表
     * @param pageExtAction
     */
    AidouResult topicMessage(PageExtAction pageExtAction);

    /**
     * 评论删除
     * @param commid
     */
    void commitDelete(Long commid);

    /**
     * 话题详情
     * @param topicId
     * @return
     */
    TopicItemRespone selectDescriptByTopicId(Long topicId);

    /**
     * 评论点赞
     * @param topicRequest
     */
    void commentZan(TopicLikeRequest topicRequest);


    /**
     * 评论点赞
     * @param uid
     * @param commentId
     */
    void addCommentThumbup(Long uid, Long commentId);


    /**
     * 评论点赞删除
     * @param uid  用户ID
     * @param commId  评论ID
     */
    void delCommentThumbup(Long uid, Long commId);

}
