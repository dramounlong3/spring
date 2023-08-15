package com.example.demo.Zsample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 設定Spring用的class, class名稱不重要
// WebMvcConfigurer是和Interceptor相關才需實作的
@Configuration
public class MyConfiguration implements WebMvcConfigurer {

    @Autowired
    private MyInterceptor myInterceptor;


    // 在Spring創建bean, 只能加在有Configuration的class底下的方法
    @Bean
    Printer myPrinter() {
        //Spring容器中會有一個Printer類型且名為myPrinter的CanonPrinter的實例
        return new CanonPrinter();
    }

    // 設定哪些路徑會被先進到攔截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor).addPathPatterns("/**");
    }
}
