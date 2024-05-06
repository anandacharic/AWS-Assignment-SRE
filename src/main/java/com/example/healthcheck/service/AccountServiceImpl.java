package com.example.healthcheck.service;

import com.example.healthcheck.dao.AccountRepository;
import com.example.healthcheck.entity.Account;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService{
    private EntityManager entityManager;
    private AccountRepository accountRepository;
    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository,EntityManager entityManager){
        this.accountRepository=accountRepository;
        this.entityManager=entityManager;
    }
    @Override
    public Account save(Account account){
        return accountRepository.save(account);
    }

    @Override
    public List<Account> findByEmail(String email){
        TypedQuery<Account> theQuery=entityManager.createQuery("From Account where email=:data",Account.class);
        theQuery.setParameter("data",email);
        return theQuery.getResultList();
    }

}
