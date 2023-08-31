package com.example.demo.Zsample;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ObjectMapperController {


    @GetMapping("/convert")
    public String convert() throws JsonProcessingException {
        User user = new User();
        user.setId(1);
        user.setName("Judy");

        ObjectMapper objectMapper = new ObjectMapper();

        //將object轉json字串
        objectMapper.writeValueAsString(user);
        System.out.println("json: " + user);

        //將json字串轉object, 第二個參數是看要轉成什麼Object
        //若有測試的地方要多次寫上原生的json格式內容, 可以改用object set, 再用objectMapper轉換成json字串, 可降低code維護的繁瑣性
        String json = "{\"id\":3,\"name\":\"Amy\"}";
        User userResult = objectMapper.readValue(json, User.class);
        System.out.println("User id= " + userResult.getId());
        System.out.println("User name= " + userResult.getName());

        return "convert success";
    }

}
