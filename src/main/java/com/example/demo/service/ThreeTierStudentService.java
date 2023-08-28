package com.example.demo.service;

import com.example.demo.model.ThreeTierStudent;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ThreeTierStudentService {
    List<ThreeTierStudent> getById(Integer studentId);

    Integer insertStudent(ThreeTierStudent threeTierStudent);

    void deleteById(Integer studentId);
}
