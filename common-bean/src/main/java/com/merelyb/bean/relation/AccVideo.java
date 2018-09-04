package com.merelyb.bean.relation;

import java.util.Date;

/**
 * @项目: Merelyb
 * @包: com.merelyb.bean.video
 * @作者: LiM
 * @创建时间: 2018-08-13 11:46
 * @Description: ${Description}
 */
public class AccVideo {

    //编号
    private Long id;
    //口令
    private String wordInfo;
    //图片链接
    private String imageUrl;
    //视频链接
    private String videoUrl;
    //所属用户
    private Long accId;
    //当前类型
    private Byte curType;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    //数据状态
    private Byte isDelete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWordInfo() {
        return wordInfo;
    }

    public void setWordInfo(String wordInfo) {
        this.wordInfo = wordInfo;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Long getAccId() {
        return accId;
    }

    public void setAccId(Long accId) {
        this.accId = accId;
    }

    public Byte getCurType() {
        return curType;
    }

    public void setCurType(Byte curType) {
        this.curType = curType;
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
