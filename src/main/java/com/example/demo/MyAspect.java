package com.example.demo;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Date;

// @Aspect切面  AOP: Aspect Oriented Programming 切面物件導向: 將共用的方法寫到bean的切面之中
// 一定要伴隨著@Component一起寫
@Aspect
@Component
public class MyAspect {

    //在切入點SonyPrinter.print()執行之前執行
    @Before("execution (* com.example.demo.SonyPrinter.print(..))")
    public void before() {
        System.out.println("I'm before.");
    }

    //在切入點SonyPrinter.print()執行之後執行
    @After("execution (* com.example.demo.SonyPrinter.print(..))")
    public void after() {
        System.out.println("I'm after.");
    }

    //在切入點SonyPrinter.myFunction()執行的前後都執行, 但因myFunction非interface裡面的function不確定是否因為這樣所以無法透過左方的method連結到
    @Around("execution (* com.example.demo.SonyPrinter.myFunction(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("開始計時...");
        Date start = new Date();

        // 執行切入點的方法(必填)
        Object obj = pjp.proceed();

        Date end = new Date();

        long time = end.getTime() - start.getTime();
        System.out.println("總共執行了: " + time + "ms");

        return obj;

    }

}
