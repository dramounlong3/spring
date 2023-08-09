package com.example.demo;

import org.springframework.jdbc.core.RowMapper;

import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Student2RowMapper implements RowMapper<Student> {

    // resultSet= 從資料庫取得的數據(資料集就是controller所撰寫的sql語法), i= 第幾筆數據 (很少用)
    // 方法的返回類型需要和implements的類型一致
    // rowMapper一次只會取回一條數據
    @Override
    public Student mapRow(ResultSet resultSet, int i) throws SQLException {

        Student student = new Student();

        // get內的參數名稱 需對應到 controller SQL的select名稱, 例如有使用別名, 這邊也得使用別名
        // get的型態需對應資料庫中的型態
        // 最後將取得的數據透過setXX 轉回 java object
        student.setId(resultSet.getInt("id"));
        student.setName(resultSet.getString("name"));

        // 將含有陣列符號的字串先轉為list, 先取代掉前後的[], split將字串切成陣列, 最後再將陣列轉arraylist
        String tempStr= resultSet.getString("courseList").replaceAll("\\[|\\]","");
        List<String> tempList = new ArrayList<String>(Arrays.asList(tempStr.split(",")));
        student.setCourseList(tempList);

        tempStr = resultSet.getString("todoList").replaceAll("\\[|\\}]","");
        tempList = new ArrayList<>(Arrays.asList(tempStr.split(",")));
        student.setTodoList(tempList);
        return student;
    }
}
