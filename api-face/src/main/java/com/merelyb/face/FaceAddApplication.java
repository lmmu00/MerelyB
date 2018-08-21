package com.merelyb.face;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @项目: Merelyb
 * @包: com.merelyb.face
 * @作者: LiM
 * @创建时间: 2018-08-21 13:51
 * @Description: ${Description}
 */
@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class FaceAddApplication {
    public static void main(String[] args) {
        SpringApplication.run(FaceAddApplication.class, args);
    }
}
