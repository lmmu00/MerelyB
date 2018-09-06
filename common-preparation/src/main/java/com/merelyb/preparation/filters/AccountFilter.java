package com.merelyb.preparation.filters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @项目: Merelyb
 * @包: com.merelyb.preparation.filters
 * @作者: LiM
 * @创建时间: 2018-09-06 10:35
 * @Description: account过滤器
 */
public class AccountFilter implements Filter{

    private Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long start = System.currentTimeMillis();
        logger.info("------------我是过滤器AccountFilter(begin)-----------------");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        logger.info(request.getRequestURI());
        //加判断
        filterChain.doFilter(servletRequest, servletResponse);
        logger.info("消耗时间：" + (System.currentTimeMillis() - start));
        logger.info("------------我是过滤器AccountFilter(end)-----------------");
    }

    @Override
    public void destroy() {

    }
}
