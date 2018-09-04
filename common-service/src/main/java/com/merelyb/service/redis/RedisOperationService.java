package com.merelyb.service.redis;

import com.merelyb.bean.PageBean;
import com.merelyb.bean.dataConf.DataConf;
import com.merelyb.bean.dataConf.RedisConf;
import com.merelyb.constant.DataBaseConstant;
import com.merelyb.data.DataBaseOperation;
import com.merelyb.service.BaseService;
import com.merelyb.utils.database.ConvertToSQLStrUtils;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;
import java.util.List;

/**
 * @项目: Merelyb
 * @包: com.merelyb.service.redis
 * @作者: LiM
 * @创建时间: 2018-09-04 15:03
 * @Description: redis操作
 */
public class RedisOperationService extends BaseService<RedisConf> {

    private ConvertToSQLStrUtils convertToSQLStrUtils;

    protected DataBaseOperation dataBaseOperation;

    private Logger logger = LogManager.getLogger(this.getClass());

    public RedisOperationService() throws Exception{
        DataConf dataConf = initService(DataBaseConstant.DB_CONF);
        convertToSQLStrUtils = new ConvertToSQLStrUtils();
        dataBaseOperation = new DataBaseOperation(dataConf);
    }

    @Override
    protected DataConf initService(String sCode) throws Exception{
        return super.initService(sCode);
    }

    /**
     * 新增数据
     * @param redisConf
     * @return
     * @throws Exception
     */
    @Override
    public int insert(RedisConf redisConf) throws Exception {
        if (super.insert(redisConf) == -1) return  -1;
        String sSQL = "INSERT INTO redisconf(id, addIP, port, authPwd, createTime, updateTime, isDelete) VALUES ("
            + convertToSQLStrUtils.ConvertToSQL(redisConf.getId()) + " ," + convertToSQLStrUtils.ConvertToSQL(redisConf.getAddIP())
            + ", " + convertToSQLStrUtils.ConvertToSQL(redisConf.getPort()) + ", "  + convertToSQLStrUtils.ConvertToSQL(redisConf.getAuthPwd())
            + ", " + convertToSQLStrUtils.ConvertToSQL(redisConf.getCreateTime()) + ", " + convertToSQLStrUtils.ConvertToSQL(redisConf.getUpdateTime())
            + ", " + convertToSQLStrUtils.ConvertToSQL(redisConf.getIsDelete()) + ");";
        logger.info(sSQL);
        return  dataBaseOperation.insertAndUpdateAndDelete(sSQL);
    }

    /**
     * 查询数据
     * @param redisConf
     * @return
     * @throws Exception
     */
    @Override
    public List<RedisConf> select(RedisConf redisConf) throws Exception{
        String sSQL = "SELECT * FROM redisconf WHERE 1=1 ";
        if(redisConf.getId() != null){
            sSQL += " and id = " + convertToSQLStrUtils.ConvertToSQL(redisConf.getId());
        }
        if(redisConf.getAddIP() != null){
            sSQL += " and addIP = " + convertToSQLStrUtils.ConvertToSQL(redisConf.getAddIP());
        }
        if(redisConf.getPort() != null){
            sSQL += " and port = " + convertToSQLStrUtils.ConvertToSQL(redisConf.getPort());
        }
        if(redisConf.getAuthPwd() != null){
            sSQL += " and authPwd = " + convertToSQLStrUtils.ConvertToSQL(redisConf.getAuthPwd());
        }
        if(redisConf.getIsDelete() != null){
            sSQL += " and isDelete = " + convertToSQLStrUtils.ConvertToSQL(redisConf.getIsDelete());
        }
        logger.info(sSQL);
        return dataBaseOperation.query(sSQL, RedisConf.class);
    }

    /**
     * 逻辑删除数据
     * @param redisConf
     * @return
     * @throws Exception
     */
    @Override
    public int delete(RedisConf redisConf) throws Exception {
        if (super.delete(redisConf) == -1) return -1;
        String sSQL = "UPDATE redisconf SET isDelete = 1, updateTime =" + convertToSQLStrUtils.ConvertToSQL(new Date()) + " Where ";
        if(redisConf.getId() != null){
            sSQL += " id = " + convertToSQLStrUtils.ConvertToSQL(redisConf.getId()) + " and";
        }
        if(redisConf.getAddIP() != null){
            sSQL += " addIP = " + convertToSQLStrUtils.ConvertToSQL(redisConf.getAddIP()) + " and";
        }
        if(redisConf.getPort() != null){
            sSQL += " port = " + convertToSQLStrUtils.ConvertToSQL(redisConf.getPort()) + " and";
        }
        if(redisConf.getAuthPwd() != null){
            sSQL += " authPwd = " + convertToSQLStrUtils.ConvertToSQL(redisConf.getAuthPwd()) + " and";
        }
        if(redisConf.getIsDelete() != null){
            sSQL += " isDelete = " + convertToSQLStrUtils.ConvertToSQL(redisConf.getIsDelete()) + " and";
        }
        sSQL = sSQL.substring(0, sSQL.lastIndexOf("and"));
        logger.info(sSQL);
        return dataBaseOperation.insertAndUpdateAndDelete(sSQL);
    }

    /**
     * 更新数据
     * @param redisConf
     * @return
     * @throws Exception
     */
    @Override
    public int updateById(RedisConf redisConf) throws Exception {
        if (super.updateById(redisConf) == -1) return -1;
        String sSQL = "UPDATE redisconf SET updateTime =" + convertToSQLStrUtils.ConvertToSQL(new Date()) + ", ";
        if(redisConf.getAddIP() != null){
            sSQL += " addIP = " + convertToSQLStrUtils.ConvertToSQL(redisConf.getAddIP()) + ", ";
        }
        if(redisConf.getPort() != null){
            sSQL += " port = " + convertToSQLStrUtils.ConvertToSQL(redisConf.getPort()) + ", ";
        }
        if(redisConf.getAuthPwd() != null){
            sSQL += " authPwd = " + convertToSQLStrUtils.ConvertToSQL(redisConf.getAuthPwd()) + ", ";
        }
        if(redisConf.getIsDelete() != null){
            sSQL += " isDelete = " + convertToSQLStrUtils.ConvertToSQL(redisConf.getIsDelete()) + ", ";
        }
        sSQL = sSQL.substring(0, sSQL.lastIndexOf(",")) + " WHERE id = " + convertToSQLStrUtils.ConvertToSQL(redisConf.getId());
        logger.info(sSQL);
        return dataBaseOperation.insertAndUpdateAndDelete(sSQL);
    }

    /**
     * 更新数据
     * @param redisConf
     * @param redisConfWhere
     * @return
     * @throws Exception
     */
    @Override
    public int updateByBean(RedisConf redisConf, RedisConf redisConfWhere) throws Exception {
        if (super.updateByBean(redisConf, redisConfWhere) == -1) return  -1;
        String sSQL = "UPDATE redisconf SET updateTime =" + convertToSQLStrUtils.ConvertToSQL(new Date()) + ", ";
        if(redisConf.getAddIP() != null){
            sSQL += " addIP = " + convertToSQLStrUtils.ConvertToSQL(redisConf.getAddIP()) + ", ";
        }
        if(redisConf.getPort() != null){
            sSQL += " port = " + convertToSQLStrUtils.ConvertToSQL(redisConf.getPort()) + ", ";
        }
        if(redisConf.getAuthPwd() != null){
            sSQL += " authPwd = " + convertToSQLStrUtils.ConvertToSQL(redisConf.getAuthPwd()) + ", ";
        }
        if(redisConf.getIsDelete() != null){
            sSQL += " isDelete = " + convertToSQLStrUtils.ConvertToSQL(redisConf.getIsDelete()) + ", ";
        }
        sSQL = sSQL.substring(0, sSQL.lastIndexOf(",")) + " WHERE ";

        String sWhere = "";

        if(redisConf.getId() != null){
            sWhere += " id = " + convertToSQLStrUtils.ConvertToSQL(redisConf.getId()) + " and ";
        }
        if(redisConf.getAddIP() != null){
            sWhere += " addIP = " + convertToSQLStrUtils.ConvertToSQL(redisConf.getAddIP())  + " and ";
        }
        if(redisConf.getPort() != null){
            sWhere += " port = " + convertToSQLStrUtils.ConvertToSQL(redisConf.getPort())  + " and ";
        }
        if(redisConf.getAuthPwd() != null){
            sWhere += " authPwd = " + convertToSQLStrUtils.ConvertToSQL(redisConf.getAuthPwd())  + " and ";
        }
        if(redisConf.getIsDelete() != null){
            sWhere += " isDelete = " + convertToSQLStrUtils.ConvertToSQL(redisConf.getIsDelete())  + " and ";
        }
        sWhere = sWhere.substring(0, sWhere.lastIndexOf("and"));
        sSQL += sWhere;
        logger.info(sSQL);
        return dataBaseOperation.insertAndUpdateAndDelete(sSQL);
    }

    /**
     * 分页查询
     * @param redisConf
     * @param pageBean
     * @return
     * @throws Exception
     */
    @Override
    public List<RedisConf> selectByPage(RedisConf redisConf, PageBean pageBean) throws Exception{
        String sSQL = "SELECT * FROM redisconf WHERE 1=1 ";
        if(redisConf.getId() != null){
            sSQL += " and id = " + convertToSQLStrUtils.ConvertToSQL(redisConf.getId());
        }
        if(redisConf.getAddIP() != null){
            sSQL += " and addIP = " + convertToSQLStrUtils.ConvertToSQL(redisConf.getAddIP());
        }
        if(redisConf.getPort() != null){
            sSQL += " and port = " + convertToSQLStrUtils.ConvertToSQL(redisConf.getPort());
        }
        if(redisConf.getAuthPwd() != null){
            sSQL += " and authPwd = " + convertToSQLStrUtils.ConvertToSQL(redisConf.getAuthPwd());
        }
        if(redisConf.getIsDelete() != null){
            sSQL += " and isDelete = " + convertToSQLStrUtils.ConvertToSQL(redisConf.getIsDelete());
        }
        sSQL += " limit " + String.valueOf(pageBean.getOffset()) + ", " + String.valueOf(pageBean.getPageSize());
        logger.info(sSQL);
        return dataBaseOperation.query(sSQL, RedisConf.class);
    }
}
