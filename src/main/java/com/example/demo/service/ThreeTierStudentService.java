package com.example.demo.service;

import org.springframework.http.ResponseEntity;

public interface ThreeTierStudentService {
    ResponseEntity<?> getById(Integer studentId);
}
