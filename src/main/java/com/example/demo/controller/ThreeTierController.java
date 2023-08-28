package com.example.demo.controller;

import com.example.demo.model.ThreeTierStudent;
import com.example.demo.service.ThreeTierStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ThreeTierController {

    // @注入Service的Bean
    @Autowired
    ThreeTierStudentService threeTierStudentService;

    @GetMapping("/three/students/{studentId}")
    public ResponseEntity<?> select(@PathVariable Integer studentId) {
        //呼叫service
        List<ThreeTierStudent> student = threeTierStudentService.getById(studentId);
        return ResponseEntity.status(200).header("returnHeader","testReturnHeader").body(student);
    }

    @PostMapping("/three/students")
    public ResponseEntity<?> insert(@Valid @RequestBody ThreeTierStudent threeTierStudent) {
        Integer id = threeTierStudentService.insertStudent(threeTierStudent);
        return ResponseEntity.status(201).body(id);
    }

    @DeleteMapping("three/students/{studentId}")
    public ResponseEntity<?> delete(@PathVariable Integer studentId) {
        threeTierStudentService.deleteById(studentId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
