package com.example.healthcheck.service;

import com.example.healthcheck.entity.Account;

import java.util.List;

public interface AccountService {
    public Account save(Account account);
    public List<Account> findByEmail(String email);

}
