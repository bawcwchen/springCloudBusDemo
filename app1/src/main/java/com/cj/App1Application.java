package com.cj;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wb-cj189958
 * @date 2018/6/6 19:33
 */
@SpringBootApplication
@RestController
@EnableEurekaClient
@RefreshScope
public class App1Application {

    public static void main(String[] args) {
        SpringApplication.run(App1Application.class, args);
    }

    /**
     * config server连接的远程资源库和本地application.properties都提供
     * 优先取config server上配置app1-config1.properties
     * 若没有，则取application.properties配置
     */
    @Value("${username}")
    private String username;

    /**
     * config server没有提供该配置，取application.properties
     */
    @Value("${year}")
    private Integer year;

    /**
     * 取config server上配置app1-config2.properties,注意这里是资源库上另外一个配置文件
     */
    @Value("${address}")
    private String address;

    /**
     * 从application.properties, application.yml, application-*.properties取全局配置。
     */
    @Value("${greeting}")
    private String greeting;

    @RequestMapping(value = "/hello")
    public String hello() {
        return username + ":" + year + ":" + address + ":" + greeting;
    }
}
