package com.example.demo.dao;

import org.springframework.http.ResponseEntity;

public interface ThreeTierStudentDao {
    ResponseEntity<?> getById(Integer studentId);

}
