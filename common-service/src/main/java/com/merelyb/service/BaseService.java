package com.merelyb.service;

import com.google.gson.reflect.TypeToken;
import com.merelyb.bean.dataConf.DataConf;
import com.merelyb.bean.PageBean;
import com.merelyb.bean.ResultBean;
import com.merelyb.constant.UrlContant;
import com.merelyb.utils.httpclient.HttpClientResult;
import com.merelyb.utils.httpclient.HttpClientUtils;
import com.merelyb.utils.json.JsonUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import java.util.List;

/**
 * @项目: Merelyb
 * @包: com.merelyb.data.acc
 * @作者: LiM
 * @创建时间: 2018-08-06 14:56
 * @Description: ${Description}
 */
public class BaseService<T> {

    private Logger logger = LogManager.getLogger(this.getClass());

    /**
     * 初始化service
     * @param sCode
     * @throws Exception
     */
    protected DataConf initService(String sCode) throws Exception{
        DataConf dataConf = null;
        String sUrl = UrlContant.DATACONFURL + sCode;
        logger.info(sUrl);
        HttpClientUtils httpClientUtils = new HttpClientUtils();
        HttpClientResult httpClientResult = httpClientUtils.httpPostRequest(sUrl);
        logger.info(JsonUtils.obj2Json(httpClientResult));
        if(httpClientResult.isSuccessed()){
            ResultBean resultBean = JsonUtils.json2Obj(httpClientResult.getData(), ResultBean.class);
            if(resultBean.getStatus() == true){
                String sData = (String)resultBean.getData();
                List<DataConf> dataConfList = JsonUtils.json2Objs(sData, new TypeToken<List<DataConf>>(){});
                if(dataConfList.size() > 0){
                    dataConf = (DataConf) dataConfList.get(0);
                }
            }
        }
        return dataConf;
    }

    /**
     * 检测bean是否为空
     * @param obj
     * @return
     */
    public boolean checkNull(T obj){
        boolean bReturn = true;
        String strTmp = JsonUtils.obj2Json(obj);
        JSONObject jsonObject = new JSONObject(strTmp);
        while (jsonObject.keys().hasNext()){
            String sKey = jsonObject.keys().next();
            if(!jsonObject.isNull(sKey)) {
                bReturn = false;
               break;
            }
        }
        return bReturn;
    }

    /**
     * 新增
     * @param obj
     * @return
     * @throws Exception
     */
    public int insert(T obj) throws Exception{
        if(checkNull(obj)) return  -1;
        return 0;
    }

    /**
     * 查询
     * @param obj
     * @return
     * @throws Exception
     */
    public List<T> select(T obj) throws Exception{
        return null;
    }

    /**
     * 分页查询数据
     * @param obj
     * @param pageBean
     * @return
     * @throws Exception
     */
    public List<T> selectByPage(T obj, PageBean pageBean) throws Exception{
        return null;
    }

    /**
     * 逻辑删除
     * @param obj
     * @return
     * @throws Exception
     */
    public int delete(T obj) throws Exception{
        if(checkNull(obj)) return  -1;
        return 0;
    }

    /**
     * 根据编号更新数据
     * @param obj
     * @return
     * @throws Exception
     */
    public int updateById(T obj) throws Exception{
        if(checkNull(obj)) return  -1;
        return 0;
    }

    /**
     * 根据条件更新数据
     * @param obj
     * @param obj2
     * @return
     * @throws Exception
     */
    public int updateByBean(T obj, T obj2) throws Exception{
        if(checkNull(obj) || checkNull(obj2)) return  -1;
        return 0;
    }
}
