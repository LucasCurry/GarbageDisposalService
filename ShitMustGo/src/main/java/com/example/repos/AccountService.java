package com.example.repos;

import com.example.Account;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountService {

    @Autowired
    AccountRepo accRepo;

    public void addUser(Account account){
        accRepo.save(account);

    }
}
