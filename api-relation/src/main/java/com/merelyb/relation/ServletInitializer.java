package com.merelyb.relation;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @项目: Merelyb
 * @包: com.merelyb.relation
 * @作者: LiM
 * @创建时间: 2018-09-04 20:32
 * @Description: ${Description}
 */
public class ServletInitializer extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(RelationApplication.class);
    }
}
