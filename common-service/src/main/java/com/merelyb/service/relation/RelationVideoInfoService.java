package com.merelyb.service.relation;

import com.merelyb.bean.dataConf.DataConf;
import com.merelyb.bean.PageBean;
import com.merelyb.bean.relation.AccVideo;
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
 * @包: com.merelyb.data.video
 * @作者: LiM
 * @创建时间: 2018-08-13 14:03
 * @Description: ${Description}
 */
public class RelationVideoInfoService extends BaseService<AccVideo> {

    private ConvertToSQLStrUtils convertToSQLStrUtils;

    protected DataBaseOperation dataBaseOperation;

    private Logger logger = LogManager.getLogger(this.getClass());

    public RelationVideoInfoService() throws Exception {
        DataConf dataConf = initService(DataBaseConstant.DB_RELATION);
        convertToSQLStrUtils = new ConvertToSQLStrUtils();
        dataBaseOperation = new DataBaseOperation(dataConf);
    }

    @Override
    protected DataConf initService(String sCode) throws Exception {
        return super.initService(sCode);
    }

    /**
     * 插入数据
     *
     * @param accVideo
     * @return
     * @throws Exception
     */
    @Override
    public int insert(AccVideo accVideo) throws Exception {
        if (super.insert(accVideo) == -1) return -1;
        String sSQL = "INSERT INTO acc_video(id, wordInfo, imageUrl, videoUrl, accId, curType, createTime, updateTime, isDelete) VALUES("
                + convertToSQLStrUtils.ConvertToSQL(accVideo.getId()) + ", " + convertToSQLStrUtils.ConvertToSQL(accVideo.getWordInfo())
                + ", " + convertToSQLStrUtils.ConvertToSQL(accVideo.getImageUrl()) + ", " + convertToSQLStrUtils.ConvertToSQL(accVideo.getVideoUrl())
                + ", " + convertToSQLStrUtils.ConvertToSQL(accVideo.getAccId()) + ", " + convertToSQLStrUtils.ConvertToSQL(accVideo.getCreateTime())
                + ", " + convertToSQLStrUtils.ConvertToSQL(accVideo.getUpdateTime()) + ", " + convertToSQLStrUtils.ConvertToSQL(accVideo.getIsDelete())
                + ")";
        logger.info(sSQL);
        return dataBaseOperation.insertAndUpdateAndDelete(sSQL);
    }

    /**
     * 查询数据
     *
     * @param accVideo
     * @return
     * @throws Exception
     */
    @Override
    public List<AccVideo> select(AccVideo accVideo) throws Exception {
        String sSQL = "SELECT * FROM acc_video WHERE 1=1 ";
        if (accVideo.getAccId() != null) {
            sSQL += " and accId = " + convertToSQLStrUtils.ConvertToSQL(accVideo.getAccId());
        }
        if (accVideo.getCurType() != null) {
            sSQL += " and curType = " + convertToSQLStrUtils.ConvertToSQL(accVideo.getCurType());
        }
        if (accVideo.getImageUrl() != null) {
            sSQL += " and imageUrl = " + convertToSQLStrUtils.ConvertToSQL(accVideo.getImageUrl());
        }
        if (accVideo.getVideoUrl() != null) {
            sSQL += " and videoUrl = " + convertToSQLStrUtils.ConvertToSQL(accVideo.getVideoUrl());
        }
        if (accVideo.getWordInfo() != null) {
            sSQL += " and wordInfo = " + convertToSQLStrUtils.ConvertToSQL(accVideo.getWordInfo());
        }
        if (accVideo.getId() != null) {
            sSQL += " and id = " + convertToSQLStrUtils.ConvertToSQL(accVideo.getId());
        }
        if (accVideo.getIsDelete() != null) {
            sSQL += " and isDelete = " + convertToSQLStrUtils.ConvertToSQL(accVideo.getIsDelete());
        }
        logger.info(sSQL);
        return dataBaseOperation.query(sSQL, AccVideo.class);
    }

    /**
     * 逻辑删除数据
     *
     * @param accVideo
     * @return
     * @throws Exception
     */
    @Override
    public int delete(AccVideo accVideo) throws Exception {
        if (super.delete(accVideo) == -1) return -1;
        String sSQL = "UPDATE acc_video SET isDelete = 1, updateTime =" + convertToSQLStrUtils.ConvertToSQL(new Date()) + " WHERE ";
        if (accVideo.getAccId() != null) {
            sSQL += " accId = " + convertToSQLStrUtils.ConvertToSQL(accVideo.getAccId()) + "and  ";
        }
        if (accVideo.getCurType() != null) {
            sSQL += " curType = " + convertToSQLStrUtils.ConvertToSQL(accVideo.getCurType()) + "and  ";
        }
        if (accVideo.getImageUrl() != null) {
            sSQL += " imageUrl = " + convertToSQLStrUtils.ConvertToSQL(accVideo.getImageUrl()) + "and  ";
        }
        if (accVideo.getVideoUrl() != null) {
            sSQL += " videoUrl = " + convertToSQLStrUtils.ConvertToSQL(accVideo.getVideoUrl()) + "and  ";
        }
        if (accVideo.getWordInfo() != null) {
            sSQL += " wordInfo = " + convertToSQLStrUtils.ConvertToSQL(accVideo.getWordInfo()) + "and  ";
        }
        if (accVideo.getId() != null) {
            sSQL += " id = " + convertToSQLStrUtils.ConvertToSQL(accVideo.getId()) + "and  ";
        }
        if (accVideo.getIsDelete() != null) {
            sSQL += " isDelete = " + convertToSQLStrUtils.ConvertToSQL(accVideo.getIsDelete()) + "and  ";
        }
        sSQL = sSQL.substring(0, sSQL.lastIndexOf("and"));
        logger.info(sSQL);
        return dataBaseOperation.insertAndUpdateAndDelete(sSQL);
    }

    /**
     * 根据编号更新
     *
     * @param accVideo
     * @return
     * @throws Exception
     */
    @Override
    public int updateById(AccVideo accVideo) throws Exception {
        if (super.updateById(accVideo) == -1) return -1;
        if (accVideo.getId() == null) return -1;
        String sSQL = " UPDATE acc_video SET updateTime =" + convertToSQLStrUtils.ConvertToSQL(new Date()) + ", ";
        if (accVideo.getAccId() != null) {
            sSQL += " accId = " + convertToSQLStrUtils.ConvertToSQL(accVideo.getAccId()) + ",  ";
        }
        if (accVideo.getCurType() != null) {
            sSQL += " curType = " + convertToSQLStrUtils.ConvertToSQL(accVideo.getCurType()) + ",  ";
        }
        if (accVideo.getImageUrl() != null) {
            sSQL += " imageUrl = " + convertToSQLStrUtils.ConvertToSQL(accVideo.getImageUrl()) + ",  ";
        }
        if (accVideo.getVideoUrl() != null) {
            sSQL += " videoUrl = " + convertToSQLStrUtils.ConvertToSQL(accVideo.getVideoUrl()) + ",  ";
        }
        if (accVideo.getWordInfo() != null) {
            sSQL += " wordInfo = " + convertToSQLStrUtils.ConvertToSQL(accVideo.getWordInfo()) + ",  ";
        }
        if (accVideo.getIsDelete() != null) {
            sSQL += " isDelete = " + convertToSQLStrUtils.ConvertToSQL(accVideo.getIsDelete()) + ",  ";
        }
        sSQL = sSQL.substring(0, sSQL.lastIndexOf(",")) + " WHERE id = " + convertToSQLStrUtils.ConvertToSQL(accVideo.getId());
        logger.info(sSQL);
        return dataBaseOperation.insertAndUpdateAndDelete(sSQL);
    }

    /**
     * 根据条件查询
     *
     * @param accVideo
     * @param accVideoWhere
     * @return
     * @throws Exception
     */
    @Override
    public int updateByBean(AccVideo accVideo, AccVideo accVideoWhere) throws Exception {
        if (super.updateByBean(accVideo, accVideoWhere) == -1) return -1;
        String sSQL = " UPDATE acc_video SET updateTime =" + convertToSQLStrUtils.ConvertToSQL(new Date()) + ", ";
        if (accVideo.getAccId() != null) {
            sSQL += " accId = " + convertToSQLStrUtils.ConvertToSQL(accVideo.getAccId()) + ",  ";
        }
        if (accVideo.getCurType() != null) {
            sSQL += " curType = " + convertToSQLStrUtils.ConvertToSQL(accVideo.getCurType()) + ",  ";
        }
        if (accVideo.getImageUrl() != null) {
            sSQL += " imageUrl = " + convertToSQLStrUtils.ConvertToSQL(accVideo.getImageUrl()) + ",  ";
        }
        if (accVideo.getVideoUrl() != null) {
            sSQL += " videoUrl = " + convertToSQLStrUtils.ConvertToSQL(accVideo.getVideoUrl()) + ",  ";
        }
        if (accVideo.getWordInfo() != null) {
            sSQL += " wordInfo = " + convertToSQLStrUtils.ConvertToSQL(accVideo.getWordInfo()) + ",  ";
        }
        if (accVideo.getIsDelete() != null) {
            sSQL += " isDelete = " + convertToSQLStrUtils.ConvertToSQL(accVideo.getIsDelete()) + ",  ";
        }
        sSQL = sSQL.substring(0, sSQL.lastIndexOf(",")) + " WHERE ";

        String sWhere = "";
        if (accVideoWhere.getAccId() != null) {
            sWhere += " accId = " + convertToSQLStrUtils.ConvertToSQL(accVideo.getAccId()) + "and  ";
        }
        if (accVideoWhere.getCurType() != null) {
            sWhere += " curType = " + convertToSQLStrUtils.ConvertToSQL(accVideo.getCurType()) + "and  ";
        }
        if (accVideoWhere.getImageUrl() != null) {
            sWhere += " imageUrl = " + convertToSQLStrUtils.ConvertToSQL(accVideo.getImageUrl()) + "and  ";
        }
        if (accVideoWhere.getVideoUrl() != null) {
            sWhere += " videoUrl = " + convertToSQLStrUtils.ConvertToSQL(accVideo.getVideoUrl()) + "and  ";
        }
        if (accVideoWhere.getWordInfo() != null) {
            sWhere += " wordInfo = " + convertToSQLStrUtils.ConvertToSQL(accVideo.getWordInfo()) + "and  ";
        }
        if (accVideoWhere.getIsDelete() != null) {
            sWhere += " isDelete = " + convertToSQLStrUtils.ConvertToSQL(accVideo.getIsDelete()) + "and  ";
        }
        sWhere = sWhere.substring(0, sWhere.lastIndexOf("and"));
        sSQL += sWhere;
        logger.info(sSQL);
        return dataBaseOperation.insertAndUpdateAndDelete(sSQL);
    }

    /**
     * 分页查询
     *
     * @param accVideo
     * @param pageBean
     * @return
     * @throws Exception
     */
    @Override
    public List<AccVideo> selectByPage(AccVideo accVideo, PageBean pageBean) throws Exception {
        String sSQL = "SELECT * FROM acc_video WHERE 1=1 ";
        if (accVideo.getAccId() != null) {
            sSQL += " and accId = " + convertToSQLStrUtils.ConvertToSQL(accVideo.getAccId());
        }
        if (accVideo.getCurType() != null) {
            sSQL += " and curType = " + convertToSQLStrUtils.ConvertToSQL(accVideo.getCurType());
        }
        if (accVideo.getImageUrl() != null) {
            sSQL += " and imageUrl = " + convertToSQLStrUtils.ConvertToSQL(accVideo.getImageUrl());
        }
        if (accVideo.getVideoUrl() != null) {
            sSQL += " and videoUrl = " + convertToSQLStrUtils.ConvertToSQL(accVideo.getVideoUrl());
        }
        if (accVideo.getWordInfo() != null) {
            sSQL += " and wordInfo = " + convertToSQLStrUtils.ConvertToSQL(accVideo.getWordInfo());
        }
        if (accVideo.getId() != null) {
            sSQL += " and id = " + convertToSQLStrUtils.ConvertToSQL(accVideo.getId());
        }
        if (accVideo.getIsDelete() != null) {
            sSQL += " and isDelete = " + convertToSQLStrUtils.ConvertToSQL(accVideo.getIsDelete());
        }
        sSQL += " limit " + String.valueOf(pageBean.getOffset()) + ", " + String.valueOf(pageBean.getPageSize());
        logger.info(sSQL);
        return dataBaseOperation.query(sSQL, AccVideo.class);
    }

    /**
     * 批量逻辑删除
     * @param idList
     * @return
     * @throws Exception
     */
    public int deleteByList(List<String> idList) throws Exception {
        if (idList.size() == 0) return -1;
        String sSQL = "UPDATE acc_video SET isDelete = 1, updateTime =" + convertToSQLStrUtils.ConvertToSQL(new Date()) + " WHERE ";
        String sIds = "";
        for (String sId : idList
                ) {
            if (sIds.equals("")) {
                sIds = sId;
            } else {
                sIds += "," + sId;
            }
        }
        sSQL += " id in (" + sIds + ")  ";
        logger.info(sSQL);
        return dataBaseOperation.insertAndUpdateAndDelete(sSQL);
    }

}
