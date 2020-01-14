package com.aidou.api.service.impl;

import com.aidou.api.enums.LineSourceType;
import com.aidou.api.service.*;
import com.aidou.api.vo.user.UserLikeVo;
import com.aidou.dao.entity.TbFriendApplication;
import com.aidou.dao.entity.TbFriendApplicationExample;
import com.aidou.dao.entity.TbUser;
import com.aidou.dao.mapper.TbFriendApplicationMapper;
import com.aidou.util.entity.CurrentUser;
import com.aidou.util.entity.wechat.WeChatMessageModel;
import com.aidou.util.enums.NoticeEnum;
import com.aidou.util.exception.BizException;
import com.aidou.util.tool.DateUtil;
import com.aidou.util.tool.EmojiUtil;
import com.aidou.util.tool.UserDateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by yingjiafeng on 2019/5/24.
 */
@Service
public class FriendApplicationServiceImpl implements FriendApplicationService {

    @Autowired
    private TbFriendApplicationMapper tbFriendApplicationMapper;

    @Resource
    private NoticeService  noticeService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private WeChatMessagePushService  weChatMessagePushService;

    @Autowired
    private UserImageService userImageService;

    @Autowired
    private LineService lineService;
    @Autowired
    private UserCardService  userCardService;


    @Override
    @Transactional(rollbackFor =BizException.class)
    public void createdConfession(Long toid, String content) throws BizException {
        //是否有历史记录
        Long uid = CurrentUser.get().getId();
        TbFriendApplicationExample example = new TbFriendApplicationExample();
        example.createCriteria().andUseridEqualTo(uid).andFriendIdEqualTo(toid);
        example.or().andUseridEqualTo(toid).andFriendIdEqualTo(uid);
        List<TbFriendApplication> tbFriendApplications = tbFriendApplicationMapper.selectByExample(example);
        if (tbFriendApplications.isEmpty()){
            TbFriendApplication record = new TbFriendApplication();
            record.setUserid(CurrentUser.get().getId());
            record.setFriendId(toid);
            record.setIslike(false);
            record.setLineBack(DateUtil.getDateAfter(new Date(), 1));
            record.setCreated(new Date());
            record.setUpdated(new Date());
            record.setContent(content);
            tbFriendApplicationMapper.insertSelective(record);
        }else {
                throw new BizException("已申请,请耐心等待对方回复");
        }
    }

    /**
     * 是否正在表白中
     * @param tagerUid
     * @return
     */
    @Override
    public boolean isConfession(Long tagerUid) {
        if (CurrentUser.get() == null) {
            return false;
        }
        Long id = CurrentUser.get().getId();
        TbFriendApplicationExample example = new TbFriendApplicationExample();
        example.createCriteria().andUseridEqualTo(id).andFriendIdEqualTo(tagerUid).andStatusEqualTo(1);
        example.or().andUseridEqualTo(tagerUid).andFriendIdEqualTo(id).andStatusEqualTo(1);
        List<TbFriendApplication> tbFriendApplications = tbFriendApplicationMapper.selectByExample(example);
        return !tbFriendApplications.isEmpty();
    }

    @Override
    public List<UserLikeVo> getLikeEachUserList() throws BizException {
        if (CurrentUser.get() == null) {
            return new ArrayList<>();
        }
        Long id = CurrentUser.get().getId();
        //获取彼此喜欢的
        TbFriendApplicationExample example = getTbFriendApplicationExample(3);
        List<UserLikeVo> userLikeVoList = getUserLikeVoByExample(example, id,3);
        return userLikeVoList;
    }

    /**
     * 获取牵线用户资料
     * @param example
     * @param id
     * @return
     */
    private List<UserLikeVo>  getUserLikeVoByExample(TbFriendApplicationExample example,Long id,Integer  type){
        List<TbFriendApplication> tbFriendApplications = tbFriendApplicationMapper.selectByExample(example);
        if (tbFriendApplications.isEmpty()) {
            return new ArrayList<>();
        }
        List<Long> ids = new ArrayList<>();
        HashMap<Long,TbFriendApplication>   contentHashMap=new HashMap<>();
        tbFriendApplications.forEach((x) -> {
            Long friendId = x.getFriendId();
            Long userid = x.getUserid();
            //获取对方用户ID
            Long aLong = friendId.longValue() != id.longValue() ? friendId : userid;
            contentHashMap.put(aLong,x);
            ids.add(aLong);
        });
        if (ids.isEmpty()) {
            return new ArrayList<>();
        }
        List<UserLikeVo> userLikeVoList = new ArrayList<>();
        //获取用户基本信息
        List<TbUser> userListByIds = userInfoService.findUserListByIds(ids);
        userListByIds.forEach((x) -> {
            UserLikeVo userLikeVo = new UserLikeVo();
            BeanUtils.copyProperties(x, userLikeVo);
            userLikeVo.setNikeName(EmojiUtil.emojiConverterUnicodeStr(x.getNikename()));
            userLikeVo.setAddress(x.getTown());
            String image = userImageService.findUserTopImageByUid(x.getUid(),x.getSex());
            userLikeVo.setImage(image);
            userLikeVo.setId(contentHashMap.get(x.getUid()).getId());
            userLikeVo.setCreated(contentHashMap.get(x.getUid()).getCreated().getTime()+"");
            //星座
            userLikeVo.setConstellation(UserDateUtil.findConstellation(x.getBirthday()));
            //获取表白内容
            userLikeVo.setContent(contentHashMap.get(x.getUid()).getContent());
            userLikeVo.setUid(x.getUid().toString());
            userLikeVo.setAge(UserDateUtil.getAgeByBirth(x.getBirthday()) + "岁");
            if (type==3){
                //获取隐私资料
                if (x.getRealName()){
                    //姓名
                    String cadNameById = userCardService.findCadNameById(x.getUid());
                    userLikeVo.setName(cadNameById);
                }
            }else {
                userLikeVo.setHousehold(null);
            }
            userLikeVoList.add(userLikeVo);
        });
        return userLikeVoList;

    }

    @Override
    public List<UserLikeVo> getLikeAppleList() {
        //牵线申请
        if (CurrentUser.get() == null) {
            return new ArrayList<>();
        }
        TbFriendApplicationExample example = new TbFriendApplicationExample();
        example.createCriteria()
                .andFriendIdEqualTo(CurrentUser.get().getId())
                .andStatusEqualTo(1)
                .andIslikeEqualTo(false);
        Long id = CurrentUser.get().getId();
        List<UserLikeVo> userLikeVoList = getUserLikeVoByExample(example, id,-1);
        //去除红线
        noticeService.deleteNoticeByUid(CurrentUser.get().getId(), NoticeEnum.NOTICE_STATUS1);
        return userLikeVoList;
    }

    @Override
    public int updateUserLikeStatus(TbFriendApplication friendApplication) throws BizException {
        Optional.ofNullable(friendApplication).orElseThrow(NullPointerException::new);
        return tbFriendApplicationMapper.updateByPrimaryKeySelective(friendApplication);
    }


    @Override
    public Integer findLikeCount() throws BizException {
        //获取我喜欢的
        TbFriendApplicationExample example = getTbFriendApplicationExample(1);
        int i = tbFriendApplicationMapper.countByExample(example);
        return i ;
    }


    /**
     * 获取牵线查询条件
     * @param type  1:  我喜欢的  2: 喜欢我的  3：互相喜欢
     * @return
     */
    private TbFriendApplicationExample getTbFriendApplicationExample(Integer  type) {
        TbFriendApplicationExample example = new TbFriendApplicationExample();
        switch (type){
            case 1:
                example.createCriteria()
                        .andUseridEqualTo(CurrentUser.get().getId());
                example.or().andFriendIdEqualTo(CurrentUser.get().getId()).andIslikeEqualTo(true);
                break;
            case 2:
                example.createCriteria().andFriendIdEqualTo(CurrentUser.get().getId());
                break;
            case 3:
                example.createCriteria().andUseridEqualTo(CurrentUser.get().getId()).andStatusEqualTo(type).andIslikeEqualTo(true);
                example.or().andFriendIdEqualTo(CurrentUser.get().getId()).andStatusEqualTo(type).andIslikeEqualTo(true);
                break;
        }
        return example;
    }

    @Override
    public Integer findLikeMeCount() throws BizException {
        //获取喜欢我的
        TbFriendApplicationExample example = getTbFriendApplicationExample(2);
        int i = tbFriendApplicationMapper.countByExample(example);
        return i ;
    }

    @Override
    public Integer findEachLikeCount() throws BizException {
        //获取互相喜欢
        TbFriendApplicationExample example = getTbFriendApplicationExample(3);
        int i = tbFriendApplicationMapper.countByExample(example);
        return i ;
    }


    @Override
    public void hideFriendApplicationByUid(Long uid) throws BizException {
        TbFriendApplication friendApplication=new TbFriendApplication();
        TbFriendApplicationExample example=new TbFriendApplicationExample();
        example.createCriteria().andUseridEqualTo(uid);
        friendApplication.setStatus(-1);
        friendApplication.setUpdated(new Date());
        tbFriendApplicationMapper.updateByExampleSelective(friendApplication, example);
    }

    @Override
    public void showFriendApplicationByUid(Long uid) throws BizException {
        TbFriendApplication friendApplication=new TbFriendApplication();
        TbFriendApplicationExample example=new TbFriendApplicationExample();
        example.createCriteria().andUseridEqualTo(uid);
        friendApplication.setStatus(1);
        friendApplication.setUpdated(new Date());
        tbFriendApplicationMapper.updateByExampleSelective(friendApplication, example);
    }

    @Transactional(rollbackFor = BizException.class)
    @Override
    public void goBackToUser(List<TbFriendApplication> tbFriendApplications) {
        if (tbFriendApplications.isEmpty()) {
            return;
        }
        List<Long> idList = tbFriendApplications.stream().map((e) -> e.getId()).collect(Collectors.toList());
        //删除牵线申请
        TbFriendApplicationExample example=new TbFriendApplicationExample();
        example.createCriteria().andIdIn(idList);
        int i = tbFriendApplicationMapper.deleteByExample(example);
        if (i<idList.size()){
            throw new BizException("红线退回失败");
        }
        tbFriendApplications.forEach((x) -> {
            //有可以回收的红线
            lineService.addLine(x.getUserid(),LineSourceType.BACK,1);
            //通知
            WeChatMessageModel request=new WeChatMessageModel();
            request.setResult("牵线失败");
            request.setRemarks("您离脱单只差一次牵线成功， 红线已返还到您的账户");
            request.setUid(x.getUserid());
            request.setPage("pages/login/index/index");
            weChatMessagePushService.matchmakingResultMessage(request);
        });
    }

}
