package com.merelyb.bean.acc;

import java.util.Date;

/**
 * @项目: Merelyb
 * @包: com.merelyb.bean
 * @作者: LiM
 * @创建时间: 2018-08-03 17:54
 * @Description: 账户信息bean
 */
public class AccInfo {
    //唯一标识
    private Long accId;
    //用户名
    private String accUser;
    //密码
    private String accPwd;
    //用户状态
    private Byte accStatus;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    //数据状态
    private Byte isDelete;

    public Long getAccId() {
        return accId;
    }

    public void setAccId(Long accId) {
        this.accId = accId;
    }

    public String getAccUser() {
        return accUser;
    }

    public void setAccUser(String accUser) {
        this.accUser = accUser;
    }

    public String getAccPwd() {
        return accPwd;
    }

    public void setAccPwd(String accPwd) {
        this.accPwd = accPwd;
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

    public Byte getAccStatus() {
        return accStatus;
    }

    public void setAccStatus(Byte accStatus) {
        this.accStatus = accStatus;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }
}
