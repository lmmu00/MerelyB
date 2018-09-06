package com.merelyb.relation.controller;

import com.merelyb.bean.ResultBean;
import com.merelyb.bean.relation.AccVideo;
import com.merelyb.constant.CodeConstant;
import com.merelyb.constant.RequestConstant;
import com.merelyb.constant.ResultConstant;
import com.merelyb.service.redis.RedisOperationService;
import com.merelyb.service.relation.RelationVideoInfoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @项目: Merelyb
 * @包: com.merelyb.relation.controller
 * @作者: LiM
 * @创建时间: 2018-09-04 20:36
 * @Description: ${Description}
 */
@Controller
@RequestMapping("relation")
public class AccVideoRelationController {

    private Logger logger = LogManager.getLogger(this.getClass());

    /**
     * 新增和修改视频关联
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "newVideo")
    @ResponseBody
    public ResultBean addAccVideoRelation(HttpServletRequest request){
        ResultBean resultBean = new ResultBean();
        String sToken = request.getParameter(RequestConstant.REQUEST_TOKEN)!= null?request.getParameter(RequestConstant.REQUEST_TOKEN): "";
        if(sToken.equals("")){
            try {
                RedisOperationService redisOperationService = new RedisOperationService();
                if(!redisOperationService.exitToken(sToken)){
                    resultBean.setStatus(false);
                    resultBean.setCode(CodeConstant.CODE_MOUDLE_RELATION + CodeConstant.CODE_RELATION_NEWVIDEO);
                    resultBean.setData("");
                    resultBean.setMsg(ResultConstant.MSG_TOKEN_ERR);
                    logger.info(resultBean);
                    return resultBean;
                }
                String sRemark = request.getParameter(RequestConstant.REQUEST_REMARK)!= null?request.getParameter(RequestConstant.REQUEST_REMARK): "";
                String sVideoUrl = request.getParameter(RequestConstant.REQUEST_VIDEOURL)!= null?request.getParameter(RequestConstant.REQUEST_VIDEOURL): "";
                String sImageUrl = request.getParameter(RequestConstant.REQUEST_IMAGEURL)!= null?request.getParameter(RequestConstant.REQUEST_IMAGEURL): "";
                String sCurType = request.getParameter(RequestConstant.REQUEST_CURTYPE)!= null?request.getParameter(RequestConstant.REQUEST_CURTYPE): "";
                String sId = request.getParameter(RequestConstant.REQUEST_CURID)!= null?request.getParameter(RequestConstant.REQUEST_CURID): "";

                AccVideo accVideo = new AccVideo();
                accVideo.setImageUrl(sImageUrl);
                accVideo.setVideoUrl(sVideoUrl);
                accVideo.setWordInfo(sRemark);
                accVideo.setCurType(Byte.valueOf(sCurType));
                accVideo.setCreateTime(new Date());
                accVideo.setIsDelete((byte)0);
                accVideo.setAccId(Long.valueOf(redisOperationService.getAccFromToken(sToken)));
                if(!sId.equals("")) accVideo.setAccId(Long.valueOf(sId));

                RelationVideoInfoService  relationVideoInfoService = new RelationVideoInfoService();
                if(sId.equals("")) {
                    relationVideoInfoService.insert(accVideo);
                }else {
                    relationVideoInfoService.updateById(accVideo);
                }

                redisOperationService.destroy();

                resultBean.setStatus(true);
                resultBean.setCode(CodeConstant.CODE_MOUDLE_RELATION + CodeConstant.CODE_RELATION_NEWVIDEO);
                resultBean.setData("");
                resultBean.setMsg(ResultConstant.MSG_COMMON_SUCCESS);
                logger.info(resultBean);
            } catch (Exception e) {
                e.printStackTrace();
                resultBean.setStatus(false);
                resultBean.setCode(CodeConstant.CODE_MOUDLE_RELATION + CodeConstant.CODE_RELATION_NEWVIDEO);
                resultBean.setData("");
                resultBean.setMsg(ResultConstant.MSG_COMMON_DATABASE_ERE);
                logger.error(resultBean);
                logger.error(e.getMessage());
            }
        }
        return resultBean;
    }

    /**
     * 查询数据
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "selectVideo")
    @ResponseBody
    public ResultBean getAccVideoRelation(HttpServletRequest request){
        ResultBean resultBean = new ResultBean();
        String sToken = request.getParameter(RequestConstant.REQUEST_TOKEN)!= null?request.getParameter(RequestConstant.REQUEST_TOKEN): "";
        if(sToken.equals("")){
            try {
                RedisOperationService redisOperationService = new RedisOperationService();
                if(!redisOperationService.exitToken(sToken)){
                    resultBean.setStatus(false);
                    resultBean.setCode(CodeConstant.CODE_MOUDLE_RELATION + CodeConstant.CODE_RELATION_GETVIDEO);
                    resultBean.setData("");
                    resultBean.setMsg(ResultConstant.MSG_TOKEN_ERR);
                    logger.info(resultBean);
                    return resultBean;
                }
                String sId = request.getParameter(RequestConstant.REQUEST_CURID)!= null?request.getParameter(RequestConstant.REQUEST_CURID): "";

                AccVideo accVideo = new AccVideo();
                accVideo.setIsDelete((byte)0);
                if(!sId.equals("")) {
                    accVideo.setAccId(Long.valueOf(sId));
                }else {
                    accVideo.setAccId(Long.valueOf(redisOperationService.getAccFromToken(sToken)));
                }


                RelationVideoInfoService  relationVideoInfoService = new RelationVideoInfoService();
                List<AccVideo> accVideoList = relationVideoInfoService.select(accVideo);

                redisOperationService.destroy();

                resultBean.setStatus(true);
                resultBean.setCode(CodeConstant.CODE_MOUDLE_RELATION + CodeConstant.CODE_RELATION_GETVIDEO);
                resultBean.setData(accVideoList);
                resultBean.setMsg(ResultConstant.MSG_COMMON_SUCCESS);
                logger.info(resultBean);
            } catch (Exception e) {
                e.printStackTrace();
                resultBean.setStatus(false);
                resultBean.setCode(CodeConstant.CODE_MOUDLE_RELATION + CodeConstant.CODE_RELATION_GETVIDEO);
                resultBean.setData("");
                resultBean.setMsg(ResultConstant.MSG_COMMON_DATABASE_ERE);
                logger.error(resultBean);
                logger.error(e.getMessage());
            }
        }
        return resultBean;
    }

    /**
     * 批量删除数据
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "delVideo")
    @ResponseBody
    public ResultBean delAccVideoRelation(HttpServletRequest request){
        ResultBean resultBean = new ResultBean();
        String sToken = request.getParameter(RequestConstant.REQUEST_TOKEN)!= null?request.getParameter(RequestConstant.REQUEST_TOKEN): "";
        if(sToken.equals("")){
            try {
                RedisOperationService redisOperationService = new RedisOperationService();
                if(!redisOperationService.exitToken(sToken)){
                    resultBean.setStatus(false);
                    resultBean.setCode(CodeConstant.CODE_MOUDLE_RELATION + CodeConstant.CODE_RELATION_DELVIDEO);
                    resultBean.setData("");
                    resultBean.setMsg(ResultConstant.MSG_TOKEN_ERR);
                    logger.info(resultBean);
                    return resultBean;
                }
                String sId = request.getParameter(RequestConstant.REQUEST_CURID)!= null?request.getParameter(RequestConstant.REQUEST_CURID): "";
                String sIds = request.getParameter(RequestConstant.REQUEST_CURID)!= null?request.getParameter(RequestConstant.REQUEST_CURID): "";

                RelationVideoInfoService  relationVideoInfoService = new RelationVideoInfoService();
                if(!sIds.equals("")){
                    List<String> idList = Arrays.asList(sIds.split(","));
                    relationVideoInfoService.deleteByList(idList);
                }else {
                    AccVideo accVideo = new AccVideo();
                    accVideo.setIsDelete((byte)0);
                    if(!sId.equals("")) {
                        accVideo.setAccId(Long.valueOf(sId));
                        relationVideoInfoService.delete(accVideo);
                    }
                }

                redisOperationService.destroy();

                resultBean.setStatus(true);
                resultBean.setCode(CodeConstant.CODE_MOUDLE_RELATION + CodeConstant.CODE_RELATION_DELVIDEO);
                resultBean.setData("");
                resultBean.setMsg(ResultConstant.MSG_COMMON_SUCCESS);
                logger.info(resultBean);
            } catch (Exception e) {
                e.printStackTrace();
                resultBean.setStatus(false);
                resultBean.setCode(CodeConstant.CODE_MOUDLE_RELATION + CodeConstant.CODE_RELATION_DELVIDEO);
                resultBean.setData("");
                resultBean.setMsg(ResultConstant.MSG_COMMON_DATABASE_ERE);
                logger.error(resultBean);
                logger.error(e.getMessage());
            }
        }
        return resultBean;
    }

}
