package com.aidou.api.controller.topic;

import com.aidou.api.controller.BaseController;
import com.aidou.api.service.TopicService;
import com.aidou.api.vo.PageExtAction;
import com.aidou.api.vo.request.*;
import com.aidou.api.vo.respone.TopicItemRespone;
import com.aidou.util.entity.AidouResult;
import com.aidou.util.entity.StatusCode;
import com.aidou.util.tool.ValidationUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@Slf4j
@Api(tags = "话题")
@RestController
@RequestMapping("topic/")
public class TopicCnotroller extends BaseController {

    @Resource
    private TopicService topicService;


    //创建话题
    @ApiOperation(value = "获取评论列表", notes = "获取评论列表")
    @PostMapping(value = "comment/list")
    public AidouResult commentList(@RequestBody ArticleCommentSearchRequest articleCommentSearchRequest) {
        ValidationUtils.validate(articleCommentSearchRequest);
        return topicService.commentList(articleCommentSearchRequest);
    }


    @ApiOperation(value = "话题评论", notes = "话题评论")
    @PostMapping(value = "/commentTopic")
    public AidouResult commentTopic(@RequestBody CommentRRequest commentRRequest) {
        ValidationUtils.validate(commentRRequest);
        topicService.commentTopic(commentRRequest);
        return AidouResult.success();
    }


    @ApiOperation(value = "评论点赞/取消点赞", notes = "评论点赞/取消点赞")
    @PostMapping(value = "commentZan")
    public AidouResult commentZan(@RequestBody TopicLikeRequest topicRequest) {
        ValidationUtils.validate(topicRequest);
        topicService.commentZan(topicRequest);
        return AidouResult.success();

    }


    //创建话题
    @ApiOperation(value = "创建话题", notes = "创建话题")
    @PostMapping(value = "createTopic")
    public AidouResult createTopic(@RequestBody TopicRequest topicRequest) {
        ValidationUtils.validate(topicRequest);
        topicService.createTopic(topicRequest);
        return AidouResult.success();

    }


    //创建话题
    @ApiOperation(value = "关闭OR打开话题", notes = "关闭OR打开话题")
    @PostMapping(value = "openCloseTopic")
    public AidouResult openCloseTopic(@RequestBody TopicStatusRequest topicRequest) {
        ValidationUtils.validate(topicRequest);
        topicService.openCloseTopic(topicRequest);
        return AidouResult.success();

    }


    //点赞话题
    @ApiOperation(value = "点赞/取消点赞", notes = "点赞/取消点赞")
    @PostMapping(value = "createdLike")
    public AidouResult createdLike(@RequestBody TopicLikeRequest topicRequest) {
        ValidationUtils.validate(topicRequest);
        topicService.createdLike(topicRequest);
        return AidouResult.success();

    }

    //话题详情
    @ApiOperation(value = "话题详情", notes = "话题详情")
    @ApiResponses({
            @ApiResponse(code = StatusCode.OK, message = "话题详情 ", response = TopicItemRespone.class)
    })
    @GetMapping(value = "selectDescriptByTopicId/{topicId}")
    public AidouResult selectDescriptByTopicId(@PathVariable  Long topicId) {
        TopicItemRespone topicItemResponeList = topicService.selectDescriptByTopicId(topicId);
        return AidouResult.success(topicItemResponeList);
    }


    //创建话题
    @ApiOperation(value = "话题列表", notes = "话题列表")
    @ApiResponses({
            @ApiResponse(code = StatusCode.OK, message = "话题列表 ", response = TopicItemRespone.class)
    })
    @PostMapping(value = "selectList")
    public AidouResult selectList(@RequestBody TopicListRequest topicListRequest) {
        List<TopicItemRespone> topicItemResponeList = topicService.selectList(topicListRequest);
        return AidouResult.success(topicItemResponeList);
    }


    //创建话题
    @ApiOperation(value = "话题投票", notes = "话题投票")
    @ApiResponses({
            @ApiResponse(code = StatusCode.OK, message = "热话题投票", response = AidouResult.class)
    })
    @PostMapping(value = "topicVote")
    public AidouResult topicVote(@RequestBody TopicVoteRequest topicVoteRequest) {
        topicService.topicVote(topicVoteRequest);
        return AidouResult.success();
    }


    //评论消息
    @ApiOperation(value = "我的消息", notes = "我的消息")
    @ApiResponses({
            @ApiResponse(code = StatusCode.OK, message = "我的消息", response = TopicMessageRespone.class)
    })
    @PostMapping(value = "topicMessage")
    public AidouResult topicMessage(@RequestBody PageExtAction pageExtAction) {
        return topicService.topicMessage(pageExtAction);
    }


    //评论消息
    @ApiOperation(value = "评论删除", notes = "评论删除")
    @ApiResponses({
            @ApiResponse(code = StatusCode.OK, message = "评论删除", response = TopicMessageRespone.class)
    })
    @GetMapping(value = "commitDelete/{commid}")
    public AidouResult commitDelete(@PathVariable Long commid) {
        topicService.commitDelete(commid);
        return AidouResult.success();
    }


}
