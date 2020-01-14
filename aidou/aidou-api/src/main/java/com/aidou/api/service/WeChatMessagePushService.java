package com.aidou.api.service;

import com.aidou.api.vo.request.QrCodeRequest;
import com.aidou.dao.entity.TbUser;
import com.aidou.util.entity.AidouResult;
import com.aidou.util.entity.wechat.WeChatMessageModel;
import com.aidou.util.exception.BizException;

/**
 * Created by yingjiafeng on 2019/6/23.
 */
public interface WeChatMessagePushService {

    /**
     * 获取微信AccessToken  缓存
     *
     * @return
     * @throws BizException
     */
    String getAccessToken();

    /**
     * 审核结果通知
     *
     * @throws BizException
     */
    void checkResultMessage(WeChatMessageModel weChatMessageModel) throws BizException;

    /**
     * 牵线结果通知
     * @param weChatMessageModel
     * @throws BizException
     */
    void matchmakingResultMessage(WeChatMessageModel weChatMessageModel) throws BizException;

    /**
     * 匹配失败通知
     * @param weChatMessageModel
     * @throws BizException
     */
    void matchFailedResultMessage(WeChatMessageModel weChatMessageModel) throws BizException;

    /**
     * 牵线请求通知
     * @param weChatMessageModel
     * @throws BizException
     */
    void matchmakingRequestMessage(WeChatMessageModel weChatMessageModel) throws BizException;

    /**
     * 留言回复通知
     * @param weChatMessageModel
     * @throws BizException
     */
    void leaveMessageMessage(WeChatMessageModel weChatMessageModel) throws BizException;

    /**
     * 活动报名通知
     * @param weChatMessageModel
     * @throws BizException
     */
    void activityessage(WeChatMessageModel weChatMessageModel) throws BizException;


    /**
     * 提醒发送成功通知
     * 使⽤用场景：A⽤用户邀请了了5个⽤用户赠送⼀一条红线， 或者A⽤用户邀请点好友资料料 提交审核通过
     * @param weChatMessageModel
     * @throws BizException
     */
    void reminderSentSuccessfully(WeChatMessageModel weChatMessageModel) throws BizException;



    /**
     * 生成小程序二维码
     *
     * @return
     */
    AidouResult createdQRCode(QrCodeRequest qrCodeRequest);


}
