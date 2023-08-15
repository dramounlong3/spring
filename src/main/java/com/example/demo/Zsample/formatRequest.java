package com.example.demo.Zsample;

import java.time.LocalDate;
import java.util.Date;

public class formatRequest {
    Integer id;
    String name;
    Double score;
    String word;
    Date currentTime;
    LocalDate today;

    //在未使用lombok等套件之前, 必須建立getter和setter, 否則spring boot無法順利取得或設定對應成員為object或json格式

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

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Date getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Date currentTime) {
        this.currentTime = currentTime;
    }

    public LocalDate getToday() {
        return today;
    }

    public void setToday(LocalDate today) {
        this.today = today;
    }
}
