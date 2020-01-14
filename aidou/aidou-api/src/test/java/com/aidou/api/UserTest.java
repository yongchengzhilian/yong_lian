package com.aidou.api;

import com.aidou.api.enums.LineSourceType;
import com.aidou.api.service.*;
import com.aidou.api.vo.request.MatchmakerUserInfoRequest;
import com.aidou.dao.entity.*;
import com.aidou.dao.mapper.TbCardMapper;
import com.aidou.dao.mapper.TbFriendApplicationMapper;
import com.aidou.dao.mapper.TbUserContentMapper;
import com.aidou.dao.mapper.TbUserMapper;
import com.aidou.util.entity.CurrentUser;
import com.aidou.util.entity.wechat.UserAccessToken;
import com.aidou.util.entity.wechat.WeChatMessageModel;
import com.aidou.util.enums.ImageStatusEnum;
import com.aidou.util.tool.*;
import com.aidou.util.wechat.WechatUtil;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author yingjiafeng
 * @version 创建时间：2019/7/15 15:03
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    @Autowired
    private TbCardMapper tbCardMapper;

    @Autowired
    private TbUserContentMapper tbUserContentMapper;


    @Autowired
    private TbFriendApplicationMapper tbFriendApplicationMapper;

    @Autowired
    private TbUserMapper tbUserMapper;

    @Autowired
    private FriendRecommendService friendRecommendService;

    @Autowired
    private UserImageService userImageService;

    @Autowired
    private IdWorker idWorker;

    @Test
    public void test2() throws Exception {
        MatchmakerUserInfoRequest userInfo = new MatchmakerUserInfoRequest();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        userInfo.setBirthday(sdf.parse("1992-11-11"));
        userInfo.setSex(2);
        userInfo.setNikename("Nana一丹");
        userInfo.setPhoto(GsonUtil.gsonToList("[\"https://wx-love-img.afunapp.com/FlX8WSw1lJ_aOBqJEE5bbgjh-bP8\",\"https://wx-love-img.afunapp.com/Fnz_mqOmm6OdmjwiImps2FU4BYK5\",\"https://wx-love-img.afunapp.com/FoLFiVFENiV7r5mquqBsdpVPUi0s\",\"https://wx-love-img.afunapp.com/Fk-RVPCpxW9d5iXW63_zNwsUhjie\",\"https://wx-love-img.afunapp.com/FsJfdEihMMs790-BJXcisHbtIAux\"]", String.class));
        userInfo.setTopImage("https://wx-love-img.afunapp.com/FiDilDZZEXIeuWaAEXz9xbIRKu1D");
        userInfo.setWork("IT/互联网");
        userInfo.setWeight(46);
        userInfo.setHeight(162);
        userInfo.setTown("北京市" +
                "-北京市-海淀区");
        userInfo.setMarriage("未婚");
        userInfo.setHousehold("浙江-杭州");
        userInfo.setHouseCar("无车无房");
        userInfo.setEducation("本科");
        userInfo.setIncome("10-20");
        userInfo.setInterest("电影、旅行、相声、滑板、健身");
        userInfo.setContent("我生于江南小镇，成长于一个简单温馨的三口之家，性格里有南方姑娘的温婉，也有女生能顶半边天的独立和个性。父母对我的家教既严格也开明，爸爸是公务员，妈妈就职于银行，从小按琴棋书画的方向培养我哈哈。他们永远会是我最坚强的后盾和最骄傲的榜样，有车准备买房。工作是995的互联网创业公司，负责运营投放团队，真心的热爱工作并能感觉到自己在工作中不断学习和成长，所以就缺一个真心爱的人了。虽然经常感到自己工作很努力了，但是听到四大的朋友说出“我好希望每天十点可以下班”这种话的时候就会觉得其实我还可以更努力");
        userInfo.setFavoriteTa("未来的life partner，希望他大气独立，有能力有勇气，对身高年龄颜值这些外在的倒是没什么要求，地理位置限制不大。希望家庭条件相当，无婚史。另外爱养生爱健身的加分！");
        createAdd(userInfo);
    }

    public void createAdd(MatchmakerUserInfoRequest matchmakerInfoRespone) throws Exception {
        //保存用户信息
        long id = idWorker.nextId();
        TbUser tbuser = new TbUser();
        BeanUtils.copyProperties(matchmakerInfoRespone, tbuser);
        tbuser.setUid(id);
        tbuser.setBirthday(matchmakerInfoRespone.getBirthday());
        tbuser.setStatus(3);
        tbuser.setWechat(CurrentUser.get().getWechat());
        tbuser.setMobile(CurrentUser.get().getMobile());
        tbuser.setSex(matchmakerInfoRespone.getSex());
        tbuser.setMid(CurrentUser.get().getId());
        tbuser.setCreated(new Date());
        tbuser.setUpdated(new Date());
        tbuser.setRealName(true);
        tbUserMapper.insertSelective(tbuser);
        TbUserContent tbUserContent = new TbUserContent();
        tbUserContent.setUid(id);
        tbUserContent.setContent(EmojiUtil.emojiConverterToAlias(matchmakerInfoRespone.getContent()));
        tbUserContent.setInterest(EmojiUtil.emojiConverterToAlias(matchmakerInfoRespone.getInterest()));
        tbUserContent.setFavoriteTa(EmojiUtil.emojiConverterToAlias(matchmakerInfoRespone.getFavoriteTa()));
        tbUserContent.setUpdated(new Date());
        tbUserContent.setCreated(new Date());
        tbUserContentMapper.insertSelective(tbUserContent);
        List<String> photo = matchmakerInfoRespone.getPhoto();
        if (photo != null && !photo.isEmpty()) {
            userImageService.saveImageByImgList(id, ImageStatusEnum.IMAGE_PHOTO, photo);
        }
        String topImage = matchmakerInfoRespone.getTopImage();
        if (!StringUtils.isEmpty(topImage)) {
            userImageService.saveImageByImg(id, ImageStatusEnum.IMAGE_TOP, topImage);
        }

    }


}
