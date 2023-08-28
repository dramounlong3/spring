package com.example.demo.service;

import com.example.demo.dao.ThreeTierStudentDao;
import com.example.demo.model.ThreeTierStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

// @Component
@Service //效果與Component一樣，只是順便標記為service
public class ThreeTierStudentServiceImp implements ThreeTierStudentService{

    // 注入Dao的bean
    @Autowired
    ThreeTierStudentDao threeTierStudentDao;

    @Override
    public List<ThreeTierStudent> getById(Integer studentId) {
        //呼叫Dao
        return threeTierStudentDao.getById(studentId);
    }

    @Override
    public Integer insertStudent(ThreeTierStudent threeTierStudent) {
        return threeTierStudentDao.insertStudent(threeTierStudent);
    }

    @Override
    public void deleteById(Integer studentId) {
        threeTierStudentDao.deleteById(studentId);
    }
}
