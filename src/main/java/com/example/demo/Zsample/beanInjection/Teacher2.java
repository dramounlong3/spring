package com.example.demo.Zsample.beanInjection;

import com.example.demo.Zsample.jdbc.Printer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

//Spring官方推薦
//第二種注入方式 Constructor Injection (建構子注入)
//優點: 容易釐清依賴關係、測試，變數可為final
//缺點: 寫起來較冗長(可用Lomobok簡化)
@Component
public class Teacher2 {


    private final Printer printer;

    @Autowired
    public Teacher2(@Qualifier("sonyPrinter") Printer printer){
        this.printer = printer;
    }

    public void teach() {
        printer.print("I'm a teacher");
    }

}
