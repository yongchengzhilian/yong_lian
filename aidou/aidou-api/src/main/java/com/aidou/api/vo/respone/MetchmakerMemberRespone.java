package com.aidou.api.vo.respone;

import com.aidou.api.vo.user.ReommendUserVo;
import lombok.Data;

import java.util.List;

/**
 * @author yingjiafeng
 * @version 创建时间：2019/7/9 9:29
 */
@Data
public class MetchmakerMemberRespone  {

    private String  area;

      //会员数量
    private   Integer  vipNum;
     //已牵线会员
    private Integer  matchLineVipNum;
     //单身会员数量
    private Integer  singleVipNum;

    private String name;

    private String  face;

   //申请匹配用户
//    private   Integer  applicationNum;

//    /**
//     * 会员列表 时间倒序
//     */
//    private List<ReommendUserVo> userVoList;


}
