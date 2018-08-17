package com.merelyb.frame;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @项目: Merelyb
 * @包: com.merelyb.frame
 * @作者: LiM
 * @创建时间: 2018-08-03 15:18
 * @Description: 后端框架
 */
public class ServletInitializer extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(FrameAdminApplication.class);
    }
}
