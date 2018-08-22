package com.merelyb.bean.face;

import java.util.Date;

/**
 * @项目: Merelyb
 * @包: com.merelyb.bean.face
 * @作者: LiM
 * @创建时间: 2018-08-22 14:15
 * @Description: 返回驾照信息
 */
public class DriverLicenseReturn {
    //姓名
    private String name;
    //驾驶证版本.
    //返回 2，表示是2013版本驾驶证。
    //返回 1，表示是2008或更早版本驾驶证。
    private int version;
    //住址
    private String address;
    //生日，格式为YYYY-MM-DD
    private Date birthday;
    //性别（男0/女1)
    private int sex;
    //驾驶证号
    private String dlNum;
    //准驾车型
    private String carType;
    //国籍
    private String country;
    //签发机关
    private String office;
    //初次领证日期，格式为YYYY-MM-DD
    private Date firstCheck;
    //有效日期，格式为YYYY-MM-DD
    private Date effectiveStart;
    //有效年限，例如 6年
    private String effectiveNum;
    //有效期限格式为：YYYY-MM-DD至YYYY-MM-DD
    //根据驾驶证版本不同，一种情况会返回valid_from和valid_for两个字段，另一种情况只返回valid_date字段。
    private String effectiveTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getDlNum() {
        return dlNum;
    }

    public void setDlNum(String dlNum) {
        this.dlNum = dlNum;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public Date getFirstCheck() {
        return firstCheck;
    }

    public void setFirstCheck(Date firstCheck) {
        this.firstCheck = firstCheck;
    }

    public Date getEffectiveStart() {
        return effectiveStart;
    }

    public void setEffectiveStart(Date effectiveStart) {
        this.effectiveStart = effectiveStart;
    }

    public String getEffectiveNum() {
        return effectiveNum;
    }

    public void setEffectiveNum(String effectiveNum) {
        this.effectiveNum = effectiveNum;
    }

    public String getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(String effectiveTime) {
        this.effectiveTime = effectiveTime;
    }
}
