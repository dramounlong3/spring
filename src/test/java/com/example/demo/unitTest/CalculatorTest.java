package com.example.demo.unitTest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    //配對main底下的unitTest.Calculator class
    //Test表示單元測試, 可使用在單一方法或單一API, 避免單元測試須等另外一個測試完成才能做
    @Test
    void add() {
        Calculator calculator = new Calculator();
        //例如 當calculator.add的方法, 被改動成錯誤的邏輯時, 就會造成result的結果不會是100
        int result = calculator.add(99, 1);
        //斷言(我認為)相等 表示 100 一定會 等於 result的值, 若100 != result, 則表示add方法邏輯有錯, 最終可以避免其他人異動到add時導致邏輯錯誤
        assertEquals(100, result);
    }
}