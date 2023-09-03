package com.example.demo.Zsample.demoController;

import com.example.demo.Zsample.commonModel.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;

@RequestMapping("/students")
@RestController
@Validated  //配合 GetMapping 的 @Max, Validation類型可以上網查詢
public class StudentController {

    // @Valid 使此變數去驗證object內, 每一個成員的限制, 例如@NotNull...等, 沒有加的話, 即使class有限制spring boot也不會檢查
    @PostMapping()
    public String create(@RequestBody @Valid Student student) {
        return "執行資料庫create";
    }

    @PutMapping("/{studentId}")
    public String update(@PathVariable Integer studentId, @RequestBody Student student) {
        return "執行資料庫update";
    }

    // @studentId 需 <= 100, 且必須在該class加上 @Validated才會生效
    @GetMapping("/{studentId}")
    public String select(@PathVariable @Max(100) Integer studentId) {
        return "執行資料庫select";
    }

    @DeleteMapping("/{StudentId}")
    public String delete(@PathVariable Integer StudentId) {
        return "執行資料庫delete";
    }

    // 示範ResponseEntity<?> ?表示可以回傳任意類型
    @GetMapping()
    public ResponseEntity<?> res() {
        Student student = new Student();
        return ResponseEntity.status(HttpStatus.OK).body(student);
    }
}
