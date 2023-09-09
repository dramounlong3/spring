package com.example.demo.Zsample.debug;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class BreakPointController {

    @Autowired
    BreakPointService breakPointService;


    //當迴圈有100次，想讓程式停在第50次，可以在斷點紅點上按右鍵，設定 i == 50，可免去需一直按F9的問題
    //當太多地方都有設定斷點，可以點擊Mute BreakPoints，暫時先忽略


    @GetMapping("/breakpoint1")
    public List<String> myService1() {

        List<String> nameList = breakPointService.getAllNames();

        for (int i = 0; i < nameList.size(); i++) {
            String name = nameList.get(i);
            System.out.println("name: " + name);
        }
        return nameList;
    }

    @GetMapping("breakpoint2")
    public String myService2() {
        List<String> nameList = breakPointService.getAllNames();

        //簡單的lambda表達式，可以使用Trace Current Stream Chain執行debug
        return nameList.stream()
                .filter(y -> y.equals("Amy")).findFirst().orElse(null);
    }

}
