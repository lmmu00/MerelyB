package com.merelyb.bean.centre;

import java.util.List;

/**
 * @项目: Merelyb
 * @包: com.merelyb.bean.centre
 * @作者: LiM
 * @创建时间: 2018-09-10 21:30
 * @Description: 中间redis
 */
public class UserInfo {
    //用户名
    private String UserName;
    //用户编号
    private String UserId;
    //访问
    private List<String> Access;

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public List<String> getAccess() {
        return Access;
    }

    public void setAccess(List<String> access) {
        Access = access;
    }
}
