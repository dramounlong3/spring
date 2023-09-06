package com.example.demo.Zsample.resttemplate;


import com.example.demo.Zsample.commonModel.Student;
import org.springframework.http.*;
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


    @Override
    public Student postForEntity(Student student) {

        RestTemplate restTemplate = new RestTemplate();

        //queryParamMap是queryParameter, 就是URL ?後面的參數
        ResponseEntity<Student> studentEntity = restTemplate.postForEntity("http://localhost:8081/demo/sql/student",
                student,
                Student.class
        );
        //取得status code
        System.out.printf("statusCode= %s\n", studentEntity.getStatusCode());

        //取得body
        Student st = studentEntity.getBody();

        System.out.println("postForEntity is being called.");
        return st;
    }


    @Override
    public ResponseEntity<ChannelMsgtype[]> exchangeGet() {
        RestTemplate restTemplate = new RestTemplate();

        //設定header
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("header1", "myheader");

        //設定request的entity
        HttpEntity httpEntity = new HttpEntity<>(httpHeaders);

        Map<String, Object> queryParaMap = new HashMap<>();
        queryParaMap.put("graduate", true);

        ResponseEntity<ChannelMsgtype[]> getStudentEntity = restTemplate.exchange(
                "http://localhost:8081/kernel/channelMsgtype?graduate={graduate}",
                HttpMethod.GET,
                httpEntity,
                ChannelMsgtype[].class,
                queryParaMap
        );

        System.out.println("body= " + getStudentEntity.getBody());

        return getStudentEntity;
    }

    @Override
    public ResponseEntity<String> exchangePost(ChannelMsgtype channelMsgtype) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("header2","456");

        //由exchange發起post請求, 一定要加上content type = application/json
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity httpEntity = new HttpEntity<>(channelMsgtype, httpHeaders);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "http://localhost:8081/kernel/channelMsgtype",
                HttpMethod.POST,
                httpEntity,
                String.class
        );

        return responseEntity;
    }

}
