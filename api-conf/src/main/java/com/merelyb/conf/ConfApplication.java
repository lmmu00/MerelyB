package com.merelyb.conf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @项目: Merelyb
 * @包: com.merelyb.conf
 * @作者: LiM
 * @创建时间: 2018-07-30 20:58
 * @Description: 配置文件服务器
 */
@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class ConfApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfApplication.class, args);
    }
}
