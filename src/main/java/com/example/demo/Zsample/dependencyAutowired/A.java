package com.example.demo.Zsample.dependencyAutowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class A {

    // 和class B循環依賴, 實測還是可以啟動project, 但建議不要這樣寫
    @Autowired
    private B b;
}
