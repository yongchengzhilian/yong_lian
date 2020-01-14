package com.aidou.service.impl;

import com.aidou.dao.entity.*;
import com.aidou.dao.mapper.TbUserContentMapper;
import com.aidou.dao.mapper.TbUserImgMapper;
import com.aidou.dao.mapper.TbUserMapper;
import com.aidou.enums.ImageTypeEnum;
import com.aidou.enums.RmStatusEnum;
import com.aidou.enums.UserStatusEnum;
import com.aidou.service.MatchmakerService;
import com.aidou.util.entity.AidouResult;
import com.aidou.util.entity.CurrentUser;
import com.aidou.util.tool.GsonUtil;
import com.aidou.util.tool.IdWorker;
import com.aidou.util.tool.UserDateUtil;
import com.aidou.vo.rm.RmUserVo;
import com.aidou.vo.rm.UserInfoEditDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created by yingjiafeng on 2019/4/30.
 */
@Service
public class MatchmakerServiceImpl implements MatchmakerService {

    @Autowired
    private TbUserMapper tbUserMapper;

    @Autowired
    private TbUserImgMapper tbUserImgMapper;

    @Autowired
    private TbUserContentMapper tbUserContentMapper;

    @Autowired
    private IdWorker idWorker;

    @Value("${IMAGE_SERVER_URL}")
    private String IMAGE_SERVER_URL;






    @Transactional
    @Override
    public AidouResult AddUser(UserInfoEditDto userInfoEditDto) throws Exception {
        Long id;

        if (CurrentUser.get().getStatus()== RmStatusEnum.FULL_DATA.getIndex()){
            return  AidouResult.error("请设置个人资料");
        }

        if (userInfoEditDto.getUid()!=null && userInfoEditDto.getUid()>0){
            id=userInfoEditDto.getUid();
            tbUserMapper.deleteByPrimaryKey(id);
            TbUserImgExample key=new TbUserImgExample();
            key.createCriteria().andUidEqualTo(id);
            tbUserImgMapper.deleteByExample(key);
            tbUserContentMapper.deleteByPrimaryKey(id);
        }else {
            id=idWorker.nextId();
        }

        //添加用户
        //头像
//        userInfoEditDto.setAvatarurl(userInfoEditDto.getAvatarurl().replace(IMAGE_SERVER_URL, ""));
        TbUser user = new TbUser();
        BeanUtils.copyProperties(userInfoEditDto, user);
        //昵称
        user.setNikename(userInfoEditDto.getSex() == 1 ? "G" + getNikeName() : "M" + getNikeName());
        user.setUid(id);
        //已认证
        user.setStatus(UserStatusEnum.SUCCESS_CERTIFICATION.getIndex());
        user.setMid(CurrentUser.get().getId());
        //设置微信
        if (StringUtils.isEmpty(user.getWechat())){
            user.setWechat(CurrentUser.get().getWechat());
        }
        //手机号
        user.setMobile(CurrentUser.get().getMobile());
        //设置现住
        user.setTown(userInfoEditDto.getProvince() + "-" + userInfoEditDto.getCity());
        user.setCreated(new Date());
        user.setUpdated(new Date());
        tbUserMapper.insertSelective(user);
        //相册
        List<String> photo = userInfoEditDto.getPhoto();
        if (photo != null || !photo.isEmpty()) {
            TbUserImg userImg = new TbUserImg();
            userImg.setUid(user.getUid());
            userImg.setImgType(ImageTypeEnum.PHOTO.getName());
            userImg.setImg(GsonUtil.gsonString(photo));
            userImg.setIsLook(true);
            userImg.setCreated(new Date());
            userImg.setUpdated(new Date());
            tbUserImgMapper.insertSelective(userImg);
        }
        //自我介绍
        TbUserContent userContent = new TbUserContent();
        userContent.setUid(user.getUid());
        userContent.setContent(userInfoEditDto.getContent());
        userContent.setFavoriteTa(userInfoEditDto.getFavoriteTa());
        userContent.setInterest(userInfoEditDto.getInterest());
        userContent.setCreated(new Date());
        userContent.setUpdated(new Date());
        tbUserContentMapper.insertSelective(userContent);
        return AidouResult.success();
    }

    private Integer getNikeName() {
       return new Random().nextInt(1000000000);
    }

    @Override
    public AidouResult findUserList() {
        List<RmUserVo>  userVoList=new ArrayList<>();
          //获取红娘添加的用户
        TbUserExample example=new TbUserExample();
        example.createCriteria().andMidEqualTo(CurrentUser.get().getId());
        List<TbUser> tbUsers = tbUserMapper.selectByExample(example);
        if (!tbUsers.isEmpty()){
            tbUsers.forEach((x)->{
                RmUserVo rmUserVo=new RmUserVo();
                BeanUtils.copyProperties(x,rmUserVo);
                rmUserVo.setAge(UserDateUtil.getAgeByBirth(x.getBirthday()));
                userVoList.add(rmUserVo);
            });
        }
        return AidouResult.success(userVoList);
    }

    @Override
    public AidouResult stopUser(Long uid, Integer type) {
        TbUser user=new TbUser();
        user.setUid(uid);
        user.setStatus(type==0?UserStatusEnum.STOP.getIndex():UserStatusEnum.SUCCESS_CERTIFICATION.getIndex());
        user.setCreated(new Date());
        tbUserMapper.updateByPrimaryKeySelective(user);
        return AidouResult.success();
    }

    @Override
    public AidouResult findUserDescriptById(Long id)  throws NullPointerException{
        //获取用户详情
        Optional.ofNullable(id).orElseThrow(NullPointerException::new);
        UserInfoEditDto userInfoEditDto=new UserInfoEditDto();

        TbUser tbUser = tbUserMapper.selectByPrimaryKey(id);
        if (tbUser==null){
            return AidouResult.error("用户不存在");
        }
        BeanUtils.copyProperties(tbUser,userInfoEditDto);
        String town = tbUser.getTown();
        if (!StringUtils.isEmpty(town)){
            String[] split = town.split("-");
            userInfoEditDto.setProvince(split[0]);
            userInfoEditDto.setCity(split[1]);
        }

        //获取相册
        TbUserContent tbUserContent = tbUserContentMapper.selectByPrimaryKey(id);
        if (tbUserContent!=null){
            BeanUtils.copyProperties(tbUserContent,userInfoEditDto);
        }
        //获取自我介绍
        TbUserImgExample example=new TbUserImgExample();
        example.createCriteria().andUidEqualTo(id);
        List<TbUserImg> tbUserImgs = tbUserImgMapper.selectByExample(example);
        if (!tbUserImgs.isEmpty()){
            //获取相册
            TbUserImg tbUserImg = tbUserImgs.get(0);
            String img = tbUserImg.getImg();
            //转换list
            List<String> photoList= GsonUtil.gsonToList(img,String.class);
            userInfoEditDto.setPhoto(photoList);
        }
        return AidouResult.success(userInfoEditDto);
    }


}
