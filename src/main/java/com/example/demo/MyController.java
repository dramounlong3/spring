package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// RestController也會讓此class建立bean於Spring
@Scope("session") //配合HpPrinter設定
@RestController
public class MyController {

    // Autowired => DI: Dependency injection, 依賴注入
    // 讓MyController可以取得Spring中HpPrinter的實例hpPrinter
    // 想像MyController要依賴hpPrinter, 所以可以當成是將MyController的依賴注入到此class
    @Autowired
    //當有兩個bean屬於Printer類型的class，則需使用Qualified選擇需要的bean
    @Qualifier("myPrinter")
    private Printer printer;

    @Autowired
    @Qualifier("hpPrinter")
    private Printer hpPrinter;

    @Autowired
    @Qualifier("sonyPrinter")
    private  Printer sonyPrinter;

    @Autowired
    private SonyPrinter sony;


    @RequestMapping("/test1")
    public String test() {
        printer.print("hello myPrinter");
        return "myPrinter";
    }

    //測試bean variable initialize
    @RequestMapping("/test2")
    public String test2() {
        hpPrinter.print("hello HpPrinter");
        return "HpPrinter";
    }

    @RequestMapping("/test3")
    public String test3() {
        sonyPrinter.print("hello SonyPrinter");
        sony.myFunction();
        return "SonyPrinter";
    }
}
