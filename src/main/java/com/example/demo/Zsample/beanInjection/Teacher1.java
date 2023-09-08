package com.example.demo.Zsample.beanInjection;

import com.example.demo.Zsample.jdbc.Printer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

//beanInjection的三種bean注入方式, 效果都一樣

//第一種注入方式 Field Injection (變數注入)
//優點:簡單好上手
//缺點:容易注入過多bean進來
@Component
public class Teacher1 {

    @Autowired
    @Qualifier("sonyPrinter")
    private Printer printer;

    public void teach() {
        printer.print("I'm a teacher.");
    }

}
