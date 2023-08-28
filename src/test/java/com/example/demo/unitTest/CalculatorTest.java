package com.example.demo.unitTest;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    //配對main底下的unitTest.Calculator class
    //Test表示單元測試, 可使用在單一方法或單一API, 避免單元測試須等另外一個測試完成才能做
    //Test方法不能有回傳值也不能有參數
    @Test
    void add() {
        Calculator calculator = new Calculator();
        //例如 當calculator.add的方法, 被改動成錯誤的邏輯時, 就會造成result的結果不會是100
        int result = calculator.add(99, 1);
        //斷言(我認為)相等 表示 100 一定會 等於 result的值, 若100 != result, 則表示add方法邏輯有錯, 最終可以避免其他人異動到add時導致邏輯錯誤
        assertEquals(100, result);
        assertNotNull(result);
        assertTrue(result > 1);
        assertFalse(result < 999999999);
    }

    @DisplayName("測試方法改為以此名稱顯示")
    @Test
    void divide() {
        Calculator calculator = new Calculator();
        assertThrows(ArithmeticException.class, () -> {
                calculator.divide(1,0);
        });
    }

    //@Disabled可以取消該單元測試
    @Disabled
    @Test
    void multiple() {
        Calculator calculator = new Calculator();
        String str = null;
        assertNull(str);
    }
}