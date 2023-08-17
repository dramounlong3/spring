package com.example.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class AccountDaoImp implements AccountDao{

    @Autowired
    // @Qualifier("test1JdbcTemplate") // 當在測試多資料庫時，導致這邊需要加Qualifer，要run這段code時，需將application.properties改回單一資料庫設定並將此行註解
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public void decreaseMoney(Integer fromAccountId, Integer money) {
        String sql = "UPDATE account SET balance = balance - :money WHERE id = :id";
        Map<String, Object> map = new HashMap<>();
        map.put("id", fromAccountId);
        map.put("money", money);

        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public void increaseMoney(Integer toAccountId, Integer money) {
        String sql = "UPDATE account SET balance = balance + :money WHERE id = :id";
        Map<String, Object> map = new HashMap<>();
        map.put("id",toAccountId);
        map.put("money",money);

        namedParameterJdbcTemplate.update(sql, map);
    }
}
