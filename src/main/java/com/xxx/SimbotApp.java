package com.xxx;

import com.dtflys.forest.springboot.annotation.ForestScan;
import lombok.extern.slf4j.Slf4j;
import love.forte.simbot.spring.autoconfigure.EnableSimbot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author mpj
 * @version V1.0
 * @date 2022/2/23 12:17
 * @since jdk1.8
 **/
@EnableSimbot
@EnableScheduling
@EnableConfigurationProperties
@Slf4j
@SpringBootApplication
@ForestScan(basePackages = "com.xxx.client")
public class SimbotApp {
	public static void main(String[] args) {
		SpringApplication.run(SimbotApp.class, args);
		log.info("机器人启动了");
	}
}
