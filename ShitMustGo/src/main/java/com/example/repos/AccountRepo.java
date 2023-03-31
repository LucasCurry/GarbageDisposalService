package com.example.repos;

import com.example.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepo extends CrudRepository<Account, Long> {

    Account findByUsernameAndPassword(String username, String password);
    Account findByUsername(String username);
    Account findByEmail(String email);
}
