package com.aidou.api.service.impl;

import com.aidou.api.enums.LineSourceType;
import com.aidou.api.service.*;
import com.aidou.api.util.AidouImageUtil;
import com.aidou.api.vo.UserToken;
import com.aidou.api.vo.request.UserInfoRequest;
import com.aidou.api.vo.request.UserSmallInfoRequest;
import com.aidou.api.vo.respone.UserCenterInfoRespone;
import com.aidou.dao.entity.*;
import com.aidou.dao.mapper.TbFriendApplicationMapper;
import com.aidou.dao.mapper.TbUserDateCheckMapper;
import com.aidou.dao.mapper.TbUserMapper;
import com.aidou.util.entity.CurrentUser;
import com.aidou.util.entity.wechat.WeChatMessageModel;
import com.aidou.util.enums.AppStatusEnum;
import com.aidou.util.enums.NoticeEnum;
import com.aidou.util.enums.UserStatusEnum;
import com.aidou.util.exception.BizException;
import com.aidou.util.tool.DateUtil;
import com.aidou.util.tool.EmojiUtil;
import com.aidou.util.tool.GsonUtil;
import com.aidou.util.tool.UserDateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 用户行为相关
 * Created by yingjiafeng on 2019/5/24.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private TbUserDateCheckMapper tbUserDateCheckMapper;

    @Autowired
    private LineService lineService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UserImageService userImageService;

    @Autowired
    private TbUserMapper tbUserMapper;

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private FriendApplicationService friendApplicationService;

    @Autowired
    private TbFriendApplicationMapper tbFriendApplicationMapper;
    @Autowired
    private WeChatMessagePushService weChatMessagePushService;


    @Transactional(rollbackFor = BizException.class)
    @Override
    public void sendRedLine(Long toUid, String content) throws BizException {
        //该用户是否牵线中
        if (CurrentUser.get().getStatus() != 3) {
            throw new BizException("用户状态不正确");
        }
        //删除红线
        lineService.deleteLineByUid(toUid, "牵线申请");
        //建立告白关系
        friendApplicationService.createdConfession(toUid, content);
        //推送消息给被表白方
        WeChatMessageModel messageRequest = new WeChatMessageModel();
        messageRequest.setResult(CurrentUser.get().getNikeName());
        messageRequest.setUid(toUid);
        messageRequest.setPage("pages/login/index/index");
        messageRequest.setRemarks(content);
        weChatMessagePushService.matchmakingRequestMessage(messageRequest);   //friendApplicationNotification(tbUser, content, toUid);
        //记录通知
        TbNotice tbNotice = new TbNotice();
        tbNotice.setContent("牵线申请");
        tbNotice.setNoticeType(NoticeEnum.NOTICE_STATUS1.getIndex());
        tbNotice.setNoticeTypeName(NoticeEnum.NOTICE_STATUS1.getName());
        tbNotice.setUid(toUid);
        tbNotice.setClearTime(DateUtil.getDateAfter(new Date(),1));
        noticeService.addNotice(tbNotice);
    }


    @Override
    public UserInfoRequest findCurrentEditUserInfo() throws BizException {
        Optional.ofNullable(CurrentUser.get()).orElseThrow(() -> new BizException("当前用户不存在"));
        UserInfoRequest userInfoRequest = new UserInfoRequest();
        //先获取是否存在审核数据
        TbUserDateCheck userDateCheck = tbUserDateCheckMapper.selectByPrimaryKey(CurrentUser.get().getId());
        if (userDateCheck != null) {
            BeanUtils.copyProperties(userDateCheck, userInfoRequest);
            String photo = userDateCheck.getPhoto();
            if (!StringUtils.isEmpty(photo)) {
                List<String> gsonToList = GsonUtil.gsonToList(photo, String.class);
                List<String> collect = gsonToList.stream().map((e) -> AidouImageUtil.getHttpUrl(e)).collect(Collectors.toList());
                userInfoRequest.setPhoto(collect);
            }
            if (!StringUtils.isEmpty(userInfoRequest.getTopImage())) {
                userInfoRequest.setTopImage(AidouImageUtil.getHttpUrl(userInfoRequest.getTopImage()));
            }
        } else {
            //需要从用户资料里面获取
            TbUser tbUser = tbUserMapper.selectByPrimaryKey(CurrentUser.get().getId());
            BeanUtils.copyProperties(tbUser, userInfoRequest);
            TbUserContent userContent = userInfoService.findUserContentByUid(CurrentUser.get().getId());
            BeanUtils.copyProperties(userContent, userInfoRequest);
            String topImage = userImageService.findUserTopImageByUid(CurrentUser.get().getId(), -1);
            userInfoRequest.setTopImage(topImage);
            List<String> userPhoto = userImageService.findUserPhotoById(CurrentUser.get().getId());
            userInfoRequest.setPhoto(userPhoto);
        }
        //获取当前资料数据
        return userInfoRequest;
    }

    @Override
    public UserCenterInfoRespone findUserCenterInfo() throws BizException {
        UserCenterInfoRespone centerInfoRespone = new UserCenterInfoRespone();
        centerInfoRespone.setUid(CurrentUser.get().getId());
        String nikeName = CurrentUser.get().getNikeName();
        centerInfoRespone.setNikeName(EmojiUtil.emojiConverterUnicodeStr(nikeName));
        //我喜欢的
        Integer likeCount = friendApplicationService.findLikeCount();
        centerInfoRespone.setLikeCount(likeCount);
        //喜欢我的
        Integer likeMeCount = friendApplicationService.findLikeMeCount();
        centerInfoRespone.setLikeMeCount(likeMeCount);
        //互相喜欢
        Integer eachLikeCount = friendApplicationService.findEachLikeCount();
        centerInfoRespone.setLikeEachCount(eachLikeCount);
        //首图
        centerInfoRespone.setTopImage(userImageService.findUserFace());
        TbUser tbUser = tbUserMapper.selectByPrimaryKey(CurrentUser.get().getId());
        //学校
        centerInfoRespone.setSchool(tbUser.getSchool());
        //学历
        centerInfoRespone.setEducation(tbUser.getEducation());
        return centerInfoRespone;
    }

    @Override
    public UserToken findCurrentUserStatus() {
        Long id = CurrentUser.get().getId();
        UserToken userToken = new UserToken();
        TbUser tbUser = tbUserMapper.selectByPrimaryKey(id);
        //用户状态
        userToken.setStatus(tbUser.getStatus());
        //实名认证
        userToken.setRealName(tbUser.getRealName());
        userToken.setAccountStatus(tbUser.getAccountStatus());
        userToken.setRedCount(lineService.findRedCountByUid(id));
        userToken.setUid(tbUser.getUid().toString());
        //牵线数量
        userToken.setMatchmakingCount(noticeService.selectNoticeCountByUid(id, NoticeEnum.NOTICE_STATUS1));
        //发现数量
        userToken.setFindCount(noticeService.selectNoticeCountByUid(id, NoticeEnum.NOTICE_STATUS2));
        return userToken;
    }

    @Override
    public List<Long> findSystemGiftUser() {
        TbUserExample example = new TbUserExample();
        //实名认证
        //非红娘添加的用户
        //单身用户
        //UserStatusEnum.CERTIFIED.getIndex()
        List<Integer> ids = new ArrayList<>();
        ids.add(UserStatusEnum.CERTIFIED.getIndex());
        ids.add(UserStatusEnum.HANDS.getIndex());
        example.createCriteria().andRealNameEqualTo(true).andMidIsNull().andStatusIn(ids);
        List<TbUser> tbUsers = tbUserMapper.selectByExample(example);
        if (tbUsers.isEmpty()) {
            return new ArrayList<>();
        }
        List<Long> collect = tbUsers.stream().map((e) -> e.getUid()).collect(Collectors.toList());
        return collect;
    }

    @Override
    public TbUser findUserById(Long uid) {
        TbUser tbUser = tbUserMapper.selectByPrimaryKey(uid);
        return tbUser;
    }

    @Override
    public List<TbUser> findUserByIdLiST(List<Long> idList) {
        TbUserExample example=new TbUserExample();
        example.createCriteria().andUidIn(idList);
        return tbUserMapper.selectByExample(example);
    }

    @Override
    @Transactional(rollbackFor = BizException.class)
    public void agreeLike(Long id) throws BizException {
        TbFriendApplication friendApplication = tbFriendApplicationMapper.selectByPrimaryKey(id);
        Optional.ofNullable(friendApplication).orElseThrow(() -> new BizException("数据不存在"));
        Long uid = CurrentUser.get().getId();
        if (friendApplication.getIslike()) {
            throw new BizException("已牵线");
        }
        //判断对方用户状态
        TbUser tbUser = tbUserMapper.selectByPrimaryKey(friendApplication.getUserid());
        Optional.ofNullable(tbUser).orElseThrow(() -> new BizException("用户不存在"));
        if (tbUser.getStatus() == UserStatusEnum.HANDS.getIndex()) {
            friendApplication.setStatus(-1);
            friendApplication.setUpdated(new Date());
            friendApplicationService.updateUserLikeStatus(friendApplication);
            throw new BizException("对方牵线中!");
        }
        //判断当前申请信息和当前用户是否存在联系
        if (friendApplication.getUserid().longValue() != uid.longValue() &&
                friendApplication.getFriendId().longValue() != uid.longValue()) {
            throw new BizException("非法用户");
        }
        //状态1：发送 -1 作废 2：拒绝
        friendApplication.setIslike(true);
        friendApplication.setStatus(AppStatusEnum.APPLICATION_STATUS3.getIndex());
        friendApplication.setUpdated(new Date());
        //该用户所有发送的红线全部为隐藏状态
        friendApplicationService.hideFriendApplicationByUid(CurrentUser.get().getId());
        friendApplicationService.hideFriendApplicationByUid(friendApplication.getUserid());
        //修改用户状态
        userInfoService.updateUserStatus(friendApplication.getFriendId(), UserStatusEnum.HANDS);
        userInfoService.updateUserStatus(friendApplication.getUserid(), UserStatusEnum.HANDS);
        WeChatMessageModel messageRequest = new WeChatMessageModel();
        messageRequest.setResult("牵线成功");
        messageRequest.setUid(friendApplication.getUserid());
        messageRequest.setPage("pages/login/index/index");
        messageRequest.setRemarks("快进入小程序，点击中间爱心按钮查看您的有缘人");
        weChatMessagePushService.matchmakingResultMessage(messageRequest);
        friendApplicationService.updateUserLikeStatus(friendApplication);
        TbNotice tbNotice = new TbNotice();
        tbNotice.setContent("牵线同意");
        tbNotice.setNoticeType(NoticeEnum.NOTICE_STATUS1.getIndex());
        tbNotice.setNoticeTypeName(NoticeEnum.NOTICE_STATUS1.getName());
        tbNotice.setUid(friendApplication.getUserid());
        tbNotice.setClearTime(DateUtil.getDateAfter(new Date(),3));
        noticeService.addNotice(tbNotice);

    }

    @Override
    @Transactional(rollbackFor = BizException.class)
    public void refuseLike(Long id) throws BizException {
        //牵线拒绝
        TbFriendApplication friendApplication = tbFriendApplicationMapper.selectByPrimaryKey(id);
        Optional.ofNullable(friendApplication).orElseThrow(() -> new BizException("数据不存在"));
        Long uid = CurrentUser.get().getId();
        if (friendApplication.getIslike()) {
            if (friendApplication.getUserid().longValue() != uid.longValue() &&
                    friendApplication.getFriendId().longValue() != uid.longValue()) {
                throw new BizException("非法用户");
            }
            friendApplicationService.showFriendApplicationByUid(friendApplication.getFriendId());
            friendApplicationService.showFriendApplicationByUid(friendApplication.getUserid());
            tbFriendApplicationMapper.deleteByPrimaryKey(id);
            userInfoService.updateUserStatus(friendApplication.getFriendId(), UserStatusEnum.CERTIFIED);
            userInfoService.updateUserStatus(friendApplication.getUserid(), UserStatusEnum.CERTIFIED);
            WeChatMessageModel wechatModel = new WeChatMessageModel();
            Long wechatFromUid = friendApplication.getUserid().longValue() == uid ? friendApplication.getFriendId() : friendApplication.getUserid();
            wechatModel.setUid(wechatFromUid);
            wechatModel.setResult("匹配失败");
            wechatModel.setPage("pages/login/index/index");
            wechatModel.setRemarks("用户:" + EmojiUtil.emojiConverterToAlias(CurrentUser.get().getNikeName()) + "断开了牵线！");
            weChatMessagePushService.matchFailedResultMessage(wechatModel);
        }

    }

    @Transactional(rollbackFor = BizException.class)
    @Override
    public void applicationRefuse(Long id) throws BizException {
        //查询牵线状态
        TbFriendApplication friendApplication = tbFriendApplicationMapper.selectByPrimaryKey(id);
        Optional.ofNullable(friendApplication).orElseThrow(() -> new BizException("数据不存在"));
        tbFriendApplicationMapper.deleteByPrimaryKey(id);
        //通知
        WeChatMessageModel wechatModel = new WeChatMessageModel();
        Long wechatFromUid = friendApplication.getUserid().longValue() == CurrentUser.get().getId() ? friendApplication.getFriendId() : friendApplication.getUserid();
        wechatModel.setResult("牵线拒绝");
        wechatModel.setUid(wechatFromUid);
        wechatModel.setRemarks("红线已退还");
        wechatModel.setUid(friendApplication.getUserid());
        wechatModel.setPage("pages/login/index/index");
        weChatMessagePushService.matchmakingResultMessage(wechatModel);
        //退还红线
        lineService.addLine(friendApplication.getUserid(), LineSourceType.BACK, 1);
    }

    @Override
    public void updatedUserSmallInfo(UserSmallInfoRequest userSmallInfoRequest) {
        TbUser tbUser = tbUserMapper.selectByPrimaryKey(CurrentUser.get().getId());
        tbUser.setNikename(userSmallInfoRequest.getNikeName());
        tbUser.setAvatarurl(userSmallInfoRequest.getFaceUrl());
        tbUser.setUpdated(new Date());
        tbUserMapper.updateByPrimaryKeySelective(tbUser);
    }


}
