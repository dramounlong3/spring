package com.example.demo.service;

import com.example.demo.dao.ThreeTierStudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

// @Component
@Service //效果與Component一樣，只是順便標記為service
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
