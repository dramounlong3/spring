package com.example.demo.Zsample;


import javax.persistence.*;
import java.util.List;

// jpa所對應的table, 必須增加註釋Entity且指定table
@Entity
@Table(name = "student2") // 將Studentjpa對應到TABLE name, class name不一定要和table name相同
public class StudentJpa {

    @Id // primary key需要增加的註解
    @GeneratedValue(strategy = GenerationType.IDENTITY) //自動生成的設定
    @Column(name = "id") //將id的變數對應到欄位id
    Integer id;

    @Column(name = "name")
    String name;

    //透過@Convert 搭配自訂義的class StringListConverter且繼承AttributeConverter, 將前端array和資料庫的string(array型態), 互相轉換
    //資料庫實際欄位名稱為courseList
    //但java若以駝峰式寫法，變成會去找資料庫中course_list的欄位名稱
    //所以@Column和變數名稱都改為courselist
    @Convert(converter = StringListConverter.class)
    @Column(name = "courselist")
    List<String> courselist;

    @Convert(converter = StringListConverter.class)
    @Column(name = "todolist")
    List<String> todolist;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCourselist() {
        return courselist;
    }

    public void setCourselist(List<String> courselist) {
        this.courselist = courselist;
    }

    public List<String> getTodolist() {
        return todolist;
    }

    public void setTodolist(List<String> todolist) {
        this.todolist = todolist;
    }
}
