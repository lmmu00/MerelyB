package com.merelyb.bean;

import java.util.Date;
/**
 * @项目: Merelyb
 * @包: com.merelyb.bean
 * @作者: LiM
 * @创建时间: 2018-08-01 14:26
 * @Description: ${Description}
 */
public class DataConf {

    //唯一标识
    private Long id;
    //识别码
    private String code;
    //数据库链接地址
    private String connection;
    //用户字段参数
    private String uName;
    //数据库用户名
    private String uValue;
    //密码字段参数
    private String pName;
    //数据库密码
    private String pValue;
    //数据库类型 0 Mysql
    private int dataType;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    //数据状态 0正常 1删除
    private int isDelete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection = connection;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuValue() {
        return uValue;
    }

    public void setuValue(String uValue) {
        this.uValue = uValue;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpValue() {
        return pValue;
    }

    public void setpValue(String pValue) {
        this.pValue = pValue;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }
}
