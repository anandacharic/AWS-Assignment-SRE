package com.example.healthcheck.controller;

import com.example.healthcheck.entity.Account;
import com.example.healthcheck.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountRest {
    private AccountService accountService;
    @Autowired
    public AccountRest(AccountService accountService){
        this.accountService=accountService;
    }


}
