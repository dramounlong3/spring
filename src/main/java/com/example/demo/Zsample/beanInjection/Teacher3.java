package com.example.demo.Zsample.beanInjection;

import com.example.demo.Zsample.jdbc.Printer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


//第三種注入方式 Setter Injection (Setter注入)
@Component
public class Teacher3 {

    private Printer printer;

    @Autowired
    public void setPrinter(@Qualifier("sonyPrinter") Printer printer) {
        this.printer = printer;
    }

    public void teach() {
        printer.print("I'm a teacher");
    }
}
