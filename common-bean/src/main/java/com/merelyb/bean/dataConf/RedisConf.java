package com.merelyb.bean.dataConf;

import java.util.Date;

/**
 * @项目: Merelyb
 * @包: com.merelyb.bean.dataConf
 * @作者: LiM
 * @创建时间: 2018-09-04 15:53
 * @Description: ${Description}
 */
public class RedisConf {
    //编号
    private Long id;
    //serverIP地址
    private String addIP;
    //端口
    private Long port;
    //验证口令
    private String authPwd;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    //数据状态 0正常 1删除
    private Byte isDelete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddIP() {
        return addIP;
    }

    public void setAddIP(String addIP) {
        this.addIP = addIP;
    }

    public Long getPort() {
        return port;
    }

    public void setPort(Long port) {
        this.port = port;
    }

    public String getAuthPwd() {
        return authPwd;
    }

    public void setAuthPwd(String authPwd) {
        this.authPwd = authPwd;
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

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }
}
