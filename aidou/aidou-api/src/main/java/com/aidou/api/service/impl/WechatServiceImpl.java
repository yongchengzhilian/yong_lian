package com.aidou.api.service.impl;

import com.aidou.api.common.cache.RedisDao;
import com.aidou.api.common.httpclient.HttpAPIService;
import com.aidou.api.service.WeChatMessagePushService;
import com.aidou.api.service.WechatService;
import com.aidou.api.util.SignUtil;
import com.aidou.api.vo.wechat.WechatCustomerRespone;
import com.aidou.dao.entity.TbWechatAuth;
import com.aidou.dao.entity.TbWechatAuthExample;
import com.aidou.dao.mapper.TbWechatAuthMapper;
import com.aidou.util.tool.GsonUtil;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WechatServiceImpl implements WechatService {

    @Autowired
    private HttpAPIService httpAPIService;

    @Resource
    private RedisDao redisDao;

    @Resource
    private TbWechatAuthMapper  tbWechatAuthMapper;

    @Resource
    private WeChatMessagePushService weChatMessagePushService;


    private static String SEND_URL = "https://api.weixin.qq.com/cgi-bin/message/custom/send";


    @Override
    public void tokenInVerification(HttpServletRequest request, HttpServletResponse response) {
        // 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");
        if (StringUtil.isEmpty(echostr)) {
            return;
        }
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        PrintWriter out = null;
        try {
            out = response.getWriter();
            if (SignUtil.checkSignature(signature, timestamp, nonce)) {
                System.out.println("weixin get success....");
                out.print(echostr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    @Override
    public void smallAppMessageSend(WechatCustomerRespone wechatCustomerRespone) {
        if (wechatCustomerRespone == null || wechatCustomerRespone.getContent() == null) {
            return;
        }
        if (wechatCustomerRespone.getContent().equalsIgnoreCase("1")) {
            //小程序支付
            try {
                String conver_reply = redisDao.getValue("CONVER_REPLY");
                if (StringUtil.isEmpty(conver_reply) || "1".equalsIgnoreCase(conver_reply)) {
                    StringBuffer contentMsg = new StringBuffer();
                    String openId = wechatCustomerRespone.getFromUserName();
                    TbWechatAuthExample example=new TbWechatAuthExample();
                    example.createCriteria().andOpenIdEqualTo(openId);
                    List<TbWechatAuth> tbWechatAuths = tbWechatAuthMapper.selectByExample(example);
                    Long uid=-1L;
                    if (!tbWechatAuths.isEmpty()){
                        uid=tbWechatAuths.get(0).getUserId();
                    }
                    String html = "https://www.aidou.online/pay?userId="+uid;
                    String url = "☞ <a href=\"" + html + "\">点击跳转</a>";
                    contentMsg.append("↓↓您的专属充值链接↓↓").append("\n");
                    contentMsg.append(url);
                    sendCustomerTextMessage(wechatCustomerRespone.getFromUserName(), contentMsg.toString(), weChatMessagePushService.getAccessToken());
                } else {
                    sendCustomerImageMessage(wechatCustomerRespone.getFromUserName(), conver_reply, weChatMessagePushService.getAccessToken());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    /**
     * 文本事件
     */
    public void sendCustomerTextMessage(String openid, String text, String accessToken) throws Exception {
        Map<String, Object> map_content = new HashMap<>();
        map_content.put("content", text);
        Map<String, Object> map = new HashMap<>();
        map.put("touser", openid);
        map.put("msgtype", "text");
        map.put("text", map_content);
        StringBuffer stringBuffer = new StringBuffer(SEND_URL);
        stringBuffer.append("?access_token=" + accessToken);
        httpAPIService.doPost(stringBuffer.toString(), GsonUtil.toJsonString(map));
    }


    /***
     * 发送的图片消息
     */
    public void sendCustomerImageMessage(String openid, String mediaId, String accessToken) throws Exception {
        Map<String, Object> map_content = new HashMap<>();
        map_content.put("media_id", mediaId);
        Map<String, Object> map = new HashMap<>();
        map.put("touser", openid);
        map.put("msgtype", "image");
        map.put("image", map_content);
        httpAPIService.doPost(SEND_URL + "?access_token=" + accessToken, GsonUtil.toJsonString(map));
    }


}
