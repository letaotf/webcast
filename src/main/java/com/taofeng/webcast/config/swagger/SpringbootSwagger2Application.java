package com.taofeng.webcast.config.swagger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * <p>接口文档的启动类</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/1 上午10:18
 * @since V1.0
 */
@SpringBootApplication
@EnableSwagger2
public class SpringbootSwagger2Application {

    public static void main(String[] args){
        SpringApplication.run(SpringbootSwagger2Application.class,args);
    }

}
