package com.example;

import com.example.repos.AccountRepo;
import com.example.repos.AccountService;
import com.example.repos.TaskRepo;
import com.example.repos.TaskService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
public class WebController {

    @Autowired
    AccountService accService;
    @Autowired
    AccountRepo accountRepo;
    @Autowired
    TaskRepo taskRepo;
    @Autowired
    PasswordEncoder passEnco;
    @Autowired
    TaskService taskService;


    //Frontpage Controller + Print tasks
    @GetMapping("/")
    String home(Model model) {
        Long id = accService.getAccountId();
        /* model.addAttribute("task", taskRepo.findAll());*/
        model.addAttribute("task", taskRepo.findAllByBookedId(null));
        return "home";
    }

    //Sorting Tasks
    @PostMapping("/")
    String sortCity(Model model, @RequestParam(required = false, defaultValue = "") String cities, @RequestParam(required = false, defaultValue = "") String sorting) {
        List<Task> tasks = taskService.sortList(cities, sorting);
        model.addAttribute("task", tasks);
        return "home";
    }


    //TaskPage Controller
    @GetMapping("/task/{id}")
    String task(Model model, @PathVariable Long id) {
        model.addAttribute("task", taskRepo.findById(id).get());
        model.addAttribute("accountid", accService.getAccountId());
        System.out.println(accService.getAccountId());

        return "task";
    }

    @PostMapping("/task/{id}")
    String taskPost(Model model, @PathVariable Long id) {
        Long accountId = accService.getAccountId();
        Task task = taskRepo.findById(id).get();
        task.setBookedId(accountId);
        taskRepo.save(task);
        return "redirect:/task/" + id;
    }


    //Login Controllers
    @GetMapping("/login")
    String login() {
        return "login";
    }

    @GetMapping("/account")
    String accountpage(Model model) {
        Long id = accService.getAccountId();
        model.addAttribute("accountId", id);
        model.addAttribute("account", accountRepo.findById(id).get().firstName);
        model.addAttribute("task", taskRepo.findAllByAccountId(id));
        model.addAttribute("bookedTask", taskRepo.findAllByBookedId(id));


        return "accountpage";
    }

    @GetMapping("/account/create")
    String createTask(Model model) {
        model.addAttribute("accountId", accService.getAccountId());
        return "createTask";
    }

    @PostMapping("/account/create")
    String postCreateTask(Model model, @RequestParam String title, @RequestParam String description, @RequestParam int price, @RequestParam String image, @RequestParam String cities) {
        Long id = accService.getAccountId();
        accService.createTaskModelGen(model, title, description, price, image, id);
        Task task = new Task(title, accountRepo.findById(id).get().address, image, price, description, id, cities, LocalDateTime.now());
        taskService.addTask(task);
        return "redirect:/";
    }

    @GetMapping("/account/{id}/avboka")
    String avbokaTask(@PathVariable Long id) {
        Task task = taskRepo.findById(id).get();
        task.setBookedId(null);
        taskRepo.save(task);

        return "redirect:/account";
    }

    //Registration Controllers
    @GetMapping("/register")
    String register() {
        return "register";
    }

    @PostMapping("/register")
    String registerUser(@RequestParam String firstname, @RequestParam String lastname, @RequestParam String username, @RequestParam String password, @RequestParam String passwordControll, @RequestParam String email, @RequestParam String phonenumber, @RequestParam String address, @RequestParam String cardnumber) {
        return accService.addUser(firstname, lastname, username, password, passwordControll, email, phonenumber, address, cardnumber);
    }

    @GetMapping("/account/{id}/delete")
    String deleteTask(@PathVariable Long id) {
        taskRepo.deleteById(id);
        return "redirect:/account";
    }

    @GetMapping("/account/{id}/deleteUser")
    String deleteUser(@PathVariable Long id, Model model, HttpServletRequest request) {
        model.addAttribute("accountId", accountRepo.findById(id).get().id);
        accountRepo.deleteById(id);
        try {
            request.logout();
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/";
    }

    @GetMapping("/faq")
    String faq() {
        return "faq";
    }


// Delince or accept offer

    @PostMapping("/accept")
    String acceptOffer(@RequestParam Long id2) {
        Task task = taskRepo.findById(id2).get();
        task.setAccepted(true);
        taskRepo.save(task);
        return "redirect:/account";

    }

    @PostMapping("/decline")
    String declineOffer(@RequestParam Long id){
        Task task = taskRepo.findById(id).get();
        task.setBookedId(null);
        taskRepo.save(task);
        return "redirect:/account";
    }
}
