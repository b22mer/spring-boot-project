package com.ssafy.home;

import com.ssafy.home.interceptor.MemberInterceptor;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
public class ServletInitializer extends SpringBootServletInitializer implements WebMvcConfigurer {

    private static final List<String> interceptorUrlPatterns = Arrays.asList("/user/*");
    private static final List<String> excludeInterceptorUrlPatterns = Arrays.asList("/user/login", "/user/register", "/board", "house");

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WhereIsMyHouseApplication.class);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MemberInterceptor())
                .addPathPatterns(interceptorUrlPatterns)
                .excludePathPatterns(excludeInterceptorUrlPatterns);
    }
}
