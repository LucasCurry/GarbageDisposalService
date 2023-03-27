package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    @Autowired
    AccountRepo accountRepo;

    @GetMapping("/")
    String home(Model model) {
        Account account = accountRepo.findById(1L).get();
        model.addAttribute("username", account.getUsername());

        return "home";
    }

    @GetMapping("/secret")
    String secret() {
        return "secret";
    }
}
