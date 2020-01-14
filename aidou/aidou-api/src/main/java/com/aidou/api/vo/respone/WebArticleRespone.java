package com.aidou.api.vo.respone;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author yingjiafeng
 * @version 创建时间：2019/9/20 11:49
 */
@Data
public class WebArticleRespone  implements Serializable {

    private List<EntriesBean> entries;


        public static class EntriesBean {
            /**
             * img : https://wx-love-img.afunapp.com/FoqRC4IvvBdM9Eg0ilwsjnxqxd5x
             * info : 「青藤情侣」李一昂和杨玉是华科校友，也是华为公司的同事，因为青藤之恋，原来的陌生人成了甜蜜的情侣。
             * title : 青藤之恋脱单故事
             * url : https://mp.weixin.qq.com/s/nvazMdjkDOsvMzYAUyfYsA
             */

            private String img;
            private String info;
            private String title;
            private String url;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getInfo() {
                return info;
            }

            public void setInfo(String info) {
                this.info = info;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
}
