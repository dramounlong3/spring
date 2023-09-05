package com.example.demo.Zsample.resttemplate;


import com.example.demo.Zsample.commonModel.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class RestTemplateServiceImp implements RestTemplateService {


//    @Override
//    public ChannelMsgtype[] list() {
//
//        RestTemplate restTemplate = new RestTemplate();
//
//        //第一個參數是url
//        //第二個參數是要轉換的object
//        ChannelMsgtype[] channelMsgtypeArray = restTemplate.getForObject("http://localhost:8080/kernel/channelMsgtype", ChannelMsgtype[].class);
//
//        System.out.println("RestTemplate is being called.");
//        return channelMsgtypeArray;
//    }

    @Override
    public Student getForObjectWithParam() {

        RestTemplate restTemplate = new RestTemplate();

        Map<String, Object> queryParamMap = new HashMap<>();
        queryParamMap.put("id",123);
        queryParamMap.put("name","object");
        queryParamMap.put("testid",999);

        //queryParamMap是queryParameter, 就是URL ?後面的參數
        Student student = restTemplate.getForObject("http://localhost:8081/demo/test4?id={id}&name={name}&testId={testid}",
                Student.class,
                queryParamMap
                );

        System.out.println("getForObjectWithParam is being called.");
        return student;
    }


    @Override
    public Student getForEntity() {

        RestTemplate restTemplate = new RestTemplate();

        Map<String, Object> queryParamMap = new HashMap<>();
        queryParamMap.put("id",66);
        queryParamMap.put("name","entity");
        queryParamMap.put("testid",60);

        //queryParamMap是queryParameter, 就是URL ?後面的參數
        ResponseEntity<Student> studentEntity = restTemplate.getForEntity("http://localhost:8081/demo/test4?id={id}&name={name}&testId={testid}",
                Student.class,
                queryParamMap
        );
        //取得status code
        System.out.printf("statusCode= %s\n", studentEntity.getStatusCode());

        //取得body
        Student st = studentEntity.getBody();

        System.out.println("getForObjectWithParam is being called.");
        return st;
    }

}
