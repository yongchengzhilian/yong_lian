package com.aidou.api.service.impl;

import com.aidou.api.common.cache.RedisDao;
import com.aidou.api.common.cache.RedisKey;
import com.aidou.api.enums.CertificationStatusEnum;
import com.aidou.api.enums.LineSourceType;
import com.aidou.api.service.*;
import com.aidou.api.util.AidouImageUtil;
import com.aidou.api.vo.request.ReportUserRequest;
import com.aidou.api.vo.request.SchoolCheckRequest;
import com.aidou.api.vo.request.UserCheckRequest;
import com.aidou.api.vo.request.UserReportRequest;
import com.aidou.api.vo.respone.ReportUserItem;
import com.aidou.dao.entity.*;
import com.aidou.dao.mapper.*;
import com.aidou.util.entity.CurrentUser;
import com.aidou.util.entity.wechat.WeChatMessageModel;
import com.aidou.util.enums.CheckStatusEnum;
import com.aidou.util.enums.ImageStatusEnum;
import com.aidou.util.enums.UserStatusEnum;
import com.aidou.util.exception.BizException;
import com.aidou.util.tool.GsonUtil;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 举报
 * Created by yingjiafeng on 2019/6/3.
 */
@Slf4j
@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private TbReportMapper tbReportMapper;

    @Autowired
    private TbUserMapper tbUserMapper;

    @Autowired
    private TbCardMapper  tbCardMapper;

    @Autowired
    private TbUserContentMapper tbUserContentMapper;

    @Autowired
    private UserImageService userImageService;

    @Autowired
    private RedisDao redisDao;


    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UserService userService;

    @Autowired
    private TbUserRelationMapper tbUserRelationMapper;

    @Autowired
    private TbUserDateCheckMapper tbUserDateCheckMapper;

    @Autowired
    private WeChatMessagePushService weChatMessagePushService;

    @Autowired
    private NotifyService notifyService;

    @Autowired
    private LineService lineService;


    @Override
    public void reportUser(ReportUserRequest reportUserRequest) throws BizException {
        //添加举报信息
        Long id = CurrentUser.get().getId();
        TbReport record = new TbReport();
        record.setReportUid(id);
        record.setRemarks(reportUserRequest.getRemarks());
        record.setCreated(new Date());
        record.setUpdated(new Date());
        record.setTargetUid(reportUserRequest.getReportUid());
        if (reportUserRequest.getPhoto() != null && !reportUserRequest.getPhoto().isEmpty()) {
            record.setImage(GsonUtil.gsonString(reportUserRequest.getPhoto()).replaceAll("http://cdn.aidou.online/", ""));
        }
        tbReportMapper.insertSelective(record);
    }

    @Transactional(rollbackFor = BizException.class)
    @Override
    public void reportCheck(UserReportRequest reportRequest) throws BizException {
        Optional.ofNullable(reportRequest.getId()).orElseThrow(() -> new BizException("审核ID不能为空"));
        TbReport tbReport = tbReportMapper.selectByPrimaryKey(reportRequest.getId());
        Optional.ofNullable(tbReport).orElseThrow(() -> new BizException("举报数据不存在"));
        tbReport.setRemarks(reportRequest.getRemarks());
        tbReport.setStatus(1);
        tbReport.setUpdated(new Date());
        tbReportMapper.updateByPrimaryKey(tbReport);
        //通知
        if (reportRequest.getType() == 1) {
            //关闭用户
            int i = userInfoService.updateUserStatus(tbReport.getTargetUid(), UserStatusEnum.STOP);
            if (i <= 0) {
                throw new BizException("审核失败");
            }
        } else if (reportRequest.getType() == 0) {
            notifyService.addUserNotification(tbReport.getReportUid(), reportRequest.getRemarks(), "用户举报通知");
        }
    }

    @Transactional
    @Override
    public Integer checkUserInfo(UserCheckRequest checkRequest) throws BizException {
        TbUserDateCheck userDateCheck = tbUserDateCheckMapper.selectByPrimaryKey(checkRequest.getUid());
        if (userDateCheck == null) {
            throw new BizException("用户审核数据不存在");
        }
        if (userDateCheck.getStatus().intValue() != CheckStatusEnum.CHECK_ING.getIndex().intValue()) {
            throw new BizException("该数据不需要审核");
        }
        TbUser tbUser = tbUserMapper.selectByPrimaryKey(userDateCheck.getUid());
        TbCard tbCard = tbCardMapper.selectByPrimaryKey(tbUser.getUid());
        if (checkRequest.getStatus().equals(CheckStatusEnum.CHECK.getIndex())) {
            //删除数据  同步到用户资料中
            int count = tbUserDateCheckMapper.deleteByPrimaryKey(userDateCheck.getUid());
            if (count < 1) {
                throw new BizException("删除审核资料失败");
            }
            if (tbUser != null) {
                //初次完善资料增加一条红线
                if (StringUtils.isEmpty(tbUser.getWechat())) {
                    TbUserRelationExample example = new TbUserRelationExample();
                    example.createCriteria().andDpStatusEqualTo(1).andTUserIdEqualTo(tbUser.getUid());
                    List<TbUserRelation> tbUserRelations = tbUserRelationMapper.selectByExample(example);
                    if (!tbUserRelations.isEmpty()) {
                        TbUserRelation tbUserRelation = tbUserRelations.get(0);
                        Long uid = tbUserRelation.getsUserId();
                        lineService.addLine(uid, LineSourceType.SHARE_GIFT, 1);
                    }
                    lineService.addLine(tbUser.getUid(), LineSourceType.GIFT, 1);
                }
                Date created = tbUser.getCreated();
                BeanUtils.copyProperties(userDateCheck, tbUser);
                tbUser.setStatus(UserStatusEnum.CERTIFIED.getIndex());
                tbUser.setUpdated(new Date());
                tbUser.setCreated(created);
                tbUserMapper.updateByPrimaryKey(tbUser);
                //更新介绍
                TbUserContent tbUserContent = tbUserContentMapper.selectByPrimaryKey(userDateCheck.getUid());
                if (tbUserContent != null) {
                    BeanUtils.copyProperties(userDateCheck, tbUserContent);
                    tbUserContent.setUpdated(new Date());
                    tbUserContentMapper.updateByPrimaryKey(tbUserContent);
                } else {
                    tbUserContent = new TbUserContent();
                    BeanUtils.copyProperties(userDateCheck, tbUserContent);
                    tbUserContent.setUpdated(new Date());
                    tbUserContent.setCreated(new Date());
                    tbUserContentMapper.insertSelective(tbUserContent);
                }
                //删除历史相册
                userImageService.deleteUserImg(userDateCheck.getUid());
                //相册
                String photo = userDateCheck.getPhoto();
                if (!StringUtils.isEmpty(photo)) {
                    userImageService.saveImageByImgList(userDateCheck.getUid(), ImageStatusEnum.IMAGE_PHOTO, GsonUtil.gsonToList(photo, String.class));
                }
                String topImage = userDateCheck.getTopImage();
                if (!StringUtils.isEmpty(topImage)) {
                    userImageService.saveImageByImg(userDateCheck.getUid(), ImageStatusEnum.IMAGE_TOP, topImage);
                }

                WeChatMessageModel messageModel = new WeChatMessageModel();
                messageModel.setRemarks("个人资料审核通过");
                messageModel.setResult("通过");
                messageModel.setPage("pages/login/index/index");
                messageModel.setUid(userDateCheck.getUid());
                weChatMessagePushService.checkResultMessage(messageModel);
                //删除缓存
                redisDao.delete(RedisKey.USER_INFO.suffix(checkRequest.getUid().toString()).toString());
                return 2;
            }
        } else {
            //修改用户状态
            if (StringUtils.isEmpty(tbUser.getWechat())) {
                tbUser.setStatus(UserStatusEnum.FULL_DATA.getIndex());
                tbUser.setUpdated(new Date());
                tbUserMapper.updateByPrimaryKey(tbUser);
            }
            userDateCheck.setStatus(checkRequest.getStatus());
            userDateCheck.setRemarks(checkRequest.getRemarks());
            userDateCheck.setUpdated(new Date());
            tbUserDateCheckMapper.updateByPrimaryKey(userDateCheck);
            WeChatMessageModel messageModel = new WeChatMessageModel();
            messageModel.setUid(userDateCheck.getUid());
            messageModel.setResult("审核拒绝");
            messageModel.setPage("pages/login/index/index");
            messageModel.setRemarks(checkRequest.getRemarks());
            weChatMessagePushService.checkResultMessage(messageModel);
            return 1;
        }
        return -1;

    }

    @Transactional(rollbackFor = BizException.class)
    @Override
    public int schoolCheck(SchoolCheckRequest checkRequest) throws BizException {
        //审核学历
        TbUser tbUser = userService.findUserById(checkRequest.getUid());
        //是否审核状态
        if (tbUser.getSchoolVerification().intValue() != CertificationStatusEnum.CERTIFICATION.getIndex()) {
            throw new BizException("非审核状态");
        }
        String remarks;
        if (checkRequest.getStatus().intValue() == CheckStatusEnum.CHECK.getIndex()) {
            //审核通过
            tbUser.setSchoolVerification(CertificationStatusEnum.SUCCESS.getIndex());
            tbUser.setVerificationCode("ok");
            tbUser.setEducation(checkRequest.getEducation());
            tbUser.setSchool(checkRequest.getSchool());
            remarks = "审核通过";
            notifyService.addUserNotification(checkRequest.getUid(), "你好，学历信息审核通过", "学历审核通知");
        } else {
            //拒绝
            tbUser.setSchoolVerification(CertificationStatusEnum.ERROR.getIndex());
            notifyService.addUserNotification(checkRequest.getUid(), "由于:" + checkRequest.getRemarks() + "无法通过审核，你可以重新提交申请。", "学历审核通知");
            remarks = "审核拒绝:" + checkRequest.getRemarks();
        }
        return userInfoService.updateUser(tbUser);
    }

    @Override
    public List<ReportUserItem> findReportUserList(int page, int rows, int status) throws BizException {
        PageHelper.startPage(page, rows);
        TbReportExample example = new TbReportExample();
        //1:审核  2:未审核
        example.createCriteria().andStatusEqualTo(status);
        List<TbReport> tbReports = tbReportMapper.selectByExample(example);
        if (tbReports.isEmpty()) {
            return new ArrayList<>();
        }
        List<ReportUserItem> reportUserItemList = new ArrayList<>();
        tbReports.forEach((x) -> {
            ReportUserItem userItem = new ReportUserItem();
            BeanUtils.copyProperties(x, userItem);
            userItem.setId(x.getId().toString());
            userItem.setReportUid(x.getReportUid().toString());
            userItem.setTargetUid(x.getTargetUid().toString());
            String image = x.getImage();
            if (!StringUtils.isEmpty(image)) {
                List<String> imageList = GsonUtil.gsonToList(image, String.class);
                imageList = imageList.stream().map((y) -> AidouImageUtil.getHttpUrl(y)).collect(Collectors.toList());
                userItem.setImageList(imageList);
            }
            //被举报次数
            userItem.setTargetUserReportCount(findReportCountByUid(x.getTargetUid()));
            reportUserItemList.add(userItem);
        });
        return reportUserItemList;
    }

    @Override
    public Integer findReportCountByUid(Long uid) throws BizException {
        //查询被举报次数
        TbReportExample example = new TbReportExample();
        example.createCriteria().andTargetUidEqualTo(uid);
        int i = tbReportMapper.countByExample(example);
        return i;
    }


}
