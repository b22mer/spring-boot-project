package com.ssafy.home;

import com.ssafy.home.interceptor.MyInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class WhereIsMyHouseApplication  {

    public static void main(String[] args) {
        SpringApplication.run(WhereIsMyHouseApplication.class, args);
    }


}
