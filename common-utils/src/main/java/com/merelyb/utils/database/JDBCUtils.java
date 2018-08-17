package com.merelyb.utils.database;

import org.json.JSONObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @项目: Merelyb
 * @包: com.merelyb.utils.database
 * @作者: LiM
 * @创建时间: 2018-07-27 16:17
 * @Description: JDBC数据库类
 */
public class JDBCUtils {
    //驱动
    private Driver driver;
    //数据库连接
    private Connection connection;

    /**
     * 初始化mysql驱动
     *
     * @throws SQLException
     */
    private void initMysql() throws SQLException {
        driver = new com.mysql.cj.jdbc.Driver();
    }

    public JDBCUtils(int iType, String sConn, Properties prop) throws SQLException {
        switch (iType){
            case 0:{
                initMysql();
                break;
            }
        }
        connection = driver.connect(sConn, prop);
    }

    /**
     * 新增数据
     * @param sSQL
     * @return
     * @throws SQLException
     */
    public int insertAndUpdateAndDelete(String sSQL) throws SQLException {
        PreparedStatement preparedStatement;
        preparedStatement = (PreparedStatement) connection.prepareStatement(sSQL);
        int i = preparedStatement.executeUpdate();
        preparedStatement.close();
        destroyConnection();
        return i;
    }

    /**
     * 查询返回json格式数据
     * @param sSQL
     * @return
     * @throws SQLException
     */
    public List<JSONObject> select(String sSQL) throws SQLException{
        PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sSQL);
        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        List<JSONObject> jsonObjectList = new ArrayList<JSONObject>();
        while (resultSet.next()){
            JSONObject jsonObject = new JSONObject();
            for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                jsonObject.put(resultSetMetaData.getColumnName(i), resultSet.getObject(i));
            }
            jsonObjectList.add(jsonObject);
        }
        return jsonObjectList;
    }

    /**
     * 连接释放
     *
     * @throws SQLException
     */
    private void destroyConnection() throws SQLException {
        connection.close();
    }
}
