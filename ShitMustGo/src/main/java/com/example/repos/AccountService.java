package com.example.repos;

import com.example.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    AccountRepo accRepo;

    public void addUser(Account account){

        accRepo.save(account);

    }

    public Long getAccountId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        if (currentUsername != null) {
            Account account = accRepo.findByUsername(currentUsername);
            if(account != null){
                Long accountid = account.getId();
                return accountid;
            }
        }
        return null;
    }


}
