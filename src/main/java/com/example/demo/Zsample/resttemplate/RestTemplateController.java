package com.example.demo.Zsample.resttemplate;

import com.example.demo.Zsample.commonModel.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class RestTemplateController {

    @Autowired
    RestTemplateService restTemplateService;

//    @GetMapping("/template")
//    public ResponseEntity<?> list() {
//        ChannelMsgtype[] restTemplateArray = restTemplateService.list();
//        return ResponseEntity.status(200).body(restTemplateArray);
//    }

    @GetMapping("/templateWithParam")
    public ResponseEntity<?> templateWithParam() {
        Student student = restTemplateService.getForObjectWithParam();
        return ResponseEntity.status(200).body(student);
    }

    @GetMapping("/templateEntity")
    public ResponseEntity<?> templateEntity() {
        Student student = restTemplateService.getForEntity();
        return ResponseEntity.status(200).body(student);
    }
}
