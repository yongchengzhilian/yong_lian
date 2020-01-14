package com.aidou.api.service.impl;

import com.aidou.api.service.UserCheckInfoService;
import com.aidou.api.util.AidouImageUtil;
import com.aidou.api.vo.respone.SchoolCheckRespone;
import com.aidou.api.vo.respone.UserDateCheckRespone;
import com.aidou.dao.entity.*;
import com.aidou.dao.mapper.TbCardMapper;
import com.aidou.dao.mapper.TbUserDateCheckMapper;
import com.aidou.dao.mapper.TbUserMapper;
import com.aidou.util.exception.BizException;
import com.aidou.util.tool.EmojiUtil;
import com.aidou.util.tool.GsonUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author yingjiafeng
 * @version 创建时间：2019/7/29 9:46
 */
@Service
public class UserCheckInfoServiceImpl implements UserCheckInfoService {

    @Autowired
    private TbUserDateCheckMapper  tbUserDateCheckMapper;

    @Autowired
    private TbUserMapper tbUserMapper;

    @Autowired
    private TbCardMapper  tbCardMapper;


    /**
     *
     * @param page  页
     * @param rows 行
     * @param status  -1:审核拒绝  1:通过 2：待审核  0：所有
     * @return
     * @throws BizException
     */
    @Override
    public List<UserDateCheckRespone> findAllCheckInfoList(int page, int rows,int status) throws BizException {
        //设置分页信息
        PageHelper.startPage(page, rows);
        TbUserDateCheckExample example=new TbUserDateCheckExample();
            example.createCriteria().andStatusEqualTo(status);
        example.setOrderByClause("updated desc");
        List<UserDateCheckRespone> dateCheckResponeList=new ArrayList<>();
        List<TbUserDateCheck> tbUserDateChecks = tbUserDateCheckMapper.selectByExample(example);
        if (tbUserDateChecks.isEmpty()){
            return dateCheckResponeList;
        }
        tbUserDateChecks.forEach((x)->{
            UserDateCheckRespone  itemCheck=new UserDateCheckRespone();
            BeanUtils.copyProperties(x,itemCheck);
            itemCheck.setUid(x.getUid().toString());
            String topImage = x.getTopImage();
            itemCheck.setTopImageItem(AidouImageUtil.getHttpUrl(topImage));
            String photo = x.getPhoto();
            if (!StringUtils.isEmpty(photo)){
                List<String> photoList = GsonUtil.gsonToList(photo, String.class);
                List<String> collect = photoList.stream().map((y) -> AidouImageUtil.getHttpUrl(y)).collect(Collectors.toList());
                itemCheck.setPhotoList(collect);
            }
            itemCheck.setContent(EmojiUtil.emojiConverterUnicodeStr(itemCheck.getContent()));
            itemCheck.setInterest(EmojiUtil.emojiConverterUnicodeStr(itemCheck.getInterest()));
            itemCheck.setFavoriteTa(EmojiUtil.emojiConverterUnicodeStr(itemCheck.getFavoriteTa()));
            dateCheckResponeList.add(itemCheck);
        });
        return dateCheckResponeList;
    }

    @Override
    public List<SchoolCheckRespone> findAllSchoolList(Integer page, int rows, Integer status) throws BizException {
        PageHelper.startPage(page, rows);
        TbUserExample example=new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        if (status==5){
            //已审核
            criteria.andSchoolVerificationBetween(2,3);
        }else {
            //未审核
            criteria.andSchoolVerificationEqualTo(status);
        }
        criteria.andVerificationCodeIsNotNull();
        example.setOrderByClause("updated desc");
        List<TbUser> tbUsers = tbUserMapper.selectByExample(example);
        if (tbUsers.isEmpty()){
            return new ArrayList<>();
        }
        List<SchoolCheckRespone>  schoolCheckResponeList=new ArrayList<>();
        TbCardExample idCardExample=new TbCardExample();
        idCardExample.createCriteria().andUidIn(tbUsers.stream().map((x) -> x.getUid()).collect(Collectors.toList()));
        List<TbCard> tbCards = tbCardMapper.selectByExample(idCardExample);
        tbUsers.forEach((x)->{
            Optional<TbCard> card = tbCards.stream().filter((y) -> y.getUid().longValue() == x.getUid().longValue()).findFirst();
            SchoolCheckRespone checkRespone=new SchoolCheckRespone();
            checkRespone.setUid(x.getUid().toString());
            if (card.isPresent()){
                checkRespone.setIdCard(card.get().getIdCard());
                checkRespone.setName(card.get().getName());
            }
            checkRespone.setStatus(x.getSchoolVerification());
            checkRespone.setCreated(x.getUpdated());
            checkRespone.setVerificationCode(x.getVerificationCode());
            schoolCheckResponeList.add(checkRespone);
        });

        return schoolCheckResponeList;
    }
}
