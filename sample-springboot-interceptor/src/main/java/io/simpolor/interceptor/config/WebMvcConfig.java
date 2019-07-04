package io.simpolor.interceptor.config;

import io.simpolor.interceptor.interceptor.EnrolledStudentInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private EnrolledStudentInterceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor)
                .addPathPatterns("/student/**")
                .excludePathPatterns("/student/totalcount")
                .excludePathPatterns("/student/list");
    }

}
