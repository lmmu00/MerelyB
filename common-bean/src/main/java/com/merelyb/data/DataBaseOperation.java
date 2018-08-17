package com.merelyb.data;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.merelyb.bean.DataConf;
import com.merelyb.utils.database.JDBCUtils;
import com.merelyb.utils.json.JsonUtils;
import javafx.scene.control.TableFocusModel;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @项目: Merelyb
 * @包: com.merelyb.data
 * @作者: LiM
 * @创建时间: 2018-08-02 11:17
 * @Description: 数据库操作类
 */
public class DataBaseOperation<T> {

    //相关属性
    private Properties properties;
    //数据库链接
    private String sConnection;
    //数据库类型
    private int iType;

    /**
     * 初始化
     * @param dataConf
     */
    public DataBaseOperation(DataConf dataConf){
        sConnection = dataConf.getConnection();
        iType = dataConf.getDataType();
        properties = new Properties();
        properties.setProperty(dataConf.getuName(), dataConf.getuValue());
        properties.setProperty(dataConf.getpName(), dataConf.getpValue());

    }

    /**
     * 查询语句
     * @param sSQL
     * @return
     * @throws Exception
     */
    public List<T> query(String sSQL, Class<T> clsT) throws Exception {
        List<T> list = new ArrayList<T>();
        JDBCUtils jdbcUtils = new JDBCUtils(iType, sConnection, properties);
        List<JSONObject> jsonObjectList = jdbcUtils.select(sSQL);
        if(jsonObjectList.size() > 0) {
            Gson gson = new Gson();
            JsonArray jsonArray = new JsonParser().parse(jsonObjectList.toString()).getAsJsonArray();
            for (JsonElement jsonElement : jsonArray) {
                list.add(gson.fromJson(jsonElement, clsT));
            }
        }
        return list;
    }

    /**
     * 增加、更新、删除通用执行sql
     * @param sSQL
     * @return
     * @throws Exception
     */
    public int insertAndUpdateAndDelete(String sSQL) throws Exception{
        JDBCUtils jdbcUtils = new JDBCUtils(iType, sConnection, properties);
        return jdbcUtils.insertAndUpdateAndDelete(sSQL);
    }
}
