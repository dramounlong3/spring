package com.example.demo.service;

import com.example.demo.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountServiceImp implements AccountService{

    @Autowired
    private AccountDao accountDao;


    //透過 @Transactional 註解, 讓此方法底下的資料庫操作, 都成功時才commit
    //通常會將Transactional加在service層的方法上, 因為通service的方法大多都會去操作各個資料庫的方法
    @Transactional
    @Override
    public void transfer(Integer fromAccountId, Integer toAccountId, Integer money) {

        // 從A扣錢
        accountDao.decreaseMoney(fromAccountId, money);

        // 故意跳Exception;
        Integer a = 1/0;

        // 從B加錢
        accountDao.increaseMoney(toAccountId, money);
    }
}
