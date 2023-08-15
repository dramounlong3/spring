package com.example.demo.service;

import com.example.demo.dao.ThreeTierStudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ThreeTierStudentServiceImp implements ThreeTierStudentService{

    // 注入Dao的bean
    @Autowired
    ThreeTierStudentDao threeTierStudentDao;

    @Override
    public ResponseEntity<?> getById(Integer studentId) {
        //呼叫Dao
        return threeTierStudentDao.getById(studentId);
    }
}
