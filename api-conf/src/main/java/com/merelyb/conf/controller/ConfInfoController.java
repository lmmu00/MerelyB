package com.merelyb.conf.controller;

import com.merelyb.bean.DataConf;
import com.merelyb.bean.ResultBean;
import com.merelyb.conf.bean.DataBaseInfo;
import com.merelyb.constant.CodeConstant;
import com.merelyb.constant.RequestConstant;
import com.merelyb.constant.ResultConstant;
import com.merelyb.data.DataBaseOperation;
import com.merelyb.utils.database.RedisUtils;
import com.merelyb.utils.json.JsonUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @项目: Merelyb
 * @包: com.merelyb.conf.controller
 * @作者: LiM
 * @创建时间: 2018-07-31 11:06
 * @Description: ${Description}
 */
@Controller
public class ConfInfoController {
    @Autowired
    private DataBaseInfo dataBaseInfo;

    private Logger logger =LogManager.getLogger(this.getClass());

    /**
     * 返回结果
     *
     * @param bSuccess
     * @param sCode
     * @param sData
     * @param sMsg
     * @return
     */
    private ResultBean addResult(boolean bSuccess, String sCode, String sData, String sMsg) {
        ResultBean resultBean = new ResultBean();
        resultBean.setMsg(sMsg);
        resultBean.setData(sData);
        resultBean.setCode(sCode);
        resultBean.setStatus(bSuccess);
        return resultBean;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/getNeedData")
    @ResponseBody
    public ResultBean getNeedDataBase(HttpServletRequest request) {
        String sCode = request.getParameter(RequestConstant.APICONF_GETNEEDDATA_CODE) != null ? request.getParameter(RequestConstant.APICONF_GETNEEDDATA_CODE).toString() : "";
        if (sCode.equals("")) {
            return addResult(false, CodeConstant.CODE_MOUDLE_APICONF + CodeConstant.CODE_APICONF_GETNEEDDATA, "", ResultConstant.MSG_COMMON_PARA_MISS);
        }
        String sSQL = "SELECT * FROM dataconf WHERE isDelete = 0 AND code = '" + sCode + "'";
        logger.info(sSQL);
        DataConf dataConf = new DataConf();
        dataConf.setConnection(dataBaseInfo.getConnection());
        dataConf.setDataType(dataBaseInfo.getDataType());
        dataConf.setuName(dataBaseInfo.getVerification().getuName());
        dataConf.setuValue(dataBaseInfo.getVerification().getuValue());
        dataConf.setpName(dataBaseInfo.getVerification().getpName());
        dataConf.setpValue(dataBaseInfo.getVerification().getpValue());

        DataBaseOperation<DataConf> dataConfDataBaseOperation = new DataBaseOperation<>(dataConf);
        try {
            List<DataConf> dataConfList = dataConfDataBaseOperation.query(sSQL, DataConf.class);
            if (dataConfList == null || dataConfList.size() == 0) {
                logger.info(CodeConstant.CODE_MOUDLE_APICONF + CodeConstant.CODE_APICONF_GETNEEDDATA + ResultConstant.MSG_COMMON_DATA_NULL);
                return addResult(false, CodeConstant.CODE_MOUDLE_APICONF + CodeConstant.CODE_APICONF_GETNEEDDATA, "", ResultConstant.MSG_COMMON_DATA_NULL);
            }
            return addResult(true, CodeConstant.CODE_SUCCESS, JsonUtils.objs2Json(dataConfList), "");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return addResult(false, CodeConstant.CODE_MOUDLE_APICONF + CodeConstant.CODE_APICONF_GETNEEDDATA, "", ResultConstant.MSG_COMMON_DATABASE_ERE);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/getNeedSQL")
    @ResponseBody
    public ResultBean getNeedSQL(HttpServletRequest request) {
        JedisShardInfo shardInfo =new JedisShardInfo("47.52.137.77", 6379);
        shardInfo.setPassword("liming5258");
        List<JedisShardInfo> shardInfos = new ArrayList<>();
        shardInfos.add(shardInfo);
        RedisUtils redisUtils = new RedisUtils(shardInfos);
        redisUtils.add("test", "test");
        System.out.println(redisUtils.get("test", String.class));
        redisUtils.destroy();

        String sCode = request.getParameter("") != null ? request.getParameter("").toString() : "";
        return new ResultBean();
    }

}
