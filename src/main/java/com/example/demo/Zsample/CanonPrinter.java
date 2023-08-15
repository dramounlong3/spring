package com.example.demo.Zsample;

// 此class透過MyConfiguration設定為bean, 等同於在此直接加上@Component
public class CanonPrinter implements Printer{
    @Override
    public void print(String message) {
        System.out.println("CanonPrinter: " + message);
    }
}
