package com.aidou.api.service;

import com.aidou.util.enums.ImageStatusEnum;
import com.aidou.util.exception.BizException;

import java.util.List;

public interface UserImageService {

    /**
     * 为当前用户添加一张图片
     *
     * @param uid
     * @param imageStatusEnum  图片枚举
     * @param img  图片路径
     * @return
     * @throws BizException
     */
    int  saveImageByImg(Long uid, ImageStatusEnum imageStatusEnum, String img) throws BizException;


    /**
     * 为当前用户添加图片集合
     * @param imageStatusEnum  图片枚举
     * @param img  图片集合
     * @return
     * @throws BizException
     */
    int  saveImageByImgList(Long  uid,ImageStatusEnum imageStatusEnum, List<String> img) throws BizException;





    /**
     * 获取用户封面图
     * @param uid
     * @return
     * @throws NullPointerException
     * @throws Exception
     */
    String  findUserTopImageByUid(Long uid,Integer sex) throws BizException;




    /**
     *获取用户相册
     * @param uid
     * @return
     * @throws Exception
     */
    List<String>    findUserPhotoById(Long uid) throws BizException;


    /**
     * 删除用户照片
     * @param uid
     */
    void deleteUserImg(Long uid);

    /**
     * 获取用户头像
     * @return
     */
    String findUserFace();

    /**
     * 根据照片名称删除照片
     * @throws BizException
     */
    void deleteUserImgByPhoeoName(String  imgName) throws BizException;

}
