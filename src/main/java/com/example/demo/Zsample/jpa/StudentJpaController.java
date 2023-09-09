package com.example.demo.Zsample.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        if (studentJpaRepository.findById(studentId) != null) {
            studentJpa.setId(studentId); //必須先設定studentId, 否則會變成新增
            studentJpaRepository.save(studentJpa);
            return ResponseEntity.status(200).body("執行jpa 資料庫update操作");
        } else {
            return ResponseEntity.status(401).body("因此筆數據不存在，執行jpa 資料庫操作失敗");
        }

    }

    @DeleteMapping("/jpa/{studentId}")
    public ResponseEntity<?> delete(@PathVariable Integer studentId) {
        studentJpaRepository.deleteById(studentId);

        return ResponseEntity.status(200).body("執行 jpa 資料庫delete操作");
    }

    @GetMapping("/jpa/id/{studentId}")
    public ResponseEntity<?> read(@PathVariable Integer studentId) {

        //沒有找到就回傳null
        StudentJpa studentJpa = studentJpaRepository.findById(studentId).orElse(null);
        return ResponseEntity.status(200).body(studentJpa);
    }


    // findByXxx spring boot會根據Xxx產生對應的SQL到資料庫搜尋
    @GetMapping("/jpa/name/{studentName}")
    public ResponseEntity<?> findByName(@PathVariable String studentName) {

        //沒有找到就回傳null
        StudentJpa studentJpa = studentJpaRepository.findByName(studentName).orElse(null);
        return ResponseEntity.status(200).body(studentJpa);
    }

    // findByXxxAndYyy spring boot會根據Xxx, Yyy產生對應的SQL到資料庫搜尋
    @GetMapping("/jpa/id/{studentId}/name/{studentName}")
    public ResponseEntity<?> findByIdAndName(@PathVariable Integer studentId,
                                             @PathVariable String studentName) {
        //沒有找到就回傳null
        //傳進去的參數必須和and的順序一致
        StudentJpa studentJpa = studentJpaRepository.findByIdAndName(studentId, studentName).orElse(null);
        return ResponseEntity.status(200).body(studentJpa);
    }

    // 使用Query的原生SQL方法搜尋
    // 如果要搜尋的pathvariable, 需要以URL encod編碼代替特殊符號, 特殊符號例如 %5b = [   %5d = ]
    // http://localhost:8080/jpa/id/2/todo/%5bwatch movie, dacing%5d
    @GetMapping("/jpa/id/{id}/todo/{todolist}")
    public ResponseEntity<?> useQuery(@PathVariable Integer id
            , @PathVariable String todolist) {
        List<StudentJpa> studentJpaList = studentJpaRepository.useQuery(id, todolist);
        return ResponseEntity.status(200).body(studentJpaList);
    }


    @GetMapping("/jpa/test1")
    public List<String> test1() {
        List<String> stringList = studentJpaRepository.findAllNames();

        for (int i = 0; i < stringList.size(); i++) {
            String name = stringList.get(i);
            System.out.println("name= " + name);
        }
        return stringList;
    }

    @GetMapping("/jap/test2")
    public String test2() {
        List<String> stringList = studentJpaRepository.findAllNames();

        return stringList.stream()
                .filter(x -> x.equals("Xiang"))
                .findFirst()
                .orElse(null);
    }

}
