package com.example.demo;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
public class SonyPrinter implements Printer{
    @Override
    public void print(String message) {
        System.out.println("sonyPrinter: " + message);
    }

    public void myFunction() {
        System.out.println("sonyFunction!!");
        for (int i=0; i<=90000000; i++){
            //do something
        }
    }
}
