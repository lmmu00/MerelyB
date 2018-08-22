package com.merelyb.bean.face;

import java.util.List;

/**
 * @项目: Merelyb
 * @包: com.merelyb.bean.face
 * @作者: LiM
 * @创建时间: 2018-08-22 15:58
 * @Description: ${Description}
 */
public class BankCardResult extends BaseResult {

    private List<BankCard> bank_cards;

    public List<BankCard> getBank_cards() {
        return bank_cards;
    }

    public void setBank_cards(List<BankCard> bank_cards) {
        this.bank_cards = bank_cards;
    }
}
