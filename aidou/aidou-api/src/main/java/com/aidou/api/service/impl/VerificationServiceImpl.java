package com.aidou.api.service.impl;

import com.aidou.api.common.httpclient.HttpAPIService;
import com.aidou.api.common.httpclient.HttpResult;
import com.aidou.api.enums.CertificationStatusEnum;
import com.aidou.api.service.LineService;
import com.aidou.api.service.NotifyService;
import com.aidou.api.service.UserInfoService;
import com.aidou.api.service.VerificationService;
import com.aidou.api.vo.request.IdCardRequest;
import com.aidou.api.vo.request.IdCardVerRequest;
import com.aidou.api.vo.request.UserInfoRequest;
import com.aidou.api.vo.respone.*;
import com.aidou.dao.entity.*;
import com.aidou.dao.mapper.*;
import com.aidou.util.entity.CurrentUser;
import com.aidou.util.enums.UserStatusEnum;
import com.aidou.util.exception.BizException;
import com.aidou.util.tool.EmojiUtil;
import com.aidou.util.tool.GsonUtil;
import com.aidou.util.tool.IDCardUtil;
import com.aidou.util.tool.IdCardVo;
import com.vdurmont.emoji.EmojiParser;
import org.apache.http.HttpStatus;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created by yingjiafeng on 2019/6/2.
 */

@Service
public class VerificationServiceImpl implements VerificationService {

    @Autowired
    private TbCardMapper tbCardMapper;

    @Autowired
    private TbUserMapper tbUserMapper;

    @Autowired
    private TbZoneMapper tbZoneMapper;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private HttpAPIService httpAPIService;

    @Autowired
    private TbUserDateCheckMapper tbUserDateCheckMapper;


    @Value("${IMAGE_SERVER_URL}")
    private String IMAGE_SERVER_URL;


    @Transactional
    @Override
    public String idCardVerified(IdCardVerRequest idCardVerRequest) throws BizException {
        //身份证验证
        IdCardVo idCardVo = IDCardUtil.IDCardValidate(idCardVerRequest.getIdCard());
        if (!StringUtils.isEmpty(idCardVo.getMsg())) {
            return idCardVo.getMsg();
        }
        //编辑资料
        Long id = CurrentUser.get().getId();
        if (CurrentUser.get().isRealName()) {
            return "该用户已实名认证";
        }
        //修改用户状态
        TbUser tbUser = tbUserMapper.selectByPrimaryKey(id);
        tbUser.setSex(idCardVo.getSex());
        tbUser.setBirthday(idCardVo.getBirth());
        TbZoneExample example = new TbZoneExample();
        example.createCriteria().andZipCodeEqualTo(idCardVerRequest.getIdCard().substring(0, 6));
        List<TbZone> tbZones = tbZoneMapper.selectByExample(example);
        if (!tbZones.isEmpty()) {
            tbUser.setHousehold(idCardVo.getHousehold() + "-" + tbZones.get(0).getName());
        } else {
            tbUser.setHousehold(idCardVo.getHousehold());
        }
        tbUser.setRealName(true);
        tbUser.setUpdated(new Date());
        tbUserMapper.updateByPrimaryKey(tbUser);
        TbCard userCard = new TbCard();
        userCard.setUid(id);
        userCard.setIdCard(idCardVerRequest.getIdCard());
        userCard.setName(idCardVerRequest.getName());
        userCard.setSfzFront(idCardVerRequest.getIdcardFront());
        userCard.setSfzBack(idCardVerRequest.getIdcardFan());
        userCard.setAddress(tbUser.getHousehold());
        userCard.setCreated(new Date());
        userCard.setUpdated(new Date());
        //认证状态 1：认证中 2：拒绝  3:通过
        userCard.setStatus(CertificationStatusEnum.SUCCESS.getIndex());
        int i = tbCardMapper.insertSelective(userCard);
        if (i <= 0) {
            throw new BizException("该身份证已实名， 如有疑问， 请联系客服");
        }
        return "ok";
    }


    /**
     * 用户资料提交审核
     *
     * @param userInfoRequest
     * @throws BizException
     */

    @Override
    public void updateUserInfo(UserInfoRequest userInfoRequest) throws BizException {
        Long id = CurrentUser.get().getId();
        userInfoRequest.setTown(userInfoRequest.getTown().replace("省", "").replaceAll(" ", ""));
        TbUserDateCheck userDateCheck = new TbUserDateCheck();
        tbUserDateCheckMapper.deleteByPrimaryKey(id);
        userDateCheck.setUid(id);
        userDateCheck.setUpdated(new Date());
        userDateCheck.setCreated(new Date());
        userDateCheck.setStatus(2);
        List<String> photo = userInfoRequest.getPhoto();
        if (photo != null && !photo.isEmpty()) {
            String photoJson = GsonUtil.toJsonString(photo);
            String s = photoJson.replaceAll(IMAGE_SERVER_URL, "");
            userDateCheck.setPhoto(s);
        }
        if (!StringUtils.isEmpty(userInfoRequest.getTopImage())) {
            userDateCheck.setTopImage(userInfoRequest.getTopImage().replaceAll(IMAGE_SERVER_URL, ""));
        }
        BeanUtils.copyProperties(userInfoRequest, userDateCheck);
        userDateCheck.setContent(EmojiUtil.emojiConverterToAlias(userInfoRequest.getContent()));
        userDateCheck.setFavoriteTa(EmojiUtil.emojiConverterToAlias(userInfoRequest.getFavoriteTa()));
        userDateCheck.setInterest(EmojiUtil.emojiConverterToAlias(userInfoRequest.getInterest()));
        System.out.println(GsonUtil.gsonString(userDateCheck));
        tbUserDateCheckMapper.insertSelective(userDateCheck);
        //判断用户状态
        Integer status = CurrentUser.get().getStatus();
        if (status == 1) {
            //第一次提交资料审核 把状态修改成正在审核中
            userInfoService.updateUserStatus(id, UserStatusEnum.FULL_DATA_ING);
        }
    }


    @Override
    public String schoolVerified(String verification) throws BizException {
        Optional.ofNullable(verification).orElseThrow(() -> new BizException("在线验证码不能为空"));
        //通过在线验证码解析学籍信息
        userInfoService.addSchoolCode(verification);
        return "学历认证提交成功,等待后台工作人员审核";
    }

    @Override
    public IdCardAppRespone idCardOrc(IdCardRequest idCardRequest) {
        //身份证识别
        Map<String, String> mapParemt = new HashMap<>();
        try {
            mapParemt.put("api_key", "q5V4R4LNNpz1fSiOAss-yYygIF2Da3Kw");
            mapParemt.put("api_secret", "gzemuQpXcc9DruKQNp6gY8Q4EK0bjSg7");
            mapParemt.put("image_url", idCardRequest.getImgUrl());
            HttpResult httpResult = httpAPIService.doPost("https://api-cn.faceplusplus.com/cardpp/v1/ocridcard", mapParemt);
            System.out.println(httpResult.getCode() + httpResult.getBody());
            if (httpResult.getCode() == HttpStatus.SC_OK) {
                if (idCardRequest.getType().equalsIgnoreCase("front")) {
                    IdCardRespone idCardRespone = GsonUtil.gsonToBean(httpResult.getBody(), IdCardRespone.class);
                    if (idCardRespone.getCards().isEmpty()) {
                        return new IdCardAppRespone();
                    }
                    CardsBean cardsBean = idCardRespone.getCards().get(0);
                    return new IdCardAppRespone(cardsBean.getName(), cardsBean.getId_card_number(), cardsBean.getType(), cardsBean.getSide());
                } else if (idCardRequest.getType().equalsIgnoreCase("back")) {
                    CardBankRespone cardBankRespone = GsonUtil.gsonToBean(httpResult.getBody(), CardBankRespone.class);
                    if (cardBankRespone.getCards().isEmpty()) {
                        return new IdCardAppRespone();
                    }
                    CardBackBean cardBackBean = cardBankRespone.getCards().get(0);
                    return new IdCardAppRespone(cardBackBean.getType(), cardBackBean.getSide());
                }
                return new IdCardAppRespone();
            } else {
                throw new BizException(httpResult.getBody());
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new IdCardAppRespone();
        }
    }


}
