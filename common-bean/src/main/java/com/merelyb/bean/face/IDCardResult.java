package com.merelyb.bean.face;

import java.util.List;

/**
 * @项目: Merelyb
 * @包: com.merelyb.bean.face
 * @作者: LiM
 * @创建时间: 2018-08-21 22:21
 * @Description: 身份证返回数据类型
 */
public class IDCardResult extends BaseResult {
    //身份证信息
    private List<IDCardInfo> cards;

    public List<IDCardInfo> getCards() {
        return cards;
    }

    public void setCards(List<IDCardInfo> cards) {
        this.cards = cards;
    }
}
