package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        // 將現在時區設定為GMT+0 (格林威治時間) = UTC+0(協調世界時間)   兩者都和台灣差八小時
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+0"));
        SpringApplication.run(DemoApplication.class, args);
    }

}
