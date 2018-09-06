package com.merelyb.preparation.interceptors;

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
 * @包: com.merelyb.preparation.interceptors
 * @作者: LiM
 * @创建时间: 2018-09-06 10:37
 * @Description: token拦截器
 */
public class TokenInterceptor implements HandlerInterceptor {

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
        logger.info("------------我是拦截器TokenInterceptor(begin)-----------------");
        String sToken = request.getParameter(RequestConstant.REQUEST_TOKEN);
        try {
            if (sToken != null) {
                RedisOperationService redisOperationService = new RedisOperationService();
                if (redisOperationService.exitToken(sToken)) {
                    redisOperationService.setTokenTime(sToken, RequestConstant.ITOKENVAILD);
                } else {
                    logger.info(request.getParameterMap());
                    logger.info("------------我是拦截器TokenInterceptor(end)-----------------");
                    return false;
                }
            } else {
                logger.info(request.getParameterMap());
                logger.info("------------我是拦截器TokenInterceptor(end)-----------------");
                //需添加白名单
                if(request.getRequestURI().equals("/")) return true;
                return true;
            }
        } catch (Exception e) {
            logger.info("------------我是拦截器TokenInterceptor(end)-----------------");
            e.printStackTrace();
            logger.error(e.getMessage());
            return false;
        }
        logger.info("------------我是拦截器TokenInterceptor(end)-----------------");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }
}
