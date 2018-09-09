package com.merelyb.bean;

/**
 * @项目: Merelyb
 * @包: com.merelyb.bean
 * @作者: LiM
 * @创建时间: 2018-08-01 14:06
 * @Description: 返回结果bean
 */
public class ResultBean {
    //成功状态
    private boolean status;
    //接口编码
    private String code;
    //错误
    private String msg;
    //数据
    private String data;

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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
