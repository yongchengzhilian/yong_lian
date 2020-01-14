package com.aidou.api.service.impl;

import com.aidou.api.service.NotifyService;
import com.aidou.api.vo.respone.NotifyItemRespone;
import com.aidou.dao.entity.*;
import com.aidou.dao.mapper.TbUserMapper;
import com.aidou.dao.mapper.TbUserMsgMapper;
import com.aidou.dao.mapper.TbUserMsgRelationMapper;
import com.aidou.util.entity.CurrentUser;
import com.aidou.util.exception.BizException;
import com.aidou.util.tool.GsonUtil;
import com.aidou.util.tool.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yingjiafeng
 * @version 创建时间：2019/7/25 9:26
 */
@Service
public class NotifyServiceImpl  implements NotifyService {

    @Autowired
    private TbUserMsgMapper tbUserMsgMapper;
    @Autowired
    private TbUserMsgRelationMapper tbUserMsgRelationMapper;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private TbUserMapper tbUserMapper;


    @Override
    public List<NotifyItemRespone> findNotifyList() throws BizException {
        Long id = CurrentUser.get().getId();
        TbUserMsgRelationExample example=new TbUserMsgRelationExample();
        example.createCriteria().andUidEqualTo(id);
        List<TbUserMsgRelation> tbUserMsgRelations = tbUserMsgRelationMapper.selectByExample(example);
        if (tbUserMsgRelations.isEmpty()){
            return new ArrayList<>();
        }
        List<NotifyItemRespone>  notifyItemResponeList=new ArrayList<>();
        List<Long> midList = tbUserMsgRelations.stream().map((x) -> x.getMid()).collect(Collectors.toList());
        TbUserMsgExample msgExample=new TbUserMsgExample();
        msgExample.createCriteria().andMidIn(midList);
        msgExample.setOrderByClause("created desc");
        List<TbUserMsg> tbUserMsgs = tbUserMsgMapper.selectByExample(msgExample);
        tbUserMsgs.forEach((x)->{
            NotifyItemRespone itemRespone=new NotifyItemRespone();
            itemRespone.setTitle(x.getTitle());
            TbUserMsgRelation tbUserMsgRelation = tbUserMsgRelations.stream().filter((y) -> y.getMid().longValue() == x.getMid()).findFirst().get();
            itemRespone.setIsRead(tbUserMsgRelation.getIsRead());
            itemRespone.setMid(x.getMid().toString());
            itemRespone.setCreated(x.getCreated());
            notifyItemResponeList.add(itemRespone);
        });
        return notifyItemResponeList;
    }

    @Override
    public int deleteNotigyById(Long mid) throws BizException {
        Long id = CurrentUser.get().getId();
        TbUserMsgRelationExample example=new TbUserMsgRelationExample();
        example.createCriteria().andMidEqualTo(mid).andUidEqualTo(id);
        int i = tbUserMsgRelationMapper.deleteByExample(example);
        if (i>0){
            tbUserMsgMapper.deleteByPrimaryKey(mid);
        }
        return 0;
    }


    @Override
    public NotifyItemRespone getNotifyByMid(Long mid) throws BizException {
        Long uid =CurrentUser.get().getId();
        TbUserMsg tbUserMsg = tbUserMsgMapper.selectByPrimaryKey(mid);
        //標記狀態已讀
        TbUserMsgRelationExample example=new TbUserMsgRelationExample();
        example.createCriteria().andMidEqualTo(mid).andIsReadEqualTo(false);
        List<TbUserMsgRelation> tbUserMsgRelations = tbUserMsgRelationMapper.selectByExample(example);
        if (!tbUserMsgRelations.isEmpty()){
            TbUserMsgRelation tbUserMsgRelation = tbUserMsgRelations.get(0);
            tbUserMsgRelation.setIsRead(true);
            tbUserMsgRelation.setUpdated(new Date());
            tbUserMsgRelationMapper.updateByPrimaryKeySelective(tbUserMsgRelation);
        }
        //修改閱讀狀態
        NotifyItemRespone  itemRespone=new NotifyItemRespone();
        if (tbUserMsg==null){
            return itemRespone;
        }
        itemRespone.setCreated(tbUserMsg.getCreated());
        itemRespone.setMid(mid.toString());
        itemRespone.setTitle(tbUserMsg.getTitle());
        itemRespone.setContent(tbUserMsg.getContent());
        System.out.println(GsonUtil.gsonString(itemRespone));
        return itemRespone;
    }

    @Override
    public int searchIsNewNotifyBy() throws BizException {
        Long uid =CurrentUser.get().getId();
        TbUserMsgRelationExample example=new TbUserMsgRelationExample();
        example.createCriteria().andIsReadEqualTo(false).andUidEqualTo(uid);
        return tbUserMsgRelationMapper.countByExample(example);
    }





    @Transactional(rollbackFor = BizException.class)
    @Override
    public void  addUserFriendNotification( Long uid,Long toUid) throws BizException{
        TbUser tbUser = tbUserMapper.selectByPrimaryKey(toUid);
        if (tbUser==null){
            throw new BizException("被表白用户不存在");
        }
        addUserNotify(uid,"红线申请", "你向用户:"+tbUser.getNikename()+"发送了一条红线,我们已通知ta,请耐心等待! 如有疑问，请联系管理员确认。");
        addUserNotify(toUid,"红线申请", "你好,用户:"+CurrentUser.get().getNikeName()+"向你发送了一条红线,请查收! 如有疑问，请联系管理员确认。");
    }

    @Transactional(rollbackFor = BizException.class)
    @Override
    public void friendAgreeNotification(TbFriendApplication friendApplication) throws BizException {
        //牵线同意通知
        Long friendId = friendApplication.getFriendId();
        Long userid = friendApplication.getUserid();
        TbUser tbUser = tbUserMapper.selectByPrimaryKey(userid);
        if (tbUser==null){
            throw  new BizException("用户不存在");
        }
        addUserNotify(userid,"好友申请通知", "你好,用户:"+CurrentUser.get().getNikeName()+"同意了您的好友申请,请加微信沟通! 如有疑问，请联系管理员确认。");
        addUserNotify(friendId,"好友申请通知", "你同意了用户:"+tbUser.getNikename()+"的好友申请,请加微信沟通! 如有疑问，请联系管理员确认。");

    }

    @Override
    public void addUserNotification(Long uid, String content, String title) throws BizException {
        addUserNotify(uid,title,content);
    }

    private void addUserNotify(Long uid, String title, String content) {
        long id = idWorker.nextId();
        //添加用户通知
        TbUserMsgRelation userMsgRelation=new TbUserMsgRelation();
        userMsgRelation.setMid(id);
        userMsgRelation.setUid(uid);
        userMsgRelation.setCreated(new Date());
        userMsgRelation.setUpdated(new Date());
        tbUserMsgRelationMapper.insertSelective(userMsgRelation);
        TbUserMsg userMsg=new TbUserMsg();
        userMsg.setMid(id);
        userMsg.setContent(content);
        userMsg.setTitle(title);
        userMsg.setCreated(new Date());
        userMsg.setUpdated(new Date());
        tbUserMsgMapper.insertSelective(userMsg);
    }

}
