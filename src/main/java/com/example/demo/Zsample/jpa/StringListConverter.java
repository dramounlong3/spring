package com.example.demo.Zsample.jpa;

import javax.persistence.AttributeConverter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StringListConverter implements AttributeConverter<List<String>, String> {

    // 以什麼字元做切割
    private static final String SPLIT_CHAR = ",";


    //轉成資料庫的格式, 將array用逗號分隔轉成字串
    @Override
    public String convertToDatabaseColumn(List<String> stringList) {
        return stringList != null ? String.join(SPLIT_CHAR, stringList) : "";
    }

    //轉成java object, 將以逗號分隔的字串轉成list
    @Override
    public List<String> convertToEntityAttribute(String s) {
        return s != null ? Arrays.asList(s.split(SPLIT_CHAR)) : Collections.emptyList();
    }
}
