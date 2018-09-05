package com.merelyb.relation.interceptor;

import com.merelyb.constant.RequestConstant;
import com.merelyb.service.redis.RedisOperationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @项目: Merelyb
 * @包: com.merelyb.relation.interceptor
 * @作者: LiM
 * @创建时间: 2018-09-05 16:33
 * @Description: 拦截器
 */
public class AccVisitInterceptor implements HandlerInterceptor {

    private Logger logger = LogManager.getLogger(this.getClass());

    /**
     * 添加拦截操作
     *
     * @param request
     * @param response
     * @param handler
     * @return
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        logger.info("------------我是拦截器-----------------");
        String sToken = request.getParameter(RequestConstant.REQUEST_TOKEN);
        try {
            if (sToken != null) {
                RedisOperationService redisOperationService = new RedisOperationService();
                if (redisOperationService.exitToken(sToken)) {
                    redisOperationService.setTokenTime(sToken, RequestConstant.ITOKENVAILD);
                } else {
                    logger.info(request.getParameterMap());
                    logger.info("------------我是拦截器-----------------");
                    return false;
                }
            } else {
                logger.info(request.getParameterMap());
                logger.info("------------我是拦截器-----------------");
                return false;
            }
        } catch (Exception e) {
            logger.info("------------我是拦截器-----------------");
            e.printStackTrace();
            logger.error(e.getMessage());
            return false;
        }
        logger.info("------------我是拦截器-----------------");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }
}
