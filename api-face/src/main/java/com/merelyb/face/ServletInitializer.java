package com.merelyb.face;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @项目: Merelyb
 * @包: com.merelyb.face
 * @作者: LiM
 * @创建时间: 2018-08-21 13:50
 * @Description: face++ 相关接口
 */
public class ServletInitializer extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(FaceAddApplication.class);
    }
}
