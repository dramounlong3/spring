package com.example.demo.Zsample.resttemplate;

import com.example.sqlservertest.model.ChannelMsgtype;
import com.example.sqlservertest.service.RestTemplateService;
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

    @GetMapping("/template")
    public ResponseEntity<?> list() {
        ChannelMsgtype[] restTemplateArray = restTemplateService.list();
        return ResponseEntity.status(200).body(restTemplateArray);
    }

    @GetMapping("/templateWithParam")
    public ResponseEntity<?> templateWithParam() {
        ChannelMsgtype[] restTemplateArray = restTemplateService.getForObjectWithParam();
        return ResponseEntity.status(200).body(restTemplateArray);
    }
}
