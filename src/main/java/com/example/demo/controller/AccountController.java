package com.example.demo.controller;

import com.example.demo.dto.TransferRequest;
import com.example.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/transfer")
    public ResponseEntity<?> transfer(@RequestBody TransferRequest transferRequest) {
        accountService.transfer(
                transferRequest.getFrom(),
                transferRequest.getTo(),
                transferRequest.getMoney()
        );
        return ResponseEntity.status(200).body("轉帳成功");
    }
}
