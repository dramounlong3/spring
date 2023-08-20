package com.example.demo.Zsample;

import org.springframework.data.repository.CrudRepository;


// 繼承spring boot jpa的class, 第一個參數放要對應的table class, 第二個參數放此table的primary key變數類型
public interface StudentJpaRepository extends CrudRepository<StudentJpa, Integer> {
}
