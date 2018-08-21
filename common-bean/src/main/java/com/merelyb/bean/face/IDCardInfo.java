package com.merelyb.bean.face;

/**
 * @项目: Merelyb
 * @包: com.merelyb.bean.face
 * @作者: LiM
 * @创建时间: 2018-08-21 22:13
 * @Description: 身份证信息
 */
public class IDCardInfo {
    //证件类型。1，代表是身份证
    private int type;
    //住址
    private String  address;
    //生日，格式为YYYY-MM-DD
    private String  birthday;
    //性别（男/女）
    private String  gender;
    //身份证号
    private String  id_card_number;
    //姓名
    private String  name;
    //民族（汉字）
    private String  race;
    //表示身份证的国徽面或人像面。返回值为：front: 人像面 back: 国徽面
    private String  side;
    //签发机关
    private String  issued_by;
    //有效日期，返回值有两种格式：
    //一个16位长度的字符串：YYYY.MM.DD-YYYY.MM.DD
    //或是：YYYY.MM.DD-长期
    private String  valid_date;
    //身份证照片的合法性检查结果 正式才有
    private IDCardType legality;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public String getId_card_number() {
        return id_card_number;
    }

    public void setId_card_number(String id_card_number) {
        this.id_card_number = id_card_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getIssued_by() {
        return issued_by;
    }

    public void setIssued_by(String issued_by) {
        this.issued_by = issued_by;
    }

    public String getValid_date() {
        return valid_date;
    }

    public void setValid_date(String valid_date) {
        this.valid_date = valid_date;
    }

    public IDCardType getLegality() {
        return legality;
    }

    public void setLegality(IDCardType legality) {
        this.legality = legality;
    }
}
