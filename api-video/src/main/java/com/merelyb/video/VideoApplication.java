package com.merelyb.video;

/**
 * @项目: Merelyb
 * @包: com.merelyb.video
 * @作者: LiM
 * @创建时间: 2018-09-03 16:15
 * @Description: ${Description}
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class VideoApplication {
        public static void main(String[] args) {
        SpringApplication.run(VideoApplication.class, args);
        }
}
