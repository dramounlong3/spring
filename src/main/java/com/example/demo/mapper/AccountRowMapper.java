package com.example.demo.mapper;

import com.example.demo.model.Account;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRowMapper implements RowMapper<Account> {
    @Override
    public Account mapRow(ResultSet resultSet, int i) throws SQLException {

        Account account = new Account();

        account.setId(resultSet.getInt("id"));
        account.setName(resultSet.getString("name"));
        account.setBalance(resultSet.getInt("balance"));

        return account;
    }
}
