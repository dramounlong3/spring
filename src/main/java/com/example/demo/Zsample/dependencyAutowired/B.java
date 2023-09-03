package com.example.demo.Zsample.dependencyAutowired;

import com.example.demo.Zsample.dependencyAutowired.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class B {

    @Autowired
    private A a;
}
