package com.merelyb.bean.face;

import java.util.List;

/**
 * @项目: Merelyb
 * @包: com.merelyb.bean.face
 * @作者: LiM
 * @创建时间: 2018-08-22 13:58
 * @Description: ${Description}
 */
public class DriverLicenseResult extends BaseResult {

    private List<DriverLicenseInfo> cards;

    public List<DriverLicenseInfo> getCards() {
        return cards;
    }

    public void setCards(List<DriverLicenseInfo> cards) {
        this.cards = cards;
    }
}
