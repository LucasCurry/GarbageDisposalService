package com.example;

import com.example.Account;
import com.example.repos.AccountRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RunApplicationTests {

	@Autowired
	AccountRepo repo;
	@Test
	public void contextLoads() {

	}


	@Test
	public void testFindAccount(){
		Account account = repo.findByUsernameAndPassword("Hej", "123");
		Assertions.assertEquals(1L,account.getId());
	}

	@Test
	public void testAddUser(){
		Account account = new Account("hej", "0","0","0","0","0","0","0");
		Long count =  repo.count();
		repo.save(account);
		Long count2 = repo.count();
		Assertions.assertEquals(count+1, count2);
	}


}
