package com.merelyb.video.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @项目: Merelyb
 * @包: com.merelyb.video.bean
 * @作者: LiM
 * @创建时间: 2018-09-03 17:04
 * @Description: ${Description}
 */
@Component
@ConfigurationProperties(prefix="upload")
public class FilePath {
    //windows
    private String win;
    //centos
    private String liu;

    private String cur;

    public String getWin() {
        return win;
    }

    public void setWin(String win) {
        this.win = win;
    }

    public String getLiu() {
        return liu;
    }

    public void setLiu(String liu) {
        this.liu = liu;
    }

    public String getCur() {
        return cur;
    }

    public void setCur(String cur) {
        this.cur = cur;
    }
}
