package com.merelyb.frame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @项目: Merelyb
 * @包: com.merelyb.frame
 * @作者: LiM
 * @创建时间: 2018-08-03 15:17
 * @Description: 后端框架
 */
@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class FrameAdminApplication {
    /**
     * 启动类
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(FrameAdminApplication.class, args);
    }
}
