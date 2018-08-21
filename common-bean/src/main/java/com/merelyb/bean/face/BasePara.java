package com.merelyb.bean.face;

import java.io.File;

/**
 * @项目: Merelyb
 * @包: com.merelyb.bean.face
 * @作者: LiM
 * @创建时间: 2018-08-21 14:14
 * @Description: ${Description}
 */
public class BasePara {
    //API Key
    private String  api_key;
    //API Secret
    private String api_secret;
    //图片的 URL
    private String image_url;
    //图片二进制文件
    private File image_file;
    //base64 编码的二进制图片数据
    private String image_base64;

    public String getApi_key() {
        return api_key;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }

    public String getApi_secret() {
        return api_secret;
    }

    public void setApi_secret(String api_secret) {
        this.api_secret = api_secret;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public File getImage_file() {
        return image_file;
    }

    public void setImage_file(File image_file) {
        this.image_file = image_file;
    }

    public String getImage_base64() {
        return image_base64;
    }

    public void setImage_base64(String image_base64) {
        this.image_base64 = image_base64;
    }
}
