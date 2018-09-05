package com.merelyb.relation.filters;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @项目: Merelyb
 * @包: com.merelyb.relation
 * @作者: LiM
 * @创建时间: 2018-09-05 16:18
 * @Description: 过滤器
 */
@WebFilter(urlPatterns = "/relation", filterName = "accVisitFilter")
public class AccVisitFilter implements Filter {

    private Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public void init(FilterConfig filterConfig) throws  ServletException{

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long start = System.currentTimeMillis();
        logger.info("------------我是过滤器-----------------");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        logger.info(request.getRequestURI());
        //加判断
        filterChain.doFilter(servletRequest, servletResponse);
        logger.info("消耗时间：" + (System.currentTimeMillis() - start));
        logger.info("------------我是过滤器-----------------");
    }

    @Override
    public void destroy() {

    }
}
