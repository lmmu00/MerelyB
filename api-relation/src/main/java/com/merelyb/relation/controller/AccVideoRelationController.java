package com.merelyb.relation.controller;

import com.merelyb.bean.ResultBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

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

    @RequestMapping(method = RequestMethod.POST, value = "newVideo")
    @ResponseBody
    public ResultBean addAccVideoRelation(HttpServletRequest request){
        ResultBean resultBean = new ResultBean();
        return resultBean;
    }
}
