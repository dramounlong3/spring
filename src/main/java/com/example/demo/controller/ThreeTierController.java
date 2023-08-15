package com.example.demo.controller;

import com.example.demo.service.ThreeTierStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ThreeTierController {

    // @注入Service的Bean
    @Autowired
    ThreeTierStudentService threeTierStudentService;

    @GetMapping("/three/students/{studentId}")
    public ResponseEntity<?> select(@PathVariable Integer studentId) {
        //呼叫service
        return threeTierStudentService.getById(studentId);
    }

}
