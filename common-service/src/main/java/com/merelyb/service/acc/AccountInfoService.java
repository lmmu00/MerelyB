package com.merelyb.service.acc;

import com.merelyb.bean.AccInfo;
import com.merelyb.bean.DataConf;
import com.merelyb.bean.PageBean;
import com.merelyb.constant.DataBaseConstant;
import com.merelyb.data.DataBaseOperation;
import com.merelyb.service.BaseService;
import com.merelyb.utils.database.ConvertToSQLStrUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;
import java.util.List;

/**
 * @项目: Merelyb
 * @包: com.merelyb.data.acc
 * @作者: LiM
 * @创建时间: 2018-08-06 10:58
 * @Description: acc操作
 */
public class AccountInfoService extends BaseService<AccInfo> {

    private ConvertToSQLStrUtils convertToSQLStrUtils;

    protected DataBaseOperation dataBaseOperation;

    private Logger logger = LogManager.getLogger(this.getClass());

    public AccountInfoService() throws Exception {
        DataConf dataConf = initService(DataBaseConstant.DB_ACCOUNT);
        convertToSQLStrUtils = new ConvertToSQLStrUtils();
        dataBaseOperation = new DataBaseOperation(dataConf);
    }

    @Override
    protected DataConf initService(String sCode) throws Exception{
        return super.initService(sCode);
    }

    /**
     * 新增ACC
     * @param accInfo
     * @return
     * @throws Exception
     */
    @Override
    public int insert(AccInfo accInfo) throws Exception {
        if(super.insert(accInfo) == -1) return  -1;
        String sSQL = "INSERT INTO acc_info(accId, accUser, accPwd, accStatus, createTime, updateTime, isDelete)VALUES("
                + convertToSQLStrUtils.ConvertToSQL(accInfo.getAccId())+ ", " + convertToSQLStrUtils.ConvertToSQL(accInfo.getAccUser())
                + ", " + convertToSQLStrUtils.ConvertToSQL(accInfo.getAccPwd())  + ", " +  convertToSQLStrUtils.ConvertToSQL(accInfo.getAccStatus())
                + ", " + convertToSQLStrUtils.ConvertToSQL(accInfo.getCreateTime()) + ", " + convertToSQLStrUtils.ConvertToSQL(accInfo.getUpdateTime())
                + "," + convertToSQLStrUtils.ConvertToSQL(accInfo.getIsDelete()) + ")";
        logger.info(sSQL);
        return dataBaseOperation.insertAndUpdateAndDelete(sSQL);
    }

    /**
     * 逻辑删除数据
     * @param accInfo
     * @return
     * @throws Exception
     */
    @Override
    public int delete(AccInfo accInfo) throws Exception {
        if(super.delete(accInfo) == -1) return  -1;
        String sSQL = "UPDATE acc_info SET isDelete = 1, updateTime = " + convertToSQLStrUtils.ConvertToSQL(new Date());
        sSQL += " WHERE ";
        if(accInfo.getAccId() != null){
            sSQL += " accId = " + convertToSQLStrUtils.ConvertToSQL(accInfo.getAccId()) + " and ";
        }
        if(accInfo.getAccUser() != null){
            sSQL += " accUser = " + convertToSQLStrUtils.ConvertToSQL(accInfo.getAccUser()) + " and ";
        }
        if(accInfo.getAccStatus() != null){
            sSQL += " accStatus = " + convertToSQLStrUtils.ConvertToSQL(accInfo.getAccStatus()) + " and ";
        }
        sSQL = sSQL.substring(0, sSQL.lastIndexOf("and"));
        logger.info(sSQL);
        return dataBaseOperation.insertAndUpdateAndDelete(sSQL);
    }

    /**
     * 根据编号更新数据
     * @param accInfo
     * @return
     * @throws Exception
     */
    @Override
    public int updateById(AccInfo accInfo) throws Exception {
        if(super.updateById(accInfo) == -1) return -1;
        if(accInfo.getAccId() == null) return  -1;
        String sSQL = "UPDATE acc_info SET updateTime = " + convertToSQLStrUtils.ConvertToSQL(new Date());
        if(accInfo.getAccPwd() != null){
            sSQL += " accPwd = " + convertToSQLStrUtils.ConvertToSQL(accInfo.getAccPwd()) + ", ";
        }
        if(accInfo.getAccUser() != null){
            sSQL += " accUser = " + convertToSQLStrUtils.ConvertToSQL(accInfo.getAccUser()) + ", ";
        }
        if(accInfo.getAccStatus() != null){
            sSQL += " accStatus = " + convertToSQLStrUtils.ConvertToSQL(accInfo.getAccStatus()) + ", ";
        }
        sSQL = sSQL.substring(0, sSQL.lastIndexOf(","));
        sSQL += " WHERE accId = " + convertToSQLStrUtils.ConvertToSQL(accInfo.getAccId());
        logger.info(sSQL);
        return dataBaseOperation.insertAndUpdateAndDelete(sSQL);
    }

    /**
     * 根据条件查询
     * @param accInfo
     * @param accInfoWhere
     * @return
     * @throws Exception
     */
    @Override
    public int updateByBean(AccInfo accInfo, AccInfo accInfoWhere) throws Exception {
        if(super.updateByBean(accInfo, accInfoWhere) == -1) return -1;
        String sSQL = "UPDATE acc_info SET updateTime = " + convertToSQLStrUtils.ConvertToSQL(new Date());
        if(accInfo.getAccPwd() != null){
            sSQL += " accPwd = " + convertToSQLStrUtils.ConvertToSQL(accInfo.getAccPwd()) + ", ";
        }
        if(accInfo.getAccUser() != null){
            sSQL += " accUser = " + convertToSQLStrUtils.ConvertToSQL(accInfo.getAccUser()) + ", ";
        }
        if(accInfo.getAccStatus() != null){
            sSQL += " accStatus = " + convertToSQLStrUtils.ConvertToSQL(accInfo.getAccStatus()) + ", ";
        }
        sSQL = sSQL.substring(0, sSQL.lastIndexOf(","));

        String sWhere = "";
        if(accInfoWhere.getAccPwd() != null){
            sWhere += " accPwd = " + convertToSQLStrUtils.ConvertToSQL(accInfoWhere.getAccPwd()) + " and ";
        }
        if(accInfoWhere.getAccUser() != null){
            sWhere += " accUser = " + convertToSQLStrUtils.ConvertToSQL(accInfoWhere.getAccUser()) + " and ";
        }
        if(accInfoWhere.getAccStatus() != null){
            sWhere += " accStatus = " + convertToSQLStrUtils.ConvertToSQL(accInfoWhere.getAccStatus()) + " and ";
        }
        if(accInfoWhere.getAccId() != null){
            sWhere += " accId = " + convertToSQLStrUtils.ConvertToSQL(accInfoWhere.getAccId()) + " and ";
        }
        sWhere = sWhere.substring(0, sWhere.lastIndexOf("and"));
        sSQL += sWhere;
        logger.info(sSQL);
        return dataBaseOperation.insertAndUpdateAndDelete(sSQL);
    }

    /**
     * 按条件查询
     * @param accInfo
     * @return
     * @throws Exception
     */
    @Override
    public List<AccInfo> select(AccInfo accInfo) throws Exception {
        String sSQL = "SELECT * FROM acc_info WHERE 1=1 ";
        if(accInfo.getAccId() != null){
            sSQL += " and accId = " + convertToSQLStrUtils.ConvertToSQL(accInfo.getAccId());
        }
        if(accInfo.getAccUser() != null){
            sSQL += " and accUser = " + convertToSQLStrUtils.ConvertToSQL(accInfo.getAccUser());
        }
        if(accInfo.getAccStatus() != null){
            sSQL += " and accStatus = " + convertToSQLStrUtils.ConvertToSQL(accInfo.getAccStatus());
        }
        if(accInfo.getIsDelete() != null){
            sSQL += " and isDelete = " + convertToSQLStrUtils.ConvertToSQL(accInfo.getIsDelete());
        }
        logger.info(sSQL);
        return dataBaseOperation.query(sSQL, AccInfo.class);
    }

    /**
     * 分页查询数据
     * @param accInfo
     * @param pageBean
     * @return
     * @throws Exception
     */
    @Override
    public List<AccInfo> selectByPage(AccInfo accInfo, PageBean pageBean) throws Exception{
        String sSQL = "SELECT * FROM acc_info WHERE 1=1 ";
        if(accInfo.getAccId() != null){
            sSQL += " and accId = " + convertToSQLStrUtils.ConvertToSQL(accInfo.getAccId());
        }
        if(accInfo.getAccUser() != null){
            sSQL += " and accUser = " + convertToSQLStrUtils.ConvertToSQL(accInfo.getAccUser());
        }
        if(accInfo.getAccStatus() != null){
            sSQL += " and accStatus = " + convertToSQLStrUtils.ConvertToSQL(accInfo.getAccStatus());
        }
        if(accInfo.getIsDelete() != null){
            sSQL += " and isDelete = " + convertToSQLStrUtils.ConvertToSQL(accInfo.getIsDelete());
        }
        sSQL += " Limit " + String.valueOf(pageBean.getOffset()) + ", " + String.valueOf(pageBean.getPageSize());
        logger.info(sSQL);
        return dataBaseOperation.query(sSQL, AccInfo.class);
    }
}
