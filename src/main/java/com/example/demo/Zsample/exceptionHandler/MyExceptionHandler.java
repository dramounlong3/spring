package com.example.demo.Zsample.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// @ControllerAdvice 將class變成bean, 且在其內部可以使用@ExceptionHandler
// 可以統一管理Exception
@ControllerAdvice
public class MyExceptionHandler {
    // ====================================================================
    // java的錯誤最上層是throwable, 再來是error 和 Exception
    // 自定義的exception最多只需要寫到Exception就好, 因為Error的錯誤人為也沒有辦法處理
    // ====================================================================

    // @ExceptionHandler 只能加在方法上, 將此方法設定為可以抓到對應的Exception
    // 一定要搭配@ControllerAdvice使用
    // 通常會在此根據自定義的方式回傳status和訊息
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handler (RuntimeException exception) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(exception.getMessage());
    }

    // 當此handler沒有寫的時候, spring會自動往exception繼承的類別尋找對應handler
    // Illegal Argument的父層class為RuntimeException, 所以沒寫的話會找到上面的function
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handler (IllegalArgumentException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }
}
