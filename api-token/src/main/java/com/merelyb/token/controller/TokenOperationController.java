package com.merelyb.token.controller;

import com.google.gson.reflect.TypeToken;
import com.merelyb.bean.ResultBean;
import com.merelyb.bean.centre.UserInfo;
import com.merelyb.constant.CodeConstant;
import com.merelyb.constant.RequestConstant;
import com.merelyb.constant.ResultConstant;
import com.merelyb.service.redis.RedisOperationService;
import com.merelyb.utils.json.JsonUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @项目: Merelyb
 * @包: com.merelyb.token.controller
 * @作者: LiM
 * @创建时间: 2018-09-11 10:57
 * @Description: ${Description}
 */
@Controller
@RequestMapping(value = "token")
public class TokenOperationController {

    private Logger logger = LogManager.getLogger(this.getClass());

    /**
     * 获取用户基本信息
     *
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "getUserInfo")
    @ResponseBody
    public ResultBean getUserInfo(HttpServletRequest request) {
        ResultBean resultBean = new ResultBean();
        String sToken = request.getParameter(RequestConstant.REQUEST_TOKEN) != null ? request.getParameter(RequestConstant.REQUEST_TOKEN) : "";
        logger.info(sToken);
        if (sToken.equals("")) {
            resultBean.setStatus(false);
            resultBean.setData("");
            resultBean.setCode(CodeConstant.CODE_MOUDLE_TOKEN + CodeConstant.CODE_TOKEN_GETUSERINFO);
            resultBean.setMsg(ResultConstant.MSG_TOKEN_ERR);
            logger.info(resultBean);
        } else {
            try {
                RedisOperationService redisOperationService = new RedisOperationService();
                if (!redisOperationService.exitToken(sToken)) {
                    resultBean.setStatus(false);
                    resultBean.setData("");
                    resultBean.setCode(CodeConstant.CODE_MOUDLE_TOKEN + CodeConstant.CODE_TOKEN_GETUSERINFO);
                    resultBean.setMsg(ResultConstant.MSG_TOKEN_ERR);
                } else {
                    Map<String, String> mapValue = redisOperationService.getAccFromToken(sToken);
                    UserInfo userInfo = new UserInfo();
                    userInfo.setUserId(mapValue.containsKey(RequestConstant.USERINFO_ID) ? mapValue.get(RequestConstant.USERINFO_ID) : "");
                    userInfo.setUserName(mapValue.containsKey(RequestConstant.USERINFO_NAME) ? mapValue.get(RequestConstant.USERINFO_NAME) : "");
                    userInfo.setAccess(mapValue.containsKey(RequestConstant.USERINFO_ACCESS) ? JsonUtils.json2Objs(mapValue.get(RequestConstant.USERINFO_ID), new TypeToken<List<String>>() {
                    }) : new ArrayList<String>());
                    resultBean.setStatus(true);
                    resultBean.setData(userInfo);
                    resultBean.setCode(CodeConstant.CODE_MOUDLE_TOKEN + CodeConstant.CODE_TOKEN_GETUSERINFO);
                    resultBean.setMsg("");
                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.error(e.getMessage());
                resultBean.setStatus(false);
                resultBean.setData("");
                resultBean.setCode(CodeConstant.CODE_MOUDLE_TOKEN + CodeConstant.CODE_TOKEN_GETUSERINFO);
                resultBean.setMsg(ResultConstant.MSG_COMMON_SYSTEM_ERR);
                logger.info(resultBean);
                return resultBean;
            }
        }
        logger.info(resultBean);
        return resultBean;
    }
}
