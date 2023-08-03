package com.example.demo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class Student {
    // 不能是NULL或 空白 或 多空白 或 空字串
    // 只能對字串類型使用
    @NotBlank
    String name;

    //不能是NULL
    @NotNull
    Integer id;

    // 不能是NULL SIZE也不能<=0
    // 只能用在集合類型的參數(List、Set、Map)
    @NotEmpty
    List<String> courseList = new ArrayList<>();
    private List<String> todoList = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public List<String> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<String> courseList) {
        this.courseList = courseList;
    }

    public List<String> getTodoList() {
        return todoList;
    }

    public void setTodoList(List<String> todoList) {
        this.todoList = todoList;
    }
}
