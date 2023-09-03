package com.example.demo.dao;

import com.example.demo.model.ThreeTierStudent;
import com.example.demo.mapper.ThreeTierRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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
    // @Qualifier("test1JdbcTemplate") // 當在測試多資料庫時，導致這邊需要加Qualifier，要run這段code時，需將application.properties改回單一資料庫設定並將此行註解
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<ThreeTierStudent> getById(Integer studentId) {
        String sql = "SELECT id, name, courseList, todoList, score, graduate, create_date FROM threetier WHERE id = :studentId";

        Map<String, Object> map = new HashMap<>();
        map.put("studentId", studentId);


        // 執行query, 並將db所回傳的物件格式透過rowMapper轉換為java物件
        List<ThreeTierStudent> list = namedParameterJdbcTemplate.query(sql, map, new ThreeTierRowMapper());

            return list;
    }

    @Override
    public Integer insertStudent(ThreeTierStudent threeTierStudent) {
        //透過冒號變數的方式讓下面宣告的map代入
        String sql = "INSERT INTO threetier(id, name, courseList, todoList, score, graduate, create_date) VALUES(:studentId, :studentName, :studentCourseList, :studentTodoList, :studentScore, :studentGraduate, :studentCreate_date)";

        Map<String, Object> map = new HashMap<>();
        //由key value方式組成, 透過request傳入的參數, 代入至sql語法執行
        map.put("studentId", threeTierStudent.getId());
        map.put("studentName", threeTierStudent.getName());
        map.put("studentCourseList", threeTierStudent.getCourseList().toString());
        map.put("studentTodoList", threeTierStudent.getTodoList().toString());
        map.put("studentScore", threeTierStudent.getScore());
        map.put("studentGraduate", threeTierStudent.getGraduate());
        map.put("studentCreate_date", threeTierStudent.getCreatedate());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        //update 對應到 sql可執行 insert, update, delete
        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map), keyHolder);

        Integer id = keyHolder.getKey().intValue();

        return id;
    }

    @Override
    public void deleteById(Integer studentId) {
        String sql = "DELETE FROM threetier WHERE id = :studentId";
        Map<String, Integer> map = new HashMap<>();
        map.put("studentId",studentId);
        namedParameterJdbcTemplate.update(sql, map);
    }
}
