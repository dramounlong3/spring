package com.example.demo.Zsample.resttemplate;

import com.example.demo.Zsample.commonModel.Student;
import org.springframework.http.ResponseEntity;


public interface RestTemplateService {

   //public ChannelMsgtype[] list();

   public Student getForObjectWithParam();

   public Student getForEntity();

   public Student postForEntity(Student student);

   public ResponseEntity<ChannelMsgtype[]> exchangeGet();

   public ResponseEntity<String> exchangePost(ChannelMsgtype channelMsgtype);

}
