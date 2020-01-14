package com.aidou.api.service.impl;

import com.aidou.api.common.cache.RedisDao;
import com.aidou.api.common.cache.RedisKey;
import com.aidou.api.service.*;
import com.aidou.api.vo.PageExtAction;
import com.aidou.api.vo.request.*;
import com.aidou.api.vo.respone.ArticleAutoCommentRespone;
import com.aidou.api.vo.respone.ArticleCommentRespone;
import com.aidou.api.vo.respone.TopicItemRespone;
import com.aidou.api.vo.respone.TopicVoteRespone;
import com.aidou.dao.entity.*;
import com.aidou.dao.mapper.TbCommentMapper;
import com.aidou.dao.mapper.TbTopicMapper;
import com.aidou.dao.mapper.TbTopicSelectMapper;
import com.aidou.dao.mapper.TbTopicSelectUserRelationMapper;
import com.aidou.util.entity.AidouResult;
import com.aidou.util.entity.CurrentUser;
import com.aidou.util.entity.wechat.WeChatMessageModel;
import com.aidou.util.enums.NoticeEnum;
import com.aidou.util.enums.UserStatusEnum;
import com.aidou.util.exception.BizException;
import com.aidou.util.tool.DateUtil;
import com.aidou.util.tool.EmojiUtil;
import com.aidou.util.tool.GsonUtil;
import com.aidou.util.tool.IdWorker;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TopicServiceImpl implements TopicService {

    @Resource
    private UserService userService;

    @Resource
    private TbTopicMapper tbTopicMapper;

    @Resource
    private WeChatMessagePushService weChatMessagePushService;

    @Resource
    private TbCommentMapper tbCommentMapper;

    @Resource
    private TbTopicSelectMapper tbTopicSelectMapper;

    @Resource
    private TbTopicSelectUserRelationMapper tbTopicSelectUserRelationMapper;

    @Resource
    private WeChatContentCheckService weChatContentCheckService;

    @Resource
    private IdWorker idWorker;

    @Resource
    private NoticeService noticeService;

    @Resource
    private RedisDao redisDao;

    @Transactional(rollbackFor = BizException.class)
    @Override
    public void createTopic(TopicRequest topicRequest) {
        TbTopic tbTopic = new TbTopic();
        tbTopic.setId(idWorker.nextId());
        tbTopic.setCreated(new Date());
        tbTopic.setUpdated(new Date());
        tbTopic.setType(topicRequest.getType());
        tbTopic.setTypeName(topicRequest.getType() == 1 ? "内容话题" : "投票话题");
        tbTopic.setContent(EmojiUtil.emojiConverterToAlias(topicRequest.getContent()));

        if (topicRequest.getImageList() != null && topicRequest.getImageList().size() > 0) {
            tbTopic.setImg(GsonUtil.gsonString(topicRequest.getImageList()));
        }
        if (tbTopicMapper.insertSelective(tbTopic) < 1) {
            throw new BizException("话题创建失败");
        }
        //创建话题
        if (topicRequest.getType() == 2) {
            //投票
            if (topicRequest.getSelectTopisList() == null || topicRequest.getSelectTopisList().size() == 0) {
                throw new BizException("投票列表不能为空");
            }
            topicRequest.getSelectTopisList().forEach((x) -> {
                //保存话题列表
                TbTopicSelect topicSelect = new TbTopicSelect();
                topicSelect.setCreated(new Date());
                topicSelect.setUpdated(new Date());
                topicSelect.setSelectName(x);
                topicSelect.setTopId(tbTopic.getId());
                if (tbTopicSelectMapper.insertSelective(topicSelect) < 1) {
                    throw new BizException("创建选择话题失败");
                }
            });
        }

    }

    @Override
    public void openCloseTopic(TopicStatusRequest topicRequest) {
        TbTopic tbTopic = tbTopicMapper.selectByPrimaryKey(topicRequest.getTopicId());
        Optional.ofNullable(tbTopic).orElseThrow(() -> new BizException("话题不存在"));
        tbTopic.setState(topicRequest.getStatus());
        tbTopic.setUpdated(new Date());
        tbTopicMapper.updateByPrimaryKeyWithBLOBs(tbTopic);


    }

    @Override
    public List<TopicItemRespone> selectList(TopicListRequest topicListRequest) {
        String value = redisDao.getValue(RedisKey.TOPIC_TEXT_TOKEN.toString());
        List<TopicItemRespone> topicItemResponeList;
        if (StringUtils.isNotEmpty(value)) {
            topicItemResponeList = GsonUtil.jsonToList(value, TopicItemRespone.class);
        } else {
            topicItemResponeList = new ArrayList<>();
            TbTopicExample example = new TbTopicExample();
            example.createCriteria().andStateEqualTo(1);
            example.setOrderByClause("created   desc");
            List<TbTopic> tbTopics = tbTopicMapper.selectByExampleWithBLOBs(example);
            if (tbTopics.isEmpty()) {
                return topicItemResponeList;
            }
            for (TbTopic tbTopic : tbTopics) {
                TopicItemRespone item = new TopicItemRespone();
                BeanUtils.copyProperties(tbTopic, item);
                item.setContent(EmojiUtil.emojiConverterUnicodeStr(tbTopic.getContent()));
                item.setId(tbTopic.getId().toString());
                if (StringUtils.isNotEmpty(tbTopic.getImg())) {
                    item.setImgList(GsonUtil.gsonToList(tbTopic.getImg(), String.class));
                }
                if (tbTopic.getType() == 2) {
                    //投票
                    TbTopicSelectExample topSelectExample = new TbTopicSelectExample();
                    topSelectExample.createCriteria().andTopIdEqualTo(tbTopic.getId());
                    List<TbTopicSelect> tbTopicSelects = tbTopicSelectMapper.selectByExample(topSelectExample);
                    List<TopicVoteRespone> selectItemList = new ArrayList<>();
                    tbTopicSelects.forEach((x) -> {
                        TopicVoteRespone selectItem = new TopicVoteRespone();
                        BeanUtils.copyProperties(x, selectItem);
                        selectItem.setId(x.getId().toString());
                        selectItemList.add(selectItem);
                    });
                    item.setSelectItem(selectItemList);
                }
                topicItemResponeList.add(item);
            }
            redisDao.setKey(RedisKey.TOPIC_TEXT_TOKEN.toString(), GsonUtil.gsonString(topicItemResponeList), 60);
        }
        topicInitInfo(topicItemResponeList);
        if (topicListRequest.getType() == 2) {
            //排序
            Collections.sort(topicItemResponeList, com1);
        }
        return topicItemResponeList;
    }


    /**
     * 热门话题排序
     */
    Comparator<TopicItemRespone> com1 = new Comparator<TopicItemRespone>() {
        @Override
        public int compare(TopicItemRespone o1, TopicItemRespone o2) {
            // TODO Auto-generated method stub
            return    (o1.getThumbup()*0.4+o1.getVisits()*0.2+o1.getComment()*0.4)>(o2.getThumbup()*0.4+o2.getVisits()*0.2+o2.getComment()*0.4)?-1:0;
        }


    };


    private void topicInitInfo(List<TopicItemRespone> topicItemResponeList) {
        topicItemResponeList.forEach((x) -> {
            addVisits(CurrentUser.get().getId(), Long.valueOf(x.getId()));
            //浏览
            x.setVisits(redisDao.getSetCount(RedisKey.TOPIC_TEXT_VISITS.suffix(x.getId()).toString()));
            //点赞
            x.setThumbup(redisDao.getSetCount(RedisKey.TOPIC_TEXT_THUMBUP.suffix(x.getId()).toString()));
            //评论
            x.setComment(redisDao.getSetCount(RedisKey.TOPIC_TEXT_COMMENT.suffix(x.getId()).toString()));
            //是否点赞
            x.setIsZan(isZanByCurrent(Long.valueOf(x.getId())));
            //如果是话题选择类型 则需要判断是否选择
            if (x.getType() == 2) {
                //选择
                List<TopicVoteRespone> selectItem = x.getSelectItem();
                List<Long> selectIdList = selectItem.stream().map((y) -> Long.valueOf(y.getId())).collect(Collectors.toList());
                TbTopicSelectUserRelationExample example = new TbTopicSelectUserRelationExample();
                example.createCriteria().andSelectIdIn(selectIdList);
                List<TbTopicSelectUserRelation> tbTopicSelectUserRelations = tbTopicSelectUserRelationMapper.selectByExample(example);
                //获取到所有的评论
                for (TopicVoteRespone topicVoteRespone : selectItem) {
                    //判断当前用户是否选择
                    List<TbTopicSelectUserRelation> collect = tbTopicSelectUserRelations.stream().
                            filter((e) -> e.getSelectId().longValue() == Long.valueOf(topicVoteRespone.getId())).collect(Collectors.toList());
                    if (!collect.isEmpty()) {
                        //设置点赞数
                        topicVoteRespone.setNum(collect.size());
                        //判断当前用户是否选中
                        if (collect.stream().anyMatch((e) -> e.getUidId().longValue() == CurrentUser.get().getId().longValue())) {
                            topicVoteRespone.setIsSelect(true);
                            x.setIsSelect(true);
                        }
                    }
                }
            }
        });
    }

    /**
     * 判断当前用户是否点赞
     *
     * @param topicId
     * @return
     */
    private Boolean isZanByCurrent(Long topicId) {
        if (CurrentUser.get() != null) {
            return redisDao.getSetValExit(RedisKey.TOPIC_TEXT_THUMBUP.suffix(topicId.toString()).toString(), CurrentUser.get().getId().toString());
        }
        return false;
    }


    /**
     * 添加浏览
     *
     * @param topicId 文章ID
     */
    @Override
    public void addVisits(Long uid, Long topicId) {
        redisDao.putSet(RedisKey.TOPIC_TEXT_VISITS.suffix(topicId.toString()).toString(), uid.toString());
    }

    /**
     * 添加点赞
     *
     * @param uid     用户ID
     * @param topicId 文章ID
     */
    @Override
    public void addThumbup(Long uid, Long topicId) {
        //文章点赞
        redisDao.putSet(RedisKey.TOPIC_TEXT_THUMBUP.suffix(topicId.toString()).toString(), uid.toString());
    }

    /**
     * 添加评论点赞
     *
     * @param uid     用户ID
     * @param commentId 评论ID
     */
    @Override
    public void addCommentThumbup(Long uid, Long commentId) {
        //文章点赞
        redisDao.putSet(RedisKey.TOPIC_COMMENT_VISITS.suffix(commentId.toString()).toString(), uid.toString());
        redisDao.putSet(RedisKey.TOPIC_COMMENT_ID_SET.suffix("10086").toString(),commentId.toString());
    }

    /**
     * 删除评论点赞
     *
     * @param uid     用户ID
     * @param commentId 评论ID
     */
    @Override
    public void delCommentThumbup(Long uid, Long commentId) {
        //文章点赞
        redisDao.delSet(RedisKey.TOPIC_COMMENT_VISITS.suffix(commentId.toString()).toString(), uid.toString());
        redisDao.delSet(RedisKey.TOPIC_COMMENT_ID_SET.suffix("10086").toString(),commentId.toString());
    }

    /**
     * 添加评论数
     *
     * @param topicId 文章ID
     */
    @Override
    public void addComment(Long topicId, Long commId) {
        //添加评论数
        redisDao.putSet(RedisKey.TOPIC_TEXT_COMMENT.suffix(topicId.toString()).toString(), commId.toString());
    }

    /**
     * 添加评论数
     *
     * @param topicId 文章ID
     * @param commId  评论ID
     */
    @Override
    public void delComment(Long topicId, Long commId) {
        //添加评论数
        redisDao.delSet(RedisKey.TOPIC_TEXT_COMMENT.suffix(topicId.toString()).toString(), commId.toString());
    }

    @Override
    public void refreshDataByCachaToDb() {
        //获取所有话题
        TbTopicExample example = new TbTopicExample();
        example.createCriteria().andStateEqualTo(1);
        List<TbTopic> tbTopics = tbTopicMapper.selectByExample(example);
        tbTopics.forEach((x) -> {
            long commentCount = redisDao.getSetCount(RedisKey.TOPIC_TEXT_COMMENT.suffix(x.getId().toString()).toString());
            if (commentCount>0){
                x.setComment((int)commentCount);
            }
            long visitsCount = redisDao.getSetCount(RedisKey.TOPIC_TEXT_VISITS.suffix(x.getId().toString()).toString());
            if (visitsCount>0){
                x.setVisits((int) visitsCount);
            }

            long setThumbup = redisDao.getSetCount(RedisKey.TOPIC_TEXT_THUMBUP.suffix(x.getId().toString()).toString());
            if (setThumbup>0){
                x.setThumbup((int)setThumbup );
            }
            x.setUpdated(new Date());
            tbTopicMapper.updateByPrimaryKeySelective(x);
        });
    }

    /**
     * 点赞
     *
     * @param topicRequest
     */
    @Override
    public void createdLike(TopicLikeRequest topicRequest) {
        TbTopic tbTopic = tbTopicMapper.selectByPrimaryKey(topicRequest.getTopicId());
        Optional.ofNullable(tbTopic).orElseThrow(() -> new BizException("话题不存在"));
        if (topicRequest.getLikeStatus() == 1) {
            addThumbup(CurrentUser.get().getId(), topicRequest.getTopicId());
        } else {
            //取消点赞
            clearThumbup(topicRequest.getTopicId());
        }
    }

    /**
     * 取消点赞
     *
     * @param topicId
     */
    @Override
    public void clearThumbup(Long topicId) {
        redisDao.delSet(RedisKey.TOPIC_TEXT_THUMBUP.suffix(topicId.toString()).toString(), CurrentUser.get().getId().toString());
    }

    @Transactional(rollbackFor = BizException.class)
    @Override
    public void commentTopic(CommentRRequest commentRRequest) {
        TbTopic tbTopic = tbTopicMapper.selectByPrimaryKey(commentRRequest.getTopicId());
        Optional.ofNullable(tbTopic).orElseThrow(() -> new BizException("话题不存在"));
        if (tbTopic.getState() == UserStatusEnum.STOP.getIndex()) {
            throw new BizException("话题禁止评论");
        }
        if (!weChatContentCheckService.msgSecCheck(commentRRequest.getContent())) {
            throw new BizException("内容含有违法违规内容");
        }

        TbComment tbComment = new TbComment();
        tbComment.setContent(EmojiUtil.emojiConverterToAlias(commentRRequest.getContent()));
        tbComment.setArticleid(commentRRequest.getTopicId());
        tbComment.setCreated(new Date());
        tbComment.setUid(CurrentUser.get().getId());
        tbComment.setParentid(commentRRequest.getParentid());
        Long parentid = commentRRequest.getParentid();
        if (parentid != null && parentid > 0) {
            TbComment parentComment = tbCommentMapper.selectByPrimaryKey(parentid);
            parentComment.setCommentCount(parentComment.getCommentCount()+1);
            tbComment.setToUid(parentComment.getUid());
        } else {
            tbComment.setToUid(0L);
        }
        tbComment.setArticleid(commentRRequest.getTopicId());
        tbCommentMapper.insertSelective(tbComment);
        //保存评论数
        addComment(commentRRequest.getTopicId(), tbComment.getId());
        //评论通知
        if (commentRRequest.getParentid() != 0) {
            tbComment = tbCommentMapper.selectByPrimaryKey(commentRRequest.getParentid());
            if (CurrentUser.get().getId() != tbComment.getUid()) {
                WeChatMessageModel messageModel = new WeChatMessageModel();
                messageModel.setRemarks(commentRRequest.getContent());
                messageModel.setResult(CurrentUser.get().getNikeName());
                messageModel.setPage("pages/login/index/index");
                messageModel.setUid(tbComment.getUid());
                weChatMessagePushService.leaveMessageMessage(messageModel);
                TbNotice tbNotice = new TbNotice();
                tbNotice.setContent("留言回复");
                tbNotice.setNoticeType(NoticeEnum.NOTICE_STATUS2.getIndex());
                tbNotice.setNoticeTypeName(NoticeEnum.NOTICE_STATUS2.getName());
                tbNotice.setUid(tbComment.getUid());
                tbNotice.setClearTime(DateUtil.getDateAfter(new Date(), 7));
                noticeService.addNotice(tbNotice);
            }
        }
    }

    @Override
    public AidouResult commentList(ArticleCommentSearchRequest articleCommentSearchRequest) {
        PageHelper.startPage(articleCommentSearchRequest.getPage(), articleCommentSearchRequest.getLimit(), false);
        TbCommentExample example = new TbCommentExample();
        if (articleCommentSearchRequest.getType()==2){
            example.setOrderByClause("thumbup_count+comment_count desc");
        }else {
            example.setOrderByClause("created  desc");
        }

        TbCommentExample.Criteria criteria = example.createCriteria();
        criteria.andArticleidEqualTo(articleCommentSearchRequest.getArticleId());
        criteria.andParentidEqualTo(0L).andDpStatusEqualTo(1);
        List<TbComment> tbComments = tbCommentMapper.selectByExample(example);
        if (tbComments.isEmpty()) {
            return AidouResult.success(new ArrayList<ArticleCommentRespone>());
        }
        PageInfo pageInfo = new PageInfo(tbComments);
        long total = pageInfo.getTotal();
        List<Long> idList = tbComments.stream().map((e) -> e.getId()).collect(Collectors.toList());
        example.clear();
        example.setOrderByClause("created  desc");
        example.createCriteria().andParentidIn(idList);
        //基于帖子作者的回复
        List<TbComment> tbAuthComment = tbCommentMapper.selectByExample(example);
        List<ArticleCommentRespone> articleCommentResponeList = new ArrayList<>();
        List<Long> userIdList = tbComments.stream().map((e) -> e.getUid()).collect(Collectors.toList());
        List<TbUser> userByIdLiST = userService.findUserByIdLiST(userIdList);
        tbComments.forEach((x) -> {
            ArticleCommentRespone commonItem = new ArticleCommentRespone();
            commonItem.setId(x.getId().toString());
            commonItem.setUid(x.getUid().toString());
            commonItem.setContent(x.getContent());
            commonItem.setCreated(x.getCreated());
            commonItem.setParentId(x.getParentid().toString());
            TbUser user = userByIdLiST.stream().filter((e) -> e.getUid().longValue() == x.getUid().longValue()).findFirst().get();
            commonItem.setFaceImg(user.getAvatarurl());
            commonItem.setNikeName(user.getNikename());
            //评论
            commonItem.setCommentCount(x.getCommentCount());
            List<TbComment> commentList = tbAuthComment.stream().filter((e) -> e.getParentid().longValue() == x.getId().longValue()).collect(Collectors.toList());
            if (!commentList.isEmpty()) {
                List<ArticleAutoCommentRespone> autoComentList = new ArrayList<>();
                commentList.forEach((y) -> {
                    ArticleAutoCommentRespone autoCommItem = new ArticleAutoCommentRespone();
                    autoCommItem.setContent(y.getContent());
                    autoCommItem.setId(y.getId().toString());
                    autoCommItem.setCreated(y.getCreated());
                    autoCommItem.setParentId(y.getParentid().toString());
                    autoCommItem.setUid(y.getUid().toString());
                    TbUser userById = userService.findUserById(y.getUid());
                    autoCommItem.setFaceImg(userById.getAvatarurl());
                    autoCommItem.setNikeName(userById.getNikename());
                    //点赞数
                    commonItem.setThumbupCount(redisDao.getSetCount(RedisKey.TOPIC_COMMENT_VISITS.suffix(y.getId().toString()).toString()));
                    autoComentList.add(autoCommItem);

                });
                commonItem.setAuthCommentList(autoComentList);
            }
            commonItem.setTopicId(x.getArticleid().toString());
            articleCommentResponeList.add(commonItem);
        });
        AidouResult success = AidouResult.success(articleCommentResponeList);
        success.setTotal(total);
        return success;
    }

    @Transactional(rollbackFor = BizException.class)
    @Override
    public void topicVote(TopicVoteRequest topicVoteRequest) {
        //投票类型
        TbTopic tbTopic = tbTopicMapper.selectByPrimaryKey(topicVoteRequest.getTopicId());
        Optional.ofNullable(tbTopic).orElseThrow(() -> new BizException("话题不存在"));
        TbTopicSelectUserRelationExample exmaple = new TbTopicSelectUserRelationExample();
        exmaple.createCriteria().andTopicIdEqualTo(topicVoteRequest.getTopicId()).andUidIdEqualTo(CurrentUser.get().getId());
        List<TbTopicSelectUserRelation> tbTopicSelectUserRelations = tbTopicSelectUserRelationMapper.selectByExample(exmaple);
        if (topicVoteRequest.getSelectType() == 1) {
            //投票
            if (tbTopicSelectUserRelations.isEmpty()) {
                TbTopicSelectUserRelation recod = new TbTopicSelectUserRelation();
                recod.setCreated(new Date());
                recod.setSelectId(topicVoteRequest.getSelectId());
                recod.setTopicId(topicVoteRequest.getTopicId());
                recod.setUidId(CurrentUser.get().getId());
                if (tbTopicSelectUserRelationMapper.insertSelective(recod) < 1) {
                    throw new BizException("投票失败");
                }
            }
        } else {
            //取消
            if (!tbTopicSelectUserRelations.isEmpty()) {
                tbTopicSelectUserRelationMapper.deleteByPrimaryKey(tbTopicSelectUserRelations.get(0).getId());
            }
        }

    }

    @Override
    public AidouResult topicMessage(PageExtAction pageExtAction) {
        //我的消息
        List<TopicMessageRespone> topicMessageRespones = new ArrayList<>();
        PageHelper.startPage(pageExtAction.getPage(), pageExtAction.getLimit(), true);
        TbCommentExample example = new TbCommentExample();
        example.createCriteria().andToUidEqualTo(CurrentUser.get().getId()).andDpStatusEqualTo(1);
        List<TbComment> tbComments = tbCommentMapper.selectByExample(example);
        if (tbComments.isEmpty()) {
            return AidouResult.success(topicMessageRespones);
        }
        PageInfo<TbComment> pageHelper = new PageInfo(tbComments);
        //去获取对他的评论
        List<Long> collect = tbComments.stream().map((e) -> e.getParentid()).collect(Collectors.toList());
        example.clear();
        example.createCriteria().andIdIn(collect).andDpStatusEqualTo(1);
        List<TbComment> tbCommentsParent = tbCommentMapper.selectByExample(example);
        List<Long> collect1 = tbComments.stream().map((e) -> e.getArticleid()).collect(Collectors.toList());
        TbTopicExample topicExample = new TbTopicExample();
        topicExample.createCriteria().andIdIn(collect1);
        List<TbTopic> tbTopics = tbTopicMapper.selectByExampleWithBLOBs(topicExample);
        //获取所有评价用户
        List<Long> userIdList = tbComments.stream().map((e) -> e.getUid()).collect(Collectors.toList());
        List<TbUser> userByIdLiST = userService.findUserByIdLiST(userIdList);
        tbComments.forEach((x) -> {
            Long parentid = x.getParentid();
            TbComment tbComment = tbCommentsParent.stream().filter((e) -> e.getId().longValue() == parentid.longValue()).findFirst().get();
            TbTopic tbTopic = tbTopics.stream().filter((e) -> e.getId().longValue() == x.getArticleid().longValue()).findFirst().get();
            TopicMessageRespone topicMessageRespone = new TopicMessageRespone();
            topicMessageRespone.setContent(tbTopic.getContent());
            topicMessageRespone.setToContemt(tbComment.getContent());
            topicMessageRespone.setCreated(x.getCreated());
            topicMessageRespone.setCommidId(x.getId().toString());
            TbUser tbUser = userByIdLiST.stream().filter((e) -> e.getUid().longValue() == x.getUid().longValue()).findFirst().get();
            //头像
            topicMessageRespone.setFaceImg(tbUser.getAvatarurl());
            //昵称
            topicMessageRespone.setNikeName(EmojiUtil.emojiConverterUnicodeStr(tbUser.getNikename()));
            topicMessageRespone.setTopicId(x.getArticleid().toString());
            topicMessageRespones.add(topicMessageRespone);
        });

        AidouResult success = AidouResult.success();
        success.setTotal(pageHelper.getTotal());
        success.setData(topicMessageRespones);
        //删除消息
        noticeService.deleteNoticeByUid(CurrentUser.get().getId(), NoticeEnum.NOTICE_STATUS2);
        return success;

    }

    @Transactional(rollbackFor = BizException.class)
    @Override
    public void commitDelete(Long commid) {
        TbComment tbComment = tbCommentMapper.selectByPrimaryKey(commid);
        Optional.ofNullable(tbComment).orElseThrow(() -> new BizException("评论不存在"));
        tbComment.setDpStatus(-1);
        tbComment.setUpdated(new Date());
        tbCommentMapper.updateByPrimaryKey(tbComment);
        delComment(tbComment.getArticleid(), commid);
        if (tbComment.getParentid()!=0){
            tbComment = tbCommentMapper.selectByPrimaryKey(tbComment.getParentid());
            tbComment.setCommentCount(tbComment.getCommentCount()-1);
            tbComment.setUpdated(new Date());
            tbCommentMapper.updateByPrimaryKey(tbComment);
        }
    }

    @Override
    public TopicItemRespone selectDescriptByTopicId(Long topicId) {
        TbTopic tbTopic = tbTopicMapper.selectByPrimaryKey(topicId);
        TopicItemRespone item = new TopicItemRespone();
        BeanUtils.copyProperties(tbTopic, item);
        item.setContent(EmojiUtil.emojiConverterUnicodeStr(tbTopic.getContent()));
        item.setId(tbTopic.getId().toString());
        if (StringUtils.isNotEmpty(tbTopic.getImg())) {
            item.setImgList(GsonUtil.gsonToList(tbTopic.getImg(), String.class));
        }
        if (tbTopic.getType() == 2) {
            //投票
            TbTopicSelectExample topSelectExample = new TbTopicSelectExample();
            topSelectExample.createCriteria().andTopIdEqualTo(tbTopic.getId());
            List<TbTopicSelect> tbTopicSelects = tbTopicSelectMapper.selectByExample(topSelectExample);
            List<TopicVoteRespone> selectItemList = new ArrayList<>();
            tbTopicSelects.forEach((x) -> {
                TopicVoteRespone selectItem = new TopicVoteRespone();
                BeanUtils.copyProperties(x, selectItem);
                selectItem.setId(x.getId().toString());
                selectItemList.add(selectItem);
            });
            item.setSelectItem(selectItemList);
        }
        //浏览
        item.setVisits(redisDao.getSetCount(RedisKey.TOPIC_TEXT_VISITS.suffix(tbTopic.getId().toString()).toString()));
        //点赞
        item.setThumbup(redisDao.getSetCount(RedisKey.TOPIC_TEXT_THUMBUP.suffix(tbTopic.getId().toString()).toString()));
        //评论
        item.setComment(redisDao.getSetCount(RedisKey.TOPIC_TEXT_COMMENT.suffix(tbTopic.getId().toString()).toString()));
        return item;
    }

    @Override
    public void commentZan(TopicLikeRequest topicRequest) {
        //评论点赞
        TbComment tbComment = tbCommentMapper.selectByPrimaryKey(topicRequest.getTopicId());
        Optional.ofNullable(tbComment).orElseThrow(() -> new BizException("评论不存在"));
        if (topicRequest.getLikeStatus() == 1) {
            addCommentThumbup(CurrentUser.get().getId(), topicRequest.getTopicId());
        } else {
            //取消点赞
            delCommentThumbup(CurrentUser.get().getId(),topicRequest.getTopicId());
        }
    }

}
