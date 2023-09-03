package com.example.demo.dao;

import com.example.demo.model.ThreeTierStudent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// 此demo只有測試dao
// 各個@Test之間 彼此不能有依賴關係, 例如一定要先測A才能測B就不行
// 每次測試的結果必須一致且穩定, 資料不能因為測完一直有變化導致下一次測不過
// 測試不能受到外部服務的影響
@SpringBootTest
class ThreeTierStudentDaoImpTest {

    @Autowired
    ThreeTierStudentDao threeTierStudentDao;

    @Test
    void getById() {

        // 如果專案同時有使用到jpa 和 jdb針對同一張table去操作
        // 必須在測試用的application.properties加上 spring.jpa.hibernate.ddl-auto=none, 避免H2 sql script執行時有問題
        List<ThreeTierStudent> threeTierStudent = threeTierStudentDao.getById(4);


        assertNotNull(threeTierStudent);
        assertEquals("Kyle", threeTierStudent.get(0).getName());
        assertEquals(87.2, threeTierStudent.get(0).getScore());
        assertTrue(threeTierStudent.get(0).getGraduate());
        assertNotNull(threeTierStudent.get(0).getCreatedate());
    }

    @Test
    @Transactional
        // 因為有新增資料故需增加Tansactional, 讓每次測試完都rollback
    void insertStudent() {
        List<String> courseList = new ArrayList<>();
        List<String> todoList = new ArrayList<>();
        courseList.add("course1");
        courseList.add("course2");
        todoList.add("todo1");
        todoList.add("todo1");

        ThreeTierStudent threeTierStudent = new ThreeTierStudent();
        threeTierStudent.setId(9);
        threeTierStudent.setName("Kevin");
        threeTierStudent.setCourseList(courseList);
        threeTierStudent.setTodoList(todoList);
        threeTierStudent.setScore(66.6);
        threeTierStudent.setGraduate(false);
        Date now = new Date();
        threeTierStudent.setCreatedate(now);

        Integer studentId = threeTierStudentDao.insertStudent(threeTierStudent);

        List<ThreeTierStudent> studentList = threeTierStudentDao.getById(studentId);
        assertNotNull(studentList);
        assertEquals("Kevin", studentList.get(0).getName());
        assertEquals(66.6, studentList.get(0).getScore());
        assertFalse(studentList.get(0).getGraduate());
        // rowMapper 要特別注意取回的字串透過逗號轉成ArrayList的時候有可能逗號後面會有空白, 需在rowMapper特別注意
        assertIterableEquals(courseList, studentList.get(0).getCourseList());
        assertIterableEquals(todoList, studentList.get(0).getTodoList());
        assertNotNull(studentList.get(0).getCreatedate());

    }

    // @Transactional 為了避免刪除後影響到DB的資料, 讓每一次單元測試後都強制rollback, 與一般在main方法裡面使用不一樣(方法內有失敗才rollback)
    @Test
    @Transactional
    void deleteById() {
        threeTierStudentDao.deleteById(9);
        List<ThreeTierStudent> student = threeTierStudentDao.getById(9);
        assertEquals(0, student.size());
    }

}