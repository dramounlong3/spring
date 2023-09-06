package com.example.demo.Zsample.resttemplate;

import com.example.demo.Zsample.commonModel.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping("/templatePostEntity")
    public ResponseEntity<?> templatePostEntity(@RequestBody Student student) {
        Student responseEntity = restTemplateService.postForEntity(student);
        return ResponseEntity.status(200).body(responseEntity);
    }

    @GetMapping("/templatetExchangeGet")
    public ResponseEntity<?> templatetExchangeGet() {
        ResponseEntity<ChannelMsgtype[]> result = restTemplateService.exchangeGet();
        return ResponseEntity.status(200).body(result.getBody());
    }

    @PostMapping("/templatetExchangePost")
    public ResponseEntity<?> templatetExchangePost(@RequestBody ChannelMsgtype channelMsgtype) {
        ResponseEntity<String> result = restTemplateService.exchangePost(channelMsgtype);
        return ResponseEntity.status(200).body(result.getBody());
    }
}
