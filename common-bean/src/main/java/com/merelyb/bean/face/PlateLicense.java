package com.merelyb.bean.face;

/**
 * @项目: Merelyb
 * @包: com.merelyb.bean.face
 * @作者: LiM
 * @创建时间: 2018-08-21 15:43
 * @Description: 车牌
 */
public class PlateLicense {
    //颜色
    private int color;
    //车牌文字
    private String license_plate_number;
    //位置
    private Rectangle bound;

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getLicense_plate_number() {
        return license_plate_number;
    }

    public void setLicense_plate_number(String license_plate_number) {
        this.license_plate_number = license_plate_number;
    }

    public Rectangle getBound() {
        return bound;
    }

    public void setBound(Rectangle bound) {
        this.bound = bound;
    }
}
