package com.example.demo.Zsample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentJpaController {

    // 注入繼承jap repository的interface
    @Autowired
    StudentJpaRepository studentJpaRepository;

    @PostMapping("/jpa")
    public ResponseEntity<?> insert(@RequestBody StudentJpa studentjpa) {
        studentJpaRepository.save(studentjpa);
        return ResponseEntity.status(201).body("執行jpa 資料庫insert操作");
    }

    @PutMapping("/jpa/{studentId}")
    public ResponseEntity<?> update(@PathVariable Integer studentId, @RequestBody StudentJpa studentJpa) {

        //因為save方法當資料庫沒有此筆資料時會改為執行insert，所以為了確保update能夠不被突然新增額外的數據，故需先判斷此id的紀錄是否存在
        if(studentJpaRepository.findById(studentId) != null) {
            studentJpa.setId(studentId); //必須先設定studentId, 否則會變成新增
            studentJpaRepository.save(studentJpa);
            return ResponseEntity.status(200).body("執行jpa 資料庫update操作");
        } else {
            return  ResponseEntity.status(401).body("因此筆數據不存在，執行jpa 資料庫操作失敗");
        }

    }

    @DeleteMapping("/jpa/{studentId}")
    public ResponseEntity<?> delete(@PathVariable Integer studentId) {
        studentJpaRepository.deleteById(studentId);

        return ResponseEntity.status(200).body("執行 jpa 資料庫delete操作");
    }

    @GetMapping("/jpa/{studentId}")
    public ResponseEntity<?> read(@PathVariable Integer studentId) {

        //沒有找到就回傳null
        StudentJpa studentJpa =  studentJpaRepository.findById(studentId).orElse(null);
        return ResponseEntity.status(200).body(studentJpa);
    }

}
