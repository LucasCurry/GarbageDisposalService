package com.example;

import com.example.repos.AccountRepo;
import com.example.repos.TaskRepo;
import com.example.repos.TaskService;
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
    TaskRepo taskRepo;

    @Autowired
    TaskService taskService;


    //Frontpage Controller
    @GetMapping("/")
    String home(Model model) {
        Account account = accountRepo.findById(1L).get();
        model.addAttribute("username", account.getUsername());
        model.addAttribute("task", taskRepo.findAll());
        return "home";
    }

    //TaskPage Controller
    @GetMapping("/task/{id}")
    String task(Model model, @PathVariable(required = true) Long id) {
        model.addAttribute("task",taskRepo.findById(id).get());
        return "task";
    }

    //Login Controllers
    @GetMapping("/login")
    String login() {
        return "login";
    }

    @PostMapping("/login")
    String loggedIn(Model model, @RequestParam String username, @RequestParam String password){
        model.addAttribute("username", username);
        model.addAttribute("password", password);
//        model.addAttribute("accountId", accountRepo.findByUsername(username).getId());
        System.out.println(username);
        System.out.println(password);
        Account account = accountRepo.findByUsernameAndPassword(username, password);
        if (account != null){
            return "redirect:/account/" + account.id;
        }
        return "login";
    }

    @GetMapping("/account/{accountId}")
    String accountpage(Model model, @PathVariable Long accountId) {
        model.addAttribute("accountId", accountId);
        model.addAttribute("account", accountRepo.findById(accountId).get().username);
        model.addAttribute("task", taskRepo.findAllByAccountId(accountId));
        return "accountpage";
    }

    @GetMapping("/account/{accountId}/create")
    String createTask(Model model, @PathVariable Long accountId) {
        model.addAttribute("accountId", accountId);
        return "createTask";
    }

    @PostMapping ("/account/{accountId}/create")
    String postCreateTask(Model model, @PathVariable Long accountId, @RequestParam String title, @RequestParam String description, @RequestParam int price, @RequestParam String image) {
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("price", price);
        model.addAttribute("image", image);
        model.addAttribute("accountId", accountId);
        Task task = new Task(title, accountRepo.findById(accountId).get().address, image, price, description, accountId);
        taskService.addTask(task);

        return "redirect:/account/{accountId}";
    }


    //Access testing
    @GetMapping("/secret")
    String secret() {
        return "secret";
    }


    //Registration Controllers
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
