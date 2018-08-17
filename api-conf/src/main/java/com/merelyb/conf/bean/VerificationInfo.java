package com.merelyb.conf.bean;

/**
 * @项目: Merelyb
 * @包: com.merelyb.conf.bean
 * @作者: LiM
 * @创建时间: 2018-08-01 11:12
 * @Description: 验证参数bean
 */
public class VerificationInfo {

    //用户名字段
    private String uName;
    //用户名
    private String uValue;
    //密码字段
    private String pName;
    //密码
    private String pValue;

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuValue() {
        return uValue;
    }

    public void setuValue(String uValue) {
        this.uValue = uValue;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpValue() {
        return pValue;
    }

    public void setpValue(String pValue) {
        this.pValue = pValue;
    }
}
