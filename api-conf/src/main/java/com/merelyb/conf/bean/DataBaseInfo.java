package com.merelyb.conf.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @项目: Merelyb
 * @包: com.merelyb.conf.bean
 * @作者: LiM
 * @创建时间: 2018-08-01 11:10
 * @Description: application.yml中的数据库配置
 */
@Component
@ConfigurationProperties(prefix="database")
public class DataBaseInfo {

    //数据库类型
    private int dataType;
    //链接地址
    private String connection;
    //相关参数
    private VerificationInfo verification;

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection = connection;
    }

    public VerificationInfo getVerification() {
        return verification;
    }

    public void setVerification(VerificationInfo verification) {
        this.verification = verification;
    }
}
