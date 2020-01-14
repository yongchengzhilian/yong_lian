package com.aidou.api.service.impl;

import com.aidou.api.service.WebArticleService;
import com.aidou.api.vo.respone.WebArticleRespone;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yingjiafeng
 * @version 创建时间：2019/9/20 11:52
 */
@Service
public class ArticleServiceImpl  implements WebArticleService {

    @Override
    public WebArticleRespone findWebArticleList() {
        WebArticleRespone  webArticleRespone=new WebArticleRespone();
        List<WebArticleRespone.EntriesBean> entriesList=new ArrayList<>();
        WebArticleRespone.EntriesBean bean1=new WebArticleRespone.EntriesBean();
        bean1.setImg("http://i.caigoubao.cc/625976/640.png");
        bean1.setInfo("「红豆良缘」据说全世界两个人相遇的几率是0.48%。而相爱的几率更小，0.0049%。何缩小两个小数点的距离?");
        bean1.setTitle("99天微信网恋：我只相信你");
        bean1.setUrl("https://mp.weixin.qq.com/s/PJC5cWq7_sFFBJMFlycXHg");
        entriesList.add(bean1);

        WebArticleRespone.EntriesBean bean2=new WebArticleRespone.EntriesBean();
        bean2.setImg("http://i.caigoubao.cc/625976/640_132136910491145000.png");
        bean2.setInfo("「红豆良缘」我们认为整个追女生的一个过程其实就是在推销自己，把自己当成一个商品的过程，那么展示价值 就相当于在推销商品的时候 这商品有多好！");
        bean2.setTitle("加微信之后如何展示自己的价值");
        bean2.setUrl("https://mp.weixin.qq.com/s/Meyz1rLC8GKujAqoAz5jEA");
        entriesList.add(bean2);


        WebArticleRespone.EntriesBean bean3=new WebArticleRespone.EntriesBean();
        bean3.setImg("http://i.caigoubao.cc/625976/640_132136910491145000.png");
        bean3.setInfo("「红豆良缘」今天聊一个很细的问题，细的就像个小鱼刺，卡的很多小伙伴很不舒服，让我们来一起拔掉它。——从平台上看到对方的微信加了，怎么开始第一轮聊天的问题。");
        bean3.setTitle("彼此点亮后微信加了，怎么开始聊天？");
        bean3.setUrl("https://mp.weixin.qq.com/s/dytc5TF_gRG6Galo4kAsKA");
        entriesList.add(bean3);

        WebArticleRespone.EntriesBean bean4=new WebArticleRespone.EntriesBean();
        bean4.setImg("http://cdn.aidou.online/FvtbT-2PNXWgRNPIwk0Z11IuvsVB");
        bean4.setInfo("「红豆良缘」如何在红豆良缘寻找属于自己的幸福？");
        bean4.setTitle("红豆良缘小程序操作指南");
        bean4.setUrl("https://mp.weixin.qq.com/s/IE8pukypCTcM388THsybJg");
        entriesList.add(bean4);
        webArticleRespone.setEntries(entriesList);
        return webArticleRespone;
    }
}
