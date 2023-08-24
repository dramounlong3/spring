package com.example.demo.Zsample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountsJpaController {

    @Autowired
    AccountsJpaRepository accountJpaRepository;

    @GetMapping("/jpa/accounts/list")
    public ResponseEntity<?> composite() {

        Iterable<Accounts> accountList = accountJpaRepository.findAll();

        return ResponseEntity.status(200).body(accountList);
    }
}
