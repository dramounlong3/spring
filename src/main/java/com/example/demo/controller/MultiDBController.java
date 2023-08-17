//package com.example.demo.controller;
//
//
//import com.example.demo.mapper.AccountRowMapper;
//import com.example.demo.model.Account;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.http.ResponseEntity;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Objects;
//
//// 純粹為了練習多個資料庫連線設定, 故無將程式拆成三層式, 只有將rowMapper拆出去
//// 要run此段code需將application.properties的多資料庫設定解除註解
//@RestController
//public class MultiDBController {
//
//    @Autowired
//    @Qualifier("test1JdbcTemplate")
//    private NamedParameterJdbcTemplate test1JdbcTemplate;
//
//    @Autowired
//    @Qualifier("test2JdbcTemplate")
//    private NamedParameterJdbcTemplate test2JdbcTemplate;
//
//    @GetMapping("/multidb1")
//    public ResponseEntity<?> db1() {
//        String sql = "SELECT id, name, balance FROM account";
//        Map<String, Objects> map = new HashMap<>();
//
//        // QUERY的結果有可能0~多筆, 所以回傳的格式固定式LIST
//        List<Account> accountList = test1JdbcTemplate.query(sql, map, new AccountRowMapper());
//
//        return ResponseEntity.status(200).body(accountList);
//    }
//
//    @GetMapping("/multidb2")
//    public ResponseEntity<?> db2() {
//        String sql = "SELECT id, name, balance FROM account";
//        Map<String, Objects> map = new HashMap<>();
//
//        // QUERY的結果有可能0~多筆, 所以回傳的格式固定式LIST
//        List<Account> accountList = test2JdbcTemplate.query(sql, map, new AccountRowMapper());
//
//        return ResponseEntity.status(200).body(accountList);
//    }
//
//
//}
