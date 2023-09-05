package com.example.demo.Zsample.resttemplate;


import com.example.sqlservertest.model.ChannelMsgtype;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class RestTemplateServiceImp implements RestTemplateService {


    @Override
    public ChannelMsgtype[] list() {

        RestTemplate restTemplate = new RestTemplate();

        //第一個參數是url
        //第二個參數是要轉換的object
        ChannelMsgtype[] channelMsgtypeArray = restTemplate.getForObject("http://localhost:8080/kernel/channelMsgtype", ChannelMsgtype[].class);

        System.out.println("RestTemplate is being called.");
        return channelMsgtypeArray;
    }

    @Override
    public ChannelMsgtype[] getForObjectWithParam() {

        RestTemplate restTemplate = new RestTemplate();

        Map<String, Boolean> queryParamMap = new HashMap<>();
        queryParamMap.put("graduate",true);

        //queryParamMap是queryParameter, 就是URL ?後面的參數
        ChannelMsgtype[] channelMsgtypeArray = restTemplate.getForObject("http://localhost:8080/kernel/channelMsgtype",
                ChannelMsgtype[].class,
                queryParamMap
                );

        System.out.println("getForObjectWithParam is being called.");
        return channelMsgtypeArray;
    }

}
