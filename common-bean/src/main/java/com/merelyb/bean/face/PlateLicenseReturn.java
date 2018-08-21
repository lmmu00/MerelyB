package com.merelyb.bean.face;

/**
 * @项目: Merelyb
 * @包: com.merelyb.bean.face
 * @作者: LiM
 * @创建时间: 2018-08-21 17:55
 * @Description: ${Description}
 */
public class PlateLicenseReturn {
    //颜色
    private int plateLicenseColor;
    //车牌文字
    private String plateLicenseNum;
    //位置
    private String plateLicenseLoc;

    public int getPlateLicenseColor() {
        return plateLicenseColor;
    }

    public void setPlateLicenseColor(int plateLicenseColor) {
        this.plateLicenseColor = plateLicenseColor;
    }

    public String getPlateLicenseNum() {
        return plateLicenseNum;
    }

    public void setPlateLicenseNum(String plateLicenseNum) {
        this.plateLicenseNum = plateLicenseNum;
    }

    public String getPlateLicenseLoc() {
        return plateLicenseLoc;
    }

    public void setPlateLicenseLoc(String plateLicenseLoc) {
        this.plateLicenseLoc = plateLicenseLoc;
    }
}
