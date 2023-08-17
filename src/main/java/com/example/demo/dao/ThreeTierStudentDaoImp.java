package com.example.demo.dao;

import com.example.demo.model.ThreeTierStudent;
import com.example.demo.mapper.ThreeTierRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


// @Component
@Repository //效果與Component一樣, 只是標記為DAO層, 專門與資料庫溝通的repository
public class ThreeTierStudentDaoImp implements ThreeTierStudentDao{

    // 注入namedParameterJdbcTemplate的bean (從application.properties設定檔而來)
    @Autowired
    // @Qualifier("test1JdbcTemplate") // 當在測試多資料庫時，導致這邊需要加Qualifer，要run這段code時，需將application.properties改回單一資料庫設定並將此行註解
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public ResponseEntity<?> getById(Integer studentId) {
        String sql = "SELECT id, name, courseList, todoList FROM student2 WHERE id = :studentId";

        Map<String, Object> map = new HashMap<>();
        map.put("studentId", studentId);

        // 執行query, 並將db所回傳的物件格式透過rowMapper轉換為java物件
        List<ThreeTierStudent> list = namedParameterJdbcTemplate.query(sql, map, new ThreeTierRowMapper());

        return ResponseEntity.status(200).body(list);
    }
}
