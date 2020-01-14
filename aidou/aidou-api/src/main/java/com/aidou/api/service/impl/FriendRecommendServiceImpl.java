package com.aidou.api.service.impl;

import com.aidou.api.common.cache.RedisDao;
import com.aidou.api.common.cache.RedisKey;
import com.aidou.api.service.*;
import com.aidou.api.vo.request.UserRecommendListRequest;
import com.aidou.api.vo.user.ReommendUserVo;
import com.aidou.api.vo.user.UserDetailsVo;
import com.aidou.dao.entity.*;
import com.aidou.dao.mapper.ExtTbUserMapper;
import com.aidou.dao.mapper.TbUserMapper;
import com.aidou.librec.vo.UserVo;
import com.aidou.util.entity.AidouResult;
import com.aidou.util.entity.CurrentUser;
import com.aidou.util.enums.AppStatusEnum;
import com.aidou.util.enums.UserStatusEnum;
import com.aidou.util.exception.BizException;
import com.aidou.util.tool.EmojiUtil;
import com.aidou.util.tool.GsonUtil;
import com.aidou.util.tool.UserDateUtil;
import com.aidou.util.tool.UserTool;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by yingjiafeng on 2019/5/20.
 */
@Service
public class FriendRecommendServiceImpl implements FriendRecommendService {


    @Resource
    private UserImageService userImageService;

    @Resource
    private TbUserMapper tbUserMapper;

    @Resource
    private ExtTbUserMapper extTbUserMapper;

    @Resource
    private RedisDao redisDao;

    @Resource
    private UserInfoService userInfoService;

    @Value("${IMAGE_SERVER_URL}")
    private String IMAGE_SERVER_URL;


    /**
     * 用户推荐主页面
     *
     * @return
     */
    @Override
    public AidouResult searchlist(UserRecommendListRequest userRecommendListRequest) throws BizException {
        PageHelper.startPage(userRecommendListRequest.getPage(), userRecommendListRequest.getLimit());
        List<TbUserLastTimeVo> userLastTimeVoList = extTbUserMapper.selectLastTimeUserList(CurrentUser.get().getSex()==1?2:1);
        List<ReommendUserVo> reommendUserVoList = new ArrayList<>();
        for (TbUserLastTimeVo tbUserLastTimeVo : userLastTimeVoList) {
            System.out.println(GsonUtil.gsonString(tbUserLastTimeVo));
            ReommendUserVo item = new ReommendUserVo();
            item.setUid(tbUserLastTimeVo.getUid());
            item.setNikename(tbUserLastTimeVo.getNikename());
            item.setTopImage(UserTool.topImageCover(tbUserLastTimeVo.getTopImage(), CurrentUser.get().getSex()));
            item.setNikename(EmojiUtil.emojiConverterUnicodeStr(tbUserLastTimeVo.getNikename()));
            item.setAge(UserDateUtil.getAgeByBirth(tbUserLastTimeVo.getBirthday()) + "岁");
            item.setConstellation(UserDateUtil.findConstellation(tbUserLastTimeVo.getBirthday()));
            item.setLastTime(tbUserLastTimeVo.getLastTime().getTime() + "");
            item.setContent(tbUserLastTimeVo.getContent());
            item.setWork(tbUserLastTimeVo.getWork());
            item.setAddress(tbUserLastTimeVo.getAddress());
            item.setSex(CurrentUser.get().getSex());
            item.setHeight(tbUserLastTimeVo.getHeight());
            //头像
            item.setFace(tbUserLastTimeVo.getAvatarurl());
            item.setEducation(tbUserLastTimeVo.getEducation());
            item.setMarriage(tbUserLastTimeVo.getMarriage());
            item.setHouseCar(tbUserLastTimeVo.getHouseCar());
            reommendUserVoList.add(item);
        }
        PageInfo<TbUserLastTimeVo> pageInfo = new PageInfo<>(userLastTimeVoList);
        AidouResult success = AidouResult.success(reommendUserVoList);
        success.setTotal(pageInfo.getTotal());
        return success;
    }


    @Override
    public UserDetailsVo findUserDescriptByUid(Long uid) throws BizException {
        String redisDaoValue = redisDao.getValue(RedisKey.USER_INFO.suffix(uid.toString()).toString());
        if (StringUtil.isNotEmpty(redisDaoValue)) {
            return GsonUtil.gsonToBean(redisDaoValue, UserDetailsVo.class);
        }
        TbUser tbUser = tbUserMapper.selectByPrimaryKey(uid);
        if (tbUser == null) {
            throw new BizException("用户不存在");
        }
        UserDetailsVo userDetailsVo = new UserDetailsVo();
        //获取相册
        List<String> userPhotoById = userImageService.findUserPhotoById(uid);
        userDetailsVo.setPhoto(userPhotoById);
        //首图
        userDetailsVo.setTopImage(userImageService.findUserTopImageByUid(uid, tbUser.getSex()));
        //获取详情
        BeanUtils.copyProperties(tbUser, userDetailsVo);
        userDetailsVo.setNikename(EmojiUtil.emojiConverterUnicodeStr(tbUser.getNikename()));
        userDetailsVo.setUid(uid.toString());
        userDetailsVo.setAddress(tbUser.getTown());
        userDetailsVo.setAge(UserDateUtil.getAgeByBirth(tbUser.getBirthday()) + "岁");
        userDetailsVo.setConstellation(UserDateUtil.findConstellation(tbUser.getBirthday()));
        TbUserContent userContent = userInfoService.findUserContentByUid(uid);
        userDetailsVo.setContent(EmojiUtil.emojiConverterUnicodeStr(userContent.getContent()));
        userDetailsVo.setHousehold(tbUser.getHousehold());
        userDetailsVo.setFavoriteTa(EmojiUtil.emojiConverterUnicodeStr(userContent.getFavoriteTa()));
        userDetailsVo.setInterest(EmojiUtil.emojiConverterUnicodeStr(userContent.getInterest()));
        userDetailsVo.setSex(tbUser.getSex());
        //判断是否是红娘用户
        if (tbUser.getMid() != null && tbUser.getMid().longValue() > 0) {
            //红娘用户设置红娘联系方式
            TbUser tbUser1 = tbUserMapper.selectByPrimaryKey(tbUser.getMid());
            if (tbUser1.getStatus() != UserStatusEnum.STOP.getIndex()) {
                userDetailsVo.setMatchmakerMobile(tbUser1.getMobile());
                userDetailsVo.setMatchmakerWechat(tbUser1.getWechat());
            }
        }
        redisDao.setKey(RedisKey.USER_INFO.suffix(uid.toString()).toString(), GsonUtil.gsonString(userDetailsVo), 120);
        return userDetailsVo;
    }

}
