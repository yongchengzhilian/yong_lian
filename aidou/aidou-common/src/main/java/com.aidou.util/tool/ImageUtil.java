package com.aidou.util.tool;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

import java.io.InputStream;

public class ImageUtil {

    String accessKey = "eFpPQhJlo_cBPS46gAL1IJCH5y1f_OV5-G4qah1d";
    String secretKey = "vVwO3xO1AsNKY7_QKusn3G-j0tVzBxNb4AygEw9D";
    String bucket = "aidou_image";
    UploadManager uploadManager;
    BucketManager bucketManager;
    String key = null;
    Auth auth;

    public ImageUtil(){
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());
        uploadManager = new UploadManager(cfg);

         auth = Auth.create(accessKey, secretKey);
        bucketManager=new BucketManager(auth,cfg);

    }

    public  String   findQiNiuToken(){
        Auth auth = Auth.create(accessKey, secretKey);
        return  auth.uploadToken(bucket);
    }



    public DefaultPutRet imageUpload(InputStream inputStream){
        try {
            Response response = uploadManager.put(inputStream,key,auth.uploadToken(bucket),null,null);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
           return putRet;
        } catch (QiniuException ex) {
            Response r = ex.response;
           return null;
        }

    }


    /**
     * 删除图片
     */
    public boolean delImageByHash(String fileSet) {
        try {
            Response delete = bucketManager.delete(bucket, fileSet);
            return true;
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
            return false;
        }
    }






}
