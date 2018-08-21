package com.merelyb.bean.face;

import java.util.List;

/**
 * @项目: Merelyb
 * @包: com.merelyb.bean.face
 * @作者: LiM
 * @创建时间: 2018-08-21 15:49
 * @Description: 车牌返回类型
 */
public class PlateLicenseResult extends BaseResult {
    //返回结果
    private List<PlateLicense> results;

    public List<PlateLicense> getResults() {
        return results;
    }

    public void setResults(List<PlateLicense> results) {
        this.results = results;
    }
}
