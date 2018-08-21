package com.merelyb.bean.face;

/**
 * @项目: Merelyb
 * @包: com.merelyb.bean.face
 * @作者: LiM
 * @创建时间: 2018-08-21 15:46
 * @Description: 返回基础类型
 */
public class BaseResult {
    //用于区分每一次请求的唯一的字符串
    private String request_id;
    //被检测的图片在系统中的标识
    private String image_id;
    //错误信息
    private String error_message;
    //整个请求所花费的时间，单位为毫秒
    private int time_used;

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }

    public int getTime_used() {
        return time_used;
    }

    public void setTime_used(int time_used) {
        this.time_used = time_used;
    }
}
