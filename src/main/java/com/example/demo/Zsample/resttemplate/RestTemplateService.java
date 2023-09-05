package com.example.demo.Zsample.resttemplate;

import com.example.sqlservertest.model.ChannelMsgtype;


public interface RestTemplateService {

   public ChannelMsgtype[] list();

   public ChannelMsgtype[] getForObjectWithParam();

}
