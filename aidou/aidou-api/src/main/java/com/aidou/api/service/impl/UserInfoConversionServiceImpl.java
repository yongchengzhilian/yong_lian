package com.aidou.api.service.impl;

import com.aidou.api.service.UserImageService;
import com.aidou.api.service.UserInfoConversionService;
import com.aidou.api.service.UserInfoService;
import com.aidou.api.vo.user.ReommendUserVo;
import com.aidou.dao.entity.TbUser;
import com.aidou.dao.entity.TbUserContent;
import com.aidou.util.tool.EmojiUtil;
import com.aidou.util.tool.RelativeDateFormat;
import com.aidou.util.tool.UserDateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yingjiafeng
 * @version 创建时间：2019/7/9 14:12
 */
@Service
public class UserInfoConversionServiceImpl implements UserInfoConversionService {


    @Autowired
    private UserImageService userImageService;

    @Autowired
    private UserInfoService userInfoService;


    @Override
    public List<ReommendUserVo> tbUserConversionReommendUserVo(List<TbUser> friendList) {
        List<ReommendUserVo> reommendUserVos = new ArrayList<>();
        for (TbUser x : friendList) {
            ReommendUserVo reommendUserVo = new ReommendUserVo();
            reommendUserVo.setUid(x.getUid().toString());
            //获取相册
            reommendUserVo.setTopImage(userImageService.findUserTopImageByUid(x.getUid(),x.getSex()));
            //获取详情
            reommendUserVo.setNikename(EmojiUtil.emojiConverterUnicodeStr(x.getNikename()));
            reommendUserVo.setAddress(x.getTown());
            reommendUserVo.setAge(UserDateUtil.getAgeByBirth(x.getBirthday()) + "岁");
            if (x.getBirthday()!=null){
                reommendUserVo.setConstellation(UserDateUtil.findConstellation(x.getBirthday()));
            }
            reommendUserVo.setHeight(x.getHeight());
            reommendUserVo.setWork(x.getWork());
            reommendUserVo.setSex(x.getSex());
            //字数
            TbUserContent userContentByUid = userInfoService.findUserContentByUid(x.getUid());
            String content = userContentByUid.getContent();
            reommendUserVo.setContent(content);
            reommendUserVo.setLastTime(x.getLastTime().getTime()+"");
            reommendUserVos.add(reommendUserVo);
        }


        return reommendUserVos;
    }
}
