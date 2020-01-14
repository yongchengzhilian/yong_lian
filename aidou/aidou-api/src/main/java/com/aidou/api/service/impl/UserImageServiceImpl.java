package com.aidou.api.service.impl;

import com.aidou.api.service.UserImageService;
import com.aidou.api.util.AidouImageUtil;
import com.aidou.dao.entity.TbUser;
import com.aidou.dao.entity.TbUserImg;
import com.aidou.dao.entity.TbUserImgExample;
import com.aidou.dao.mapper.TbUserImgMapper;
import com.aidou.dao.mapper.TbUserMapper;
import com.aidou.util.entity.CurrentUser;
import com.aidou.util.enums.ImageStatusEnum;
import com.aidou.util.exception.BizException;
import com.aidou.util.tool.GsonUtil;
import com.aidou.util.tool.ImageUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserImageServiceImpl implements UserImageService {

    @Autowired
    private TbUserImgMapper tbUserImgMapper;

    @Autowired
    private TbUserMapper tbUserMapper;

    @Autowired
    private ImageUtil  imageUtil;

    @Override
    public int saveImageByImg(Long uid, ImageStatusEnum imageStatusEnum, String img) throws BizException {
        Optional.ofNullable(img).orElseThrow(()->new BizException("照片不能为空"));
        //获取用户图片
       return saveImageByImgList(uid,imageStatusEnum, Arrays.asList(img));
    }

    /**
     * 根据类型查询用户相册信息
     * @param imageStatusEnum
     * @return
     */
    private TbUserImg findUserImageByType(Long  uid,ImageStatusEnum imageStatusEnum) {
        System.out.println("uid"+uid);
        TbUserImgExample userImgExample=new TbUserImgExample();
        userImgExample.createCriteria()
                .andImgTypeEqualTo(imageStatusEnum.getIndex())
                .andUidEqualTo(uid);
        List<TbUserImg> tbUserImgs = tbUserImgMapper.selectByExampleWithBLOBs(userImgExample);
        if (tbUserImgs.isEmpty()){
            return null;
        }
        return tbUserImgs.get(0);
    }

    @Override
    public int saveImageByImgList(Long  uid,ImageStatusEnum imageStatusEnum, List<String> imgList) throws BizException {
        Optional.ofNullable(imgList).orElseThrow(()->new BizException("添加相册不能为空"));
        TbUserImg  tbUserImgList=findUserImageByType(uid,imageStatusEnum);
        if (tbUserImgList==null){
            TbUserImg userImage=new TbUserImg();
            userImage.setUid(uid);
            userImage.setImg(GsonUtil.toJsonString(imgList));
            userImage.setImgType(imageStatusEnum.getIndex());
            userImage.setCreated(new Date());
            userImage.setUpdated(new Date());
            return   tbUserImgMapper.insertSelective(userImage);
        }else {
            tbUserImgList.setUpdated(new Date());
            tbUserImgList.setImg(GsonUtil.toJsonString(imgList));
            return    tbUserImgMapper.updateByPrimaryKey(tbUserImgList);
        }
    }


    /**
     * 首图
     * @param uid  用户ID
     * @param sex  根性别获取默认图  男1  女2  -1不需要
     * @return
     * @throws BizException
     */
    @Override
    public String findUserTopImageByUid(Long uid,Integer sex) throws BizException {
        TbUserImg  tbUserImgList=findUserImageByType(uid,ImageStatusEnum.IMAGE_TOP);
        if (tbUserImgList!=null) {
            String img = tbUserImgList.getImg();
            if (StringUtils.isNotEmpty(img)){
                List<String> strings = GsonUtil.gsonToList(img, String.class);
                return AidouImageUtil.getHttpUrl(strings.get(0));
            }
        }
        if (sex>0){
            return sex == 1 ? "http://cdn.aidou.online/boy.jpg" : "http://cdn.aidou.online/girl.jpg";
        }else {
            return "";
        }

    }



    @Override
    public List<String> findUserPhotoById(Long uid) throws BizException {
        List<String> photoList=new ArrayList<>();
        TbUserImg IMAGE_PHOTO = findUserImageByType(uid,ImageStatusEnum.IMAGE_PHOTO);
        if (IMAGE_PHOTO!=null){
            String photoImg = IMAGE_PHOTO.getImg();
            List<String> imageConverList = StringImageConverList(photoImg);
            photoList.addAll(imageConverList);
        }
        return photoList;
    }

    @Override
    public void deleteUserImg(Long uid) {
        TbUserImgExample example=new TbUserImgExample();
        example.createCriteria().andUidEqualTo(uid);
        tbUserImgMapper.deleteByExample(example);
    }

    @Override
    public String findUserFace() {
        TbUser tbUser = tbUserMapper.selectByPrimaryKey(CurrentUser.get().getId());
        if (tbUser==null){
            return "";
        }

        return tbUser.getAvatarurl();
    }

    @Override
    public void deleteUserImgByPhoeoName(String  imgName) throws BizException {
        System.out.println("imgName"+imgName);
        TbUserImgExample example=new TbUserImgExample();
        example.createCriteria().andUidEqualTo(CurrentUser.get().getId());
        List<TbUserImg> tbUserImgList = tbUserImgMapper.selectByExampleWithBLOBs(example);
        if (!tbUserImgList.isEmpty()){
            System.out.println("tbUserImgList"+GsonUtil.gsonString(tbUserImgList));
            List<TbUserImg> tbUserImgs = tbUserImgList.stream().filter((e) -> e.getImg().indexOf(imgName) > 0).collect(Collectors.toList());
            if (!tbUserImgs.isEmpty()){
                TbUserImg tbUserImg = tbUserImgs.get(0);
                List<String> toList = GsonUtil.gsonToList(tbUserImg.getImg(), String.class);
                if (toList.contains(imgName)){
                    toList.remove(imgName);
                    tbUserImg.setImg(GsonUtil.toJsonString(toList));
                    tbUserImg.setUpdated(new Date());
                    tbUserImgMapper.updateByPrimaryKey(tbUserImg);
                    imageUtil.delImageByHash(imgName);
                }
            }
        }
    }

    /**
     * 字符串JSON 转换成LIST
     * @param jsonPhoto
     * @return
     */
    private List<String>  StringImageConverList(String jsonPhoto){
        List<String> strings = GsonUtil.gsonToList(jsonPhoto, String.class);
        if (strings==null){
            return new ArrayList<>();
        }
        List<String> list = strings.stream().map((e) -> AidouImageUtil.getHttpUrl(e)).collect(Collectors.toList());
        return list;
    }
}
