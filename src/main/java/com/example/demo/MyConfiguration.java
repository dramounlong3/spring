package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 設定Spring用的class, class名稱不重要
@Configuration
public class MyConfiguration {

    // 在Spring創建bean, 只能加在有Configuration的class底下的方法
    @Bean
    Printer myPrinter() {
        //Spring容器中會有一個Printer類型且名為myPrinter的CanonPrinter的實例
        return new CanonPrinter();
    }
}
