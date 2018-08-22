package com.merelyb.bean.face;

/**
 * @项目: Merelyb
 * @包: com.merelyb.bean.face
 * @作者: LiM
 * @创建时间: 2018-08-22 13:46
 * @Description: 驾照基本信息
 */
public class DriverLicenseInfo {
    //证件类型。
    //返回 2，表示是驾驶证。
    private int type;
    //驾驶证版本.
    //返回 2，表示是2013版本驾驶证。
    //返回 1，表示是2008或更早版本驾驶证。
    private int version;
    //住址
    private String address;
    //生日，格式为YYYY-MM-DD
    private String birthday;
    //性别（男/女
    private String gender;
    //驾驶证号
    private String license_number;
    //姓名
    private String name;
    //准驾车型
    private String carType;
    //表示驾驶证的正面或者反面。该字段目前只会返回“front”，表示是正面
    private String side;
    //国籍
    private String nationality;
    //签发机关
    private String issued_by;
    //初次领证日期，格式为YYYY-MM-DD
    private String issue_date;
    //有效日期，格式为YYYY-MM-DD
    private String valid_from;
    //有效年限，例如 6年
    private String valid_for;
    //有效期限格式为：YYYY-MM-DD至YYYY-MM-DD
    //根据驾驶证版本不同，一种情况会返回valid_from和valid_for两个字段，另一种情况只返回valid_date字段。
    private String valid_date;


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLicense_number() {
        return license_number;
    }

    public void setLicense_number(String license_number) {
        this.license_number = license_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getIssued_by() {
        return issued_by;
    }

    public void setIssued_by(String issued_by) {
        this.issued_by = issued_by;
    }

    public String getIssue_date() {
        return issue_date;
    }

    public void setIssue_date(String issue_date) {
        this.issue_date = issue_date;
    }

    public String getValid_from() {
        return valid_from;
    }

    public void setValid_from(String valid_from) {
        this.valid_from = valid_from;
    }

    public String getValid_for() {
        return valid_for;
    }

    public void setValid_for(String valid_for) {
        this.valid_for = valid_for;
    }

    public String getValid_date() {
        return valid_date;
    }

    public void setValid_date(String valid_date) {
        this.valid_date = valid_date;
    }
}
