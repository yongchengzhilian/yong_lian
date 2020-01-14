package com.aidou.controller.data;

import com.aidou.controller.BaseController;
import com.aidou.dao.entity.TbZone;
import com.aidou.dao.entity.TbZoneExample;
import com.aidou.dao.mapper.TbZoneMapper;
import com.aidou.util.entity.AidouResult;
import com.aidou.util.tool.GsonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by yingjiafeng on 2019/4/30.
 */
@Api(tags = "公共数据接口")
@RequestMapping("/public")
@RestController
public class PublicDataController  extends BaseController {


    @Autowired
    private TbZoneMapper tbZoneMapper;


    @ApiOperation(value = "地址", notes = "地址")
    @RequestMapping(value = "/address",method = RequestMethod.GET)
    public AidouResult address(@RequestParam(required = false,defaultValue = "1") Long  parentId){
        System.out.println("进来了吗");
        TbZoneExample example=new TbZoneExample();
        TbZoneExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbZone> tbZones = tbZoneMapper.selectByExample(example);
        System.out.println("tbZones"+ GsonUtil.gsonString(tbZones));
        return AidouResult.success(tbZones);
    }


}
