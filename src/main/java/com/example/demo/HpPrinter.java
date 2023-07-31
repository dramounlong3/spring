package com.example.demo;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

// Component => Ioc: Inversion of Control
// 讓Spring管理HpPrinter的生命週期
// 並建立bean: HpPrinter hpPrinter = new() HpPrinter
@Scope("session") //Controller也必需設定為相同的Scope否則會報錯, 同一台電腦同一種瀏覽器算同一個session所以共用一個beam, 若設定為request則每次重新整理都會重建一個bean
@Component
public class HpPrinter implements Printer, InitializingBean {

    // 直接初始化和下面方法1~3的效果是一樣的, 差別在於bean可以於bean中取得其他於bean的數值, 建構子通常用來做複雜計算的初始化
    // 也就是說如果像此例子只是單純要設定count=5, 那用直接初始化就好, 要複雜計算用constructor(方法3), 要使用到其他bean的內容就用PostConstruct(方法1)或InitializingBean(方法2)
    // 本範例雖然全部都保留, 但其實只是做了四次初始化的動作, 所以依實際情況擇一使用就好
    private Integer count; //= 5;

    // Value: 在bean or configuration底下的變數讀取resources底下的application.properties(key, value); application.yml(another key value)
    // 如果設定檔沒有myEquipment.name則會預設為Canon
    @Value("${myEquipment.name:Scanner}")
    private  String name;

    @Value("${myEquipment.type:Canon}")
    private  String type;

    @Value("${myEquipment.extraVar:defaultValue}")
    private  String extraVar;


    // 方法1較常用
    // 方法1: 透過annotation, 在bean裡面初始化 count
    @PostConstruct
    public void init() {
        count = 5;
    }

    // 方法2: 透過implement IntializingBean裡面的方法初始化 count, 和上述方法擇一使用就好
    @Override
    public void afterPropertiesSet() throws Exception {
        count = 5;
    }

    // 方法3: 透過建構子初始化, 和上述方法擇一使用就好, 與方法1, 2差別在於, 透過bean初始化可以取得其他bean的內容來做初始化
    public HpPrinter() {
        this.count = 5;
    }

    @Override
    public void print(String message) {
        count--;
        System.out.println("HpPrinter: " + message);
        // sout可以快速代出System.out.println
        System.out.println("HpPrinter剩餘次數: " + count);
        System.out.println("name= " + this.type + ", type= " + this.type + ", extraVal= " + this.extraVar);
    }


}
