package com.liang.demo.logback.appender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 * Created by haofeiL on 2017/6/3.
 */
@SpringBootApplication
public class LogbackApdApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        System.out.println("++++++++LogbackApdApplication++++++");

        SpringApplication.run(LogbackApdApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(LogbackApdApplication.class);
    }
}
