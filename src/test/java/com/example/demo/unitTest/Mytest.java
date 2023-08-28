package com.example.demo.unitTest;

import org.junit.jupiter.api.*;

public class Mytest {
    //@BeforeEach 在每一個單元測試之前都會先執行一遍
    @BeforeEach
    void BeforeEach() {
        System.out.println("@BeforeEach");
    }

    //@AfterEach 在每一個單元測試之後都會先執行一遍
    @AfterEach
    void AfterEach() {
        System.out.println("@AfterEach");
    }

    //@BeforeAll和@AfterAll方法必須為static, 比較少用, 因為設定為static後無法與bean互動
    //@BeforeAll 在所有單元測試開始之前只會執行一遍
    @BeforeAll
    static void BeforeAll() {
        System.out.println("@BeforeAll");
    }

    //@AfterAll 在所有單元測試開始之前只會執行一遍
    @AfterAll
    static void AfterAll() {
        System.out.println("@AfterAll");
    }

    @Test
    void test1(){
        System.out.println("測試test1");
    }

    @Test
    void test2(){
        System.out.println("測試test2");
    }

}
