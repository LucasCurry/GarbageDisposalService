package com.example;

import com.example.repos.AccountRepo;
import com.example.repos.AccountService;
import com.example.repos.TaskRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

@SpringBootTest
public class RunApplicationTests {

	@Autowired
	AccountRepo accRepo;
	@Autowired
	TaskRepo taskRepo;
	@Autowired
	AccountService accService;
	@Test
	public void contextLoads() {

	}

	@Test
	public void testFindAccount(){
		Account account = accRepo.findByUsernameAndPassword("bjorn", "bjorn");
		Assertions.assertEquals(3L,account.getId());
	}

	@Test
	public void testAddDeleteUser(){
		Account account = new Account("hej", "0","0","0","0","0","0");
		Long count = accRepo.count();

		accRepo.save(account);
		Assertions.assertEquals(count+1, accRepo.count());

		accRepo.delete(account);
		Assertions.assertEquals(count,accRepo.count());
	}

	@Test
	public void testFindTaskById() {
		Task task = taskRepo.findById(4L).orElse(new Task());
		Assertions.assertEquals("Kan någon hjälpa mig?",task.description);

		Task task2 = taskRepo.findById(3L).orElse(new Task());
		Assertions.assertEquals("Kungsgatan 1337",task2.address);
	}

	@Test
	public void testAddDeleteTask() {
		Task task = new Task("Hämta skräp", "Gurravägen 3", "image.png", 123, "Jo hejdu", 3L, "Sthlm", LocalDateTime.now());
		Long count = taskRepo.count();

		taskRepo.save(task);
		Assertions.assertEquals(count+1,taskRepo.count());

		taskRepo.delete(task);
		Assertions.assertEquals(count,taskRepo.count());
	}
	@Test
	public void testServiceAddDeleteUser() {

		Long count = accRepo.count();
		Account validNewAccount = new Account("heh","heh","heh","heh","heh@heh.se","123","hehvägen 3");

		// Add new user -> Successful, return "redirect:/login"
		Assertions.assertEquals("redirect:/login", accService.addUser(validNewAccount));
		Assertions.assertEquals(count+1, accRepo.count());
		Assertions.assertEquals("heh", validNewAccount.getUsername());

		// Delete user -> Successful, return "redirect:/"
		Assertions.assertEquals("redirect:/", accService.deleteUser(validNewAccount));
		Assertions.assertNull(accRepo.findByUsername("heh"));
		Assertions.assertEquals(count,accRepo.count());

		// Add new user with already existing username -> Unsuccessful, return "register"
		Account alreadyExistingUsername = new Account("heh","heh","Hej","heh","heh@heh.se","123","hehvägen 3");
		Assertions.assertEquals("register", accService.addUser(alreadyExistingUsername));

		// Assertions.assertEquals("register",accService.addUser("heh","heh","Hej","heh","heh","heh@heh.se","123","hehvägen 3","12356"));
		// Assertions.assertEquals("redirect:/login", accService.addUser("heh","heh","heh","heh","heh","heh@heh.se","123","hehvägen 3","12356"));
	}


}
