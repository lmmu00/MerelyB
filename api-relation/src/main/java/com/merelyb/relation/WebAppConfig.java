package com.merelyb.relation;

import com.merelyb.preparation.filters.AccountFilter;
import com.merelyb.preparation.interceptors.TokenInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;

/**
 * @项目: Merelyb
 * @包: com.merelyb.relation
 * @作者: LiM
 * @创建时间: 2018-09-05 16:41
 * @Description: ${Description}
 */
@Configuration
public class WebAppConfig implements WebMvcConfigurer {

    /**
     * 拦截器
     * @return
     */
    @Bean
    public HandlerInterceptor tokenInterceptor(){
        return new TokenInterceptor();
    }

    /**
     * 过滤器
     * @return
     */
    @Bean
    public Filter accountFilter(){
        return new AccountFilter();
    }

    @Bean
    public FilterRegistrationBean accVisitFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(accountFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("urlAllow", "");
        registration.setName("accVisitFilter");
        registration.setOrder(1);
        return registration;
    }
        /**
         * 拦截器加入
         * @param registry
         */
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(tokenInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/error")
                .excludePathPatterns("/static/*");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")//设置允许跨域的路径
                .allowedOrigins("*")//设置允许跨域请求的域名
                .allowCredentials(true)//是否允许证书 不再默认开启
                .allowedMethods("GET", "POST", "PUT", "DELETE")//设置允许的方法
                .maxAge(3600);//跨域允许时间
    }
}
