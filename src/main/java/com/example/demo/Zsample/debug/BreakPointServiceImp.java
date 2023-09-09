package com.example.demo.Zsample.debug;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BreakPointServiceImp implements BreakPointService{

    @Override
    public List<String> getAllNames() {
        List<String> nameList = new ArrayList<>();

        nameList.add("Judy");
        nameList.add("Amy"); //若斷點設定在這, nameList一開始只有Judy, 可開啟Evaluate Expression即時檢查程式或增加nameList, 很方便debug
        nameList.add("Mark");

        return nameList;
    }
}
