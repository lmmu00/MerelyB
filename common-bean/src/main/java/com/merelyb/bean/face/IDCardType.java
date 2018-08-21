package com.merelyb.bean.face;

/**
 * @项目: Merelyb
 * @包: com.merelyb.bean.face
 * @作者: LiM
 * @创建时间: 2018-08-21 22:06
 * @Description: 证件类型
 * 身份证照片的合法性检查结果。返回结果为身份证五种分类的概率值（取［0，1］区间实数，小数点后3位有效数字，概率值总和等于1.0）
 */
public class IDCardType {
    //正式身份证照片
    private double ID_Photo;
    //用工具合成或者编辑过的身份证图片
    private double Edited;
    //正式身份证的复印件
    private double Photocopy;
    //手机或电脑屏幕翻拍的照片
    private double Screen;
    //临时身份证照片
    private double Temporary_ID_Photo;

    public double getID_Photo() {
        return ID_Photo;
    }

    public void setID_Photo(double ID_Photo) {
        this.ID_Photo = ID_Photo;
    }

    public double getEdited() {
        return Edited;
    }

    public void setEdited(double edited) {
        Edited = edited;
    }

    public double getPhotocopy() {
        return Photocopy;
    }

    public void setPhotocopy(double photocopy) {
        Photocopy = photocopy;
    }

    public double getScreen() {
        return Screen;
    }

    public void setScreen(double screen) {
        Screen = screen;
    }

    public double getTemporary_ID_Photo() {
        return Temporary_ID_Photo;
    }

    public void setTemporary_ID_Photo(double temporary_ID_Photo) {
        Temporary_ID_Photo = temporary_ID_Photo;
    }
}
