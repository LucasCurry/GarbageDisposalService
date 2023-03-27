package com.example;

import com.example.repos.AccountRepo;
import com.example.repos.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class WebController {
    @Autowired
    AccountRepo accountRepo;
    @Autowired
    TaskRepo task;

    @GetMapping("/")
    String home(Model model) {
        Account account = accountRepo.findById(1L).get();
        model.addAttribute("username", account.getUsername());
        model.addAttribute("task", task.findAll());
        return "home";
    }
    @GetMapping("/login")
    String login() {
        return "login";
    }
    @GetMapping("/task/{id}")
    String task(Model model, @PathVariable(required = true) Long id) {
        model.addAttribute("task",task.findById(id).get());
        return "task";
    }

    @GetMapping("/secret")
    String secret() {
        return "secret";
    }
}
