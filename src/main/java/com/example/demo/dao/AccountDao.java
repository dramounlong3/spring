package com.example.demo.dao;

public interface AccountDao {
    void decreaseMoney(Integer fromAccountId, Integer money);
    void increaseMoney(Integer toAccountId, Integer money);
}
