package com.merelyb.bean;

/**
 * @项目: Merelyb
 * @包: com.merelyb.bean
 * @作者: LiM
 * @创建时间: 2018-08-13 16:25
 * @Description: 分页
 */
public class PageBean {
    //起始位
    private int offset;
    //个数
    private int pageSize;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
