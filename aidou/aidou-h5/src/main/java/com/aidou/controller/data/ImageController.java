package com.aidou.controller.data;

import com.aidou.controller.BaseController;
import com.aidou.util.entity.AidouResult;
import com.aidou.util.tool.ExceptionUtil;
import com.aidou.util.tool.ImageUtil;
import com.qiniu.storage.model.DefaultPutRet;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@Api(tags = "图片上传")
@RequestMapping("/pic")
@RestController
public class ImageController extends BaseController {

    @Value("${IMAGE_SERVER_URL}")
    private String IMAGE_SERVER_URL;


    @Autowired
    private ImageUtil imageUtil;


    @ApiOperation(value = "图片上传", notes = "图片上传")
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public AidouResult uploadFile(@RequestParam("file") MultipartFile uploadFile){
        //取文件扩展名
        if (uploadFile.isEmpty()|| uploadFile.getSize()==0){
            return AidouResult.error("图片为空");
        }
        try {
            //添加水印
            DefaultPutRet putRet = imageUtil.imageUpload(uploadFile.getInputStream());
            if (putRet==null){
                return AidouResult.error("图片上传失败");
            }
         return  AidouResult.success(IMAGE_SERVER_URL+   putRet.key);
        }catch (Exception e){
            e.printStackTrace();
            return  AidouResult.error(ExceptionUtil.getExceptionAllinformation_01(e));
        }
    }





}
