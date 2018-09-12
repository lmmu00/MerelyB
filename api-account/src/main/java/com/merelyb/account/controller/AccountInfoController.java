package com.merelyb.account.controller;

import com.merelyb.bean.acc.AccInfo;
import com.merelyb.bean.ResultBean;
import com.merelyb.bean.centre.UserInfo;
import com.merelyb.constant.CodeConstant;
import com.merelyb.constant.RequestConstant;
import com.merelyb.constant.ResultConstant;
import com.merelyb.service.acc.AccountInfoService;
import com.merelyb.service.redis.RedisOperationService;
import com.merelyb.utils.crypt.CryptUtils;
import com.merelyb.utils.json.JsonUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @项目: Merelyb
 * @包: com.merelyb.account.controller
 * @作者: LiM
 * @创建时间: 2018-08-03 14:34
 * @Description: Account 信息
 */
@Controller
@RequestMapping("/Acc/Info")
public class AccountInfoController {

    private Logger logger = LogManager.getLogger(this.getClass());
    /**
     * 用户注册页面
     *
     * @return
     */
    @RequestMapping("/addAccPage")
    public String getAddAccPage() {
        return "addAccPage";
    }

    /**
     * 注册用户
     *
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/addAcc")
    @ResponseBody
    public ResultBean addAcc(HttpServletRequest request) {
        ResultBean resultBean = new ResultBean();
        String sUser = request.getParameter(RequestConstant.ACCOUNT_ADDACC_USER) != null ? request.getParameter(RequestConstant.ACCOUNT_ADDACC_USER) : "";
        String sPwd = request.getParameter(RequestConstant.ACCOUNT_ADDACC_PWD) != null ? request.getParameter(RequestConstant.ACCOUNT_ADDACC_PWD) : "";
        logger.info("sUser: " + sUser + ", sPwd: " + sPwd);
        if (sUser.equals("") || sPwd.equals("")) {
            resultBean.setStatus(false);
            resultBean.setCode(CodeConstant.CODE_MOUDLE_ACCOUNT + CodeConstant.CODE_ACCOUNT_ADDACC);
            resultBean.setData("");
            resultBean.setMsg(ResultConstant.MSG_COMMON_DATA_NULL);
            logger.info(resultBean);
            return resultBean;
        }
        //密码需加base64解码
        try {
            Date dtDate = new Date();
            CryptUtils cryptUtils = new CryptUtils();
            sPwd = cryptUtils.Encrypt(sPwd, dtDate);
            AccInfo accInfo = new AccInfo();
            accInfo.setAccUser(sUser);
            accInfo.setIsDelete((byte) 0);
            AccountInfoService accountInfoService = new AccountInfoService();
            List<AccInfo> accInfoList = accountInfoService.select(accInfo);
            logger.info(JsonUtils.objs2Json(accInfoList));
            if (accInfoList != null && accInfoList.size() > 0) {
                resultBean.setStatus(false);
                resultBean.setCode(CodeConstant.CODE_MOUDLE_ACCOUNT + CodeConstant.CODE_ACCOUNT_ADDACC);
                resultBean.setData("");
                resultBean.setMsg(ResultConstant.MSG_ACCOUNY_LOGIN_HAD);
                logger.info(resultBean);
                return resultBean;
            }
            accInfo.setAccPwd(sPwd);
            accInfo.setCreateTime(dtDate);
            accInfo.setAccStatus((byte) 0);
            int iReturn = accountInfoService.insert(accInfo);
            if(iReturn == -1 || iReturn == 0){
                resultBean.setStatus(false);
                resultBean.setCode(CodeConstant.CODE_MOUDLE_ACCOUNT + CodeConstant.CODE_ACCOUNT_ADDACC);
                resultBean.setData("");
                resultBean.setMsg(ResultConstant.MSG_COMMON_DATA_ADD_ERR);
            }
            resultBean.setStatus(true);
            resultBean.setCode(CodeConstant.CODE_MOUDLE_ACCOUNT + CodeConstant.CODE_ACCOUNT_ADDACC);
            resultBean.setData("");
            resultBean.setMsg(ResultConstant.MSG_COMMON_DATA_ADD);
        } catch (Exception e) {
            e.printStackTrace();
            resultBean.setStatus(false);
            resultBean.setCode(CodeConstant.CODE_MOUDLE_ACCOUNT + CodeConstant.CODE_ACCOUNT_ADDACC);
            resultBean.setData("");
            resultBean.setMsg(ResultConstant.MSG_COMMON_DATABASE_ERE);
            logger.error(resultBean);
            logger.error(e.getMessage());
            return resultBean;
        }
        logger.info(resultBean);
        return resultBean;
    }

    /**
     * 用户登陆
     *
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "accLogin")
    @ResponseBody
    public ResultBean accLogin(HttpServletRequest request) {
        ResultBean resultBean = new ResultBean();
        String sUser = request.getParameter(RequestConstant.ACCOUNT_ADDACC_USER) != null ? request.getParameter(RequestConstant.ACCOUNT_ADDACC_USER) : "";
        String sPwd = request.getParameter(RequestConstant.ACCOUNT_ADDACC_PWD) != null ? request.getParameter(RequestConstant.ACCOUNT_ADDACC_PWD) : "";
        logger.info("sUser: " + sUser + ", sPwd: " + sPwd);
        if (sUser.equals("") || sPwd.equals("")) {
            resultBean.setStatus(false);
            resultBean.setCode(CodeConstant.CODE_MOUDLE_ACCOUNT + CodeConstant.CODE_ACCOUNT_LOGIN);
            resultBean.setData("");
            resultBean.setMsg(ResultConstant.MSG_COMMON_DATA_NULL);
            logger.info(resultBean);
            return resultBean;
        }
        AccInfo accInfo = new AccInfo();
        accInfo.setAccUser(sUser);
        accInfo.setIsDelete((byte) 0);
        RedisOperationService redisOperationService = null;
        try {
            AccountInfoService accountInfoService = new AccountInfoService();
            List<AccInfo> accInfoList = accountInfoService.select(accInfo);
            if (accInfoList.size() == 0 || accInfoList == null) {
                resultBean.setStatus(false);
                resultBean.setCode(CodeConstant.CODE_MOUDLE_ACCOUNT + CodeConstant.CODE_ACCOUNT_LOGIN);
                resultBean.setData("");
                resultBean.setMsg(ResultConstant.MSG_ACCOUNT_LOGIN_NULL);
                logger.info(resultBean);
                return resultBean;
            }
            logger.info(JsonUtils.objs2Json(accInfoList));
            accInfo = (AccInfo) accInfoList.get(0);
            CryptUtils cryptUtils = new CryptUtils();
            String sEbPwd = cryptUtils.Decrypt(accInfo.getAccPwd(), accInfo.getCreateTime());
            if (!sEbPwd.equals(sPwd)) {
                resultBean.setStatus(false);
                resultBean.setCode(CodeConstant.CODE_MOUDLE_ACCOUNT + CodeConstant.CODE_ACCOUNT_LOGIN);
                resultBean.setData("");
                resultBean.setMsg(ResultConstant.MSG_ACCOUNT_LOGIN_ERR);
                logger.info(resultBean);
                return resultBean;
            }
            //token 放到redis
            String sToken = cryptUtils.DecryptBase64(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + String.valueOf(accInfo.getAccId()));
            redisOperationService = new RedisOperationService();
            //权限添加
            List<String> roleList = new ArrayList<>();
            roleList.add(accInfo.getAccUser());
            UserInfo userInfo = new UserInfo();
            userInfo.setAccess(roleList);
            userInfo.setUserName(accInfo.getAccUser());
            userInfo.setUserId(String.valueOf(accInfo.getAccId()));
            redisOperationService.addNewToken(JsonUtils.obj2Json(userInfo), sToken);
            redisOperationService.setTokenTime(sToken, RequestConstant.ITOKENVAILD);
            redisOperationService.destroy();

            resultBean.setStatus(true);
            resultBean.setCode(CodeConstant.CODE_MOUDLE_ACCOUNT + CodeConstant.CODE_ACCOUNT_LOGIN);
            resultBean.setData(sToken); //需添加token
            resultBean.setMsg(ResultConstant.MSG_COMMON_SUCCESS);
        } catch (Exception e) {
            if(redisOperationService != null) redisOperationService.destroy();
            e.printStackTrace();
            resultBean.setStatus(false);
            resultBean.setCode(CodeConstant.CODE_MOUDLE_ACCOUNT + CodeConstant.CODE_ACCOUNT_LOGIN);
            resultBean.setData("");
            resultBean.setMsg(ResultConstant.MSG_COMMON_DATABASE_ERE);
            logger.error(resultBean);
            logger.error(e.getMessage());
            return resultBean;
        }
        logger.info(resultBean);
        return resultBean;
    }
}
