package com.merelyb.service.video;

import com.merelyb.bean.DataConf;
import com.merelyb.bean.PageBean;
import com.merelyb.bean.video.VideoInfo;
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
public class VideoInfoService extends BaseService<VideoInfo> {

    private ConvertToSQLStrUtils convertToSQLStrUtils;

    protected DataBaseOperation dataBaseOperation;

    private Logger logger = LogManager.getLogger(this.getClass());

    public VideoInfoService() throws Exception {
        DataConf dataConf = initService(DataBaseConstant.DB_VIDEOINFO);
        convertToSQLStrUtils = new ConvertToSQLStrUtils();
        dataBaseOperation = new DataBaseOperation(dataConf);
    }

    @Override
    protected DataConf initService(String sCode) throws Exception{
        return super.initService(sCode);
    }

    /**
     * 插入数据
     * @param videoInfo
     * @return
     * @throws Exception
     */
    @Override
    public int insert(VideoInfo videoInfo) throws Exception{
        if (super.insert(videoInfo) == -1) return -1;
        String sSQL = "INSERT INTO video_info(id, wordInfo, imageUrl, videoUrl, accId, curType, createTime, updateTime, isDelete) VALUES("
                + convertToSQLStrUtils.ConvertToSQL(videoInfo.getId()) + ", "  + convertToSQLStrUtils.ConvertToSQL(videoInfo.getWordInfo())
                + ", " + convertToSQLStrUtils.ConvertToSQL(videoInfo.getImageUrl()) + ", " + convertToSQLStrUtils.ConvertToSQL(videoInfo.getVideoUrl())
                + ", " + convertToSQLStrUtils.ConvertToSQL(videoInfo.getAccId()) + ", " + convertToSQLStrUtils.ConvertToSQL(videoInfo.getCreateTime())
                + ", " + convertToSQLStrUtils.ConvertToSQL(videoInfo.getUpdateTime()) + ", " + convertToSQLStrUtils.ConvertToSQL(videoInfo.getIsDelete())
                + ")";
        logger.info(sSQL);
        return  dataBaseOperation.insertAndUpdateAndDelete(sSQL);
    }

    /**
     * 查询数据
     * @param videoInfo
     * @return
     * @throws Exception
     */
    @Override
    public List<VideoInfo> select(VideoInfo videoInfo) throws Exception{
        String sSQL = "SELECT * FROM video_info WHERE 1=1 ";
        if(videoInfo.getAccId() != null){
            sSQL += " and accId = " + convertToSQLStrUtils.ConvertToSQL(videoInfo.getAccId());
        }
        if(videoInfo.getCurType() != null){
            sSQL += " and curType = " + convertToSQLStrUtils.ConvertToSQL(videoInfo.getCurType());
        }
        if(videoInfo.getImageUrl() != null){
            sSQL += " and imageUrl = " + convertToSQLStrUtils.ConvertToSQL(videoInfo.getImageUrl());
        }
        if(videoInfo.getVideoUrl() != null){
            sSQL += " and videoUrl = " + convertToSQLStrUtils.ConvertToSQL(videoInfo.getVideoUrl());
        }
        if(videoInfo.getWordInfo() != null){
            sSQL += " and wordInfo = " + convertToSQLStrUtils.ConvertToSQL(videoInfo.getWordInfo());
        }
        if(videoInfo.getId() != null){
            sSQL += " and id = " + convertToSQLStrUtils.ConvertToSQL(videoInfo.getId());
        }
        if(videoInfo.getIsDelete() != null){
            sSQL += " and isDelete = " + convertToSQLStrUtils.ConvertToSQL(videoInfo.getIsDelete());
        }
        logger.info(sSQL);
        return  dataBaseOperation.query(sSQL, VideoInfo.class);
    }

    /**
     * 逻辑删除数据
     * @param videoInfo
     * @return
     * @throws Exception
     */
    @Override
    public int delete(VideoInfo videoInfo) throws Exception{
        if (super.delete(videoInfo) == -1) return -1;
        String sSQL = "UPDATE video_info SET isDelete = 1, updateTime =" + convertToSQLStrUtils.ConvertToSQL(new Date()) + " WHERE ";
        if(videoInfo.getAccId() != null){
            sSQL += " accId = " + convertToSQLStrUtils.ConvertToSQL(videoInfo.getAccId()) + "and  ";
        }
        if(videoInfo.getCurType() != null){
            sSQL += " curType = " + convertToSQLStrUtils.ConvertToSQL(videoInfo.getCurType()) + "and  ";
        }
        if(videoInfo.getImageUrl() != null){
            sSQL += " imageUrl = " + convertToSQLStrUtils.ConvertToSQL(videoInfo.getImageUrl()) + "and  ";
        }
        if(videoInfo.getVideoUrl() != null){
            sSQL += " videoUrl = " + convertToSQLStrUtils.ConvertToSQL(videoInfo.getVideoUrl()) + "and  ";
        }
        if(videoInfo.getWordInfo() != null){
            sSQL += " wordInfo = " + convertToSQLStrUtils.ConvertToSQL(videoInfo.getWordInfo()) + "and  ";
        }
        if(videoInfo.getId() != null){
            sSQL += " id = " + convertToSQLStrUtils.ConvertToSQL(videoInfo.getId()) + "and  ";
        }
        if(videoInfo.getIsDelete() != null){
            sSQL += " isDelete = " + convertToSQLStrUtils.ConvertToSQL(videoInfo.getIsDelete()) + "and  ";
        }
        sSQL = sSQL.substring(0, sSQL.lastIndexOf("and"));
        logger.info(sSQL);
        return dataBaseOperation.insertAndUpdateAndDelete(sSQL);
    }

    /**
     * 根据编号更新
     * @param videoInfo
     * @return
     * @throws Exception
     */
    @Override
    public int updateById(VideoInfo videoInfo) throws Exception{
        if (super.updateById(videoInfo) == -1) return -1;
        if(videoInfo.getId() == null) return  -1;
        String sSQL = " UPDATE video_info SET updateTime =" + convertToSQLStrUtils.ConvertToSQL(new Date()) + ", ";
        if(videoInfo.getAccId() != null){
            sSQL += " accId = " + convertToSQLStrUtils.ConvertToSQL(videoInfo.getAccId()) + ",  ";
        }
        if(videoInfo.getCurType() != null){
            sSQL += " curType = " + convertToSQLStrUtils.ConvertToSQL(videoInfo.getCurType()) + ",  ";
        }
        if(videoInfo.getImageUrl() != null){
            sSQL += " imageUrl = " + convertToSQLStrUtils.ConvertToSQL(videoInfo.getImageUrl()) + ",  ";
        }
        if(videoInfo.getVideoUrl() != null){
            sSQL += " videoUrl = " + convertToSQLStrUtils.ConvertToSQL(videoInfo.getVideoUrl()) + ",  ";
        }
        if(videoInfo.getWordInfo() != null){
            sSQL += " wordInfo = " + convertToSQLStrUtils.ConvertToSQL(videoInfo.getWordInfo()) + ",  ";
        }
        if(videoInfo.getIsDelete() != null){
            sSQL += " isDelete = " + convertToSQLStrUtils.ConvertToSQL(videoInfo.getIsDelete()) + ",  ";
        }
        sSQL = sSQL.substring(0, sSQL.lastIndexOf(",")) + " WHERE id = " + convertToSQLStrUtils.ConvertToSQL(videoInfo.getId());
        logger.info(sSQL);
        return dataBaseOperation.insertAndUpdateAndDelete(sSQL);
    }

    /**
     * 根据条件查询
     * @param videoInfo
     * @param videoInfoWhere
     * @return
     * @throws Exception
     */
    @Override
    public int updateByBean(VideoInfo videoInfo, VideoInfo videoInfoWhere) throws Exception {
        if(super.updateByBean(videoInfo, videoInfoWhere) == -1) return -1;
        String sSQL = " UPDATE video_info SET updateTime =" + convertToSQLStrUtils.ConvertToSQL(new Date()) + ", ";
        if(videoInfo.getAccId() != null){
            sSQL += " accId = " + convertToSQLStrUtils.ConvertToSQL(videoInfo.getAccId()) + ",  ";
        }
        if(videoInfo.getCurType() != null){
            sSQL += " curType = " + convertToSQLStrUtils.ConvertToSQL(videoInfo.getCurType()) + ",  ";
        }
        if(videoInfo.getImageUrl() != null){
            sSQL += " imageUrl = " + convertToSQLStrUtils.ConvertToSQL(videoInfo.getImageUrl()) + ",  ";
        }
        if(videoInfo.getVideoUrl() != null){
            sSQL += " videoUrl = " + convertToSQLStrUtils.ConvertToSQL(videoInfo.getVideoUrl()) + ",  ";
        }
        if(videoInfo.getWordInfo() != null){
            sSQL += " wordInfo = " + convertToSQLStrUtils.ConvertToSQL(videoInfo.getWordInfo()) + ",  ";
        }
        if(videoInfo.getIsDelete() != null){
            sSQL += " isDelete = " + convertToSQLStrUtils.ConvertToSQL(videoInfo.getIsDelete()) + ",  ";
        }
        sSQL = sSQL.substring(0, sSQL.lastIndexOf(",")) + " WHERE ";

        String sWhere = "";
        if(videoInfoWhere.getAccId() != null){
            sWhere += " accId = " + convertToSQLStrUtils.ConvertToSQL(videoInfo.getAccId()) + "and  ";
        }
        if(videoInfoWhere.getCurType() != null){
            sWhere += " curType = " + convertToSQLStrUtils.ConvertToSQL(videoInfo.getCurType()) + "and  ";
        }
        if(videoInfoWhere.getImageUrl() != null){
            sWhere += " imageUrl = " + convertToSQLStrUtils.ConvertToSQL(videoInfo.getImageUrl()) + "and  ";
        }
        if(videoInfoWhere.getVideoUrl() != null){
            sWhere += " videoUrl = " + convertToSQLStrUtils.ConvertToSQL(videoInfo.getVideoUrl()) + "and  ";
        }
        if(videoInfoWhere.getWordInfo() != null){
            sWhere += " wordInfo = " + convertToSQLStrUtils.ConvertToSQL(videoInfo.getWordInfo()) + "and  ";
        }
        if(videoInfoWhere.getIsDelete() != null){
            sWhere += " isDelete = " + convertToSQLStrUtils.ConvertToSQL(videoInfo.getIsDelete()) + "and  ";
        }
        sWhere = sWhere.substring(0, sWhere.lastIndexOf(","));
        sSQL += sWhere;
        logger.info(sSQL);
        return dataBaseOperation.insertAndUpdateAndDelete(sSQL);
    }

    /**
     * 分页查询
     * @param videoInfo
     * @param pageBean
     * @return
     * @throws Exception
     */
    @Override
    public List<VideoInfo> selectByPage(VideoInfo videoInfo, PageBean pageBean) throws Exception{
        String sSQL = "SELECT * FROM video_info WHERE 1=1 ";
        if(videoInfo.getAccId() != null){
            sSQL += " and accId = " + convertToSQLStrUtils.ConvertToSQL(videoInfo.getAccId());
        }
        if(videoInfo.getCurType() != null){
            sSQL += " and curType = " + convertToSQLStrUtils.ConvertToSQL(videoInfo.getCurType());
        }
        if(videoInfo.getImageUrl() != null){
            sSQL += " and imageUrl = " + convertToSQLStrUtils.ConvertToSQL(videoInfo.getImageUrl());
        }
        if(videoInfo.getVideoUrl() != null){
            sSQL += " and videoUrl = " + convertToSQLStrUtils.ConvertToSQL(videoInfo.getVideoUrl());
        }
        if(videoInfo.getWordInfo() != null){
            sSQL += " and wordInfo = " + convertToSQLStrUtils.ConvertToSQL(videoInfo.getWordInfo());
        }
        if(videoInfo.getId() != null){
            sSQL += " and id = " + convertToSQLStrUtils.ConvertToSQL(videoInfo.getId());
        }
        if(videoInfo.getIsDelete() != null){
            sSQL += " and isDelete = " + convertToSQLStrUtils.ConvertToSQL(videoInfo.getIsDelete());
        }
        sSQL += " limit " + String.valueOf(pageBean.getOffset()) + ", " + String.valueOf(pageBean.getPageSize());
        logger.info(sSQL);
        return  dataBaseOperation.query(sSQL, VideoInfo.class);
    }
}
