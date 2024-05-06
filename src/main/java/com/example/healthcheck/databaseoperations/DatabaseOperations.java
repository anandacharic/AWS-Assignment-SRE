package com.example.healthcheck.databaseoperations;

import com.example.healthcheck.entity.Account;
import com.example.healthcheck.service.AccountService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

@Component
public class DatabaseOperations {
    private AccountService accountService;

    @Autowired
    public DatabaseOperations(AccountService accountService){
        this.accountService=accountService;
    }

    @PostConstruct
    public void dbinit(){

        try {
            File file = new File("/opt/users.csv");
            Scanner sc=new Scanner(file);
            sc.next();
            while(sc.hasNext())
            {
                String[] str=sc.next().split(",");
                List<Account> listaccount= accountService.findByEmail(str[2]);

                if(listaccount.size()==0){
                    Account account =new Account(str[0],str[1],str[2], (new BCryptPasswordEncoder().encode(str[3])));
                    accountService.save(account);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
