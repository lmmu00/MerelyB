package com.merelyb.utils.httpclient;

/**
 * @项目: Merelyb
 * @包: com.merelyb.utils.httpclient
 * @作者: LiM
 * @创建时间: 2018-07-27 10:55
 * @Description: ${Description}
 */
public class HttpClientResult {

    private boolean isSuccessed=false;  //请求是否正常

    private Integer code; //http请求返回的错误码

    private String msg; //封装的错误信息

    private String data; //返回的值

    public boolean isSuccessed() {
        return isSuccessed;
    }

    public void setSuccessed(boolean successed) {
        isSuccessed = successed;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
