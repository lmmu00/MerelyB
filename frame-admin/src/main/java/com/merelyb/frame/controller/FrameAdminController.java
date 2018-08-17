package com.merelyb.frame.controller;

import com.merelyb.constant.RequestConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @项目: Merelyb
 * @包: com.merelyb.frame.controller
 * @作者: LiM
 * @创建时间: 2018-08-03 15:24
 * @Description: ${Description}
 */
@Controller
public class FrameAdminController {

    /**
     * 登录
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public String getLoginPage(HttpServletRequest request){

        return "index";
    }

    @RequestMapping(value = "/")
    public String getIndexPage(HttpServletRequest request){
        String sToken = request.getHeader(RequestConstant.REQUEST_TOKEN) != null? request.getHeader(RequestConstant.REQUEST_TOKEN): "";
        //需添加验证token是否正确
        if(sToken.equals("")){
            return "login";
        }
        return "index";
    }
}
