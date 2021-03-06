package com.merelyb.relation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @项目: Merelyb
 * @包: com.merelyb.relation
 * @作者: LiM
 * @创建时间: 2018-09-04 20:35
 * @Description: ${Description}
 */
@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@ServletComponentScan("com.merelyb.relation.filters") //新增过滤器
public class RelationApplication {
    public static void main(String[] args) {
        SpringApplication.run(RelationApplication.class, args);
    }
}
