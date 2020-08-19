/**
 * Copyright (C) 2018-2020
 * All rights reserved, Designed By www.yixiang.co

 */
package co.zhenxi;

import co.zhenxi.annotation.AnonymousAccess;
import co.zhenxi.utils.SpringContextHolder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gxm
 * @date 2018/11/15 9:20:19
 */
@EnableAsync
@RestController
@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages ={ "co.zhenxi.*.mapper", "co.zhenxi.config"})
public class AppRun {

    public static void main(String[] args) {
        SpringApplication.run(AppRun.class, args);
        System.out.println(
                "              __                  \n" +
                "  __ __ ___  / /  ___   ___       \n" +
                " / // /(_-< / _ \\/ _ \\ / _ \\   \n" +
                " \\_, //___//_//_/\\___// .__/    \n" +
                "/___/                /_/          \n "+

                "\n众包Witkey电商系统启动成功 \n官网：https://www.witkey.com 提供技术支持ﾞ  \n");
    }

    @Bean
    public SpringContextHolder springContextHolder() {
        return new SpringContextHolder();
    }

    @Bean
    public ServletWebServerFactory webServerFactory() {
        TomcatServletWebServerFactory fa = new TomcatServletWebServerFactory();
        fa.addConnectorCustomizers(connector -> connector.setProperty("relaxedQueryChars", "[]{}"));
        return fa;
    }

    /**
     * 访问首页提示
     * @return /
     */
    @GetMapping("/")
    @AnonymousAccess
    public String index() {
        return "Backend service started successfully";
    }
}
