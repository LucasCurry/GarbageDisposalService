package com.example;

import com.example.repos.AccountRepo;
import com.example.repos.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @PostMapping("/login")
    String loggedIn(Model model, @RequestParam String username, @RequestParam String password){
        model.addAttribute("username", username);
        model.addAttribute("password", password);
        System.out.println(username);
        System.out.println(password);
        Account account = accountRepo.findByUsernameAndPassword(username, password);
        if (account != null){
            return "redirect:/";
        }
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

    @GetMapping("/register")
    String register(){
        return "register";
    }

    @PostMapping("/register")
    String registerUser(@RequestParam String firstname, @RequestParam String lastname,@RequestParam String username, @RequestParam String password, @RequestParam String passwordControll, @RequestParam String email, @RequestParam String phonenumber, @RequestParam String address, @RequestParam String cardnumber){
        if (accountRepo.findByUsername(username) == null){
            if (accountRepo.findByEmail(email) == null){
                if (password.equals(passwordControll)){
                    Account account = new Account(firstname, lastname,username,password, phonenumber, email, address, cardnumber);
                    accountRepo.save(account);
                    return "redirect:/login";
                }
            }
        }

        return "register";
    }
}
