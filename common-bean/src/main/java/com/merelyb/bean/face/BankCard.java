package com.merelyb.bean.face;

import java.util.List;

/**
 * @项目: Merelyb
 * @包: com.merelyb.bean.face
 * @作者: LiM
 * @创建时间: 2018-08-22 15:45
 * @Description: 银行卡信息
 */
public class BankCard {
    //银行卡坐标
    private Rectangle bound;
    //银行卡号
    private String number;
    //银行
    private String bank;
    //支持的金融组织服务
    private List<String> organization;

    public Rectangle getBound() {
        return bound;
    }

    public void setBound(Rectangle bound) {
        this.bound = bound;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public List<String> getOrganization() {
        return organization;
    }

    public void setOrganization(List<String> organization) {
        this.organization = organization;
    }
}
