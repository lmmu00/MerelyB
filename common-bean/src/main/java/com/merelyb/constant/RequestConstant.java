package com.merelyb.constant;

import org.omg.CORBA.PUBLIC_MEMBER;

/**
 * @项目: Merelyb
 * @包: com.merelyb.constant
 * @作者: LiM
 * @创建时间: 2018-07-31 14:48
 * @Description: 请求参数常量
 */
public class RequestConstant {
    //api-conf 模块 getNeedData 的 Code 参数名称
    public final static String APICONF_GETNEEDDATA_CODE = "code";


    public final static String ACCOUNT_ADDACC_USER = "acc";
    public final static String ACCOUNT_ADDACC_PWD = "pwd";

    public final static String REQUEST_TOKEN = "sstk";
    public final static String USERINFO_NAME = "uName";
    public final static String USERINFO_ID ="uId";
    public final static String USERINFO_ACCESS = "uAccess";

    public final static String REQUEST_REMARK = "remark";
    public final static String REQUEST_VIDEOURL = "videoUrl";
    public final static String REQUEST_IMAGEURL = "imageUrl";
    public final static String REQUEST_CURTYPE = "curType";
    public final static String REQUEST_CURID = "curId";

    public final static int ITOKENVAILD = 60 * 60 *2;
}
