package com.example;

import com.example.repos.AccountRepo;
import com.example.repos.AccountService;
import com.example.repos.TaskRepo;
import com.example.repos.TaskService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.time.LocalDateTime;
import java.util.List;


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

    List<Task> tasks;




    //Frontpage Controller + Print tasks
    @GetMapping("/")
    String homeRedirect(){
        return "redirect:/home";
    }
    @GetMapping("/home")
    String home(Model model, @RequestParam(required = false, defaultValue = "0") int page) {
        tasks = taskService.getPage(page, 9);
        double numOfPages = taskService.numberOfPages(9);
        model.addAttribute("task", tasks);
        model.addAttribute("currentPage", page);
        model.addAttribute("numOfPages", numOfPages);
        return "home";
    }
    @GetMapping("/home2")
    String home2(Model model) {
        model.addAttribute("task", tasks);
        return "home2";
    }

    /* @PostMapping("/home2")
    String home2post(Model model, @RequestParam(required = false, defaultValue = "") String cities, @RequestParam(required = false, defaultValue = "") String sorting, @RequestParam(required = false, defaultValue = "0") int page) {
        tasks = taskService.sortList(cities, sorting, page);
        double numOfPages = taskService.numberOfPages(9);
        model.addAttribute("task", tasks);
        model.addAttribute("currentPage", page);
        model.addAttribute("numOfPages", numOfPages);
        return "home2";
    }*/

    //Sorting Tasks
    @PostMapping("/home")
    String sortCity(Model model, @RequestParam(required = false, defaultValue = "") String cities, @RequestParam(required = false, defaultValue = "") String sorting, @RequestParam(required = false, defaultValue = "0") int page) {
        tasks = taskService.sortList(cities, sorting, page);
        double numOfPages = taskService.numberOfPages(9);
        model.addAttribute("task", tasks);
        model.addAttribute("currentPage", page);
        model.addAttribute("numOfPages", numOfPages);
        return "home";
    }


    @GetMapping("/task/{id}")
    String task2(Model model, @PathVariable Long id) {
        model.addAttribute("task", taskRepo.findById(id).get());
        model.addAttribute("accountid", accService.getAccountId());
        System.out.println(accService.getAccountId());

        return "task2";

    //TaskPage Controller
//    @GetMapping("/task/{id}")
//    String task(Model model, @PathVariable Long id) {
//        model.addAttribute("task", taskRepo.findById(id).get());
//        model.addAttribute("accountid", accService.getAccountId());
//        System.out.println(accService.getAccountId());
//
//        return "task";
    }

    @PostMapping("/task/{id}")
    String taskPost(Model model, @PathVariable Long id) {
        Long accountId = accService.getAccountId();
        Task task = taskRepo.findById(id).get();
        task.setBookedId(accountId);
        taskRepo.save(task);

        return "redirect:/account";
    }


    //Login Controllers
    @GetMapping("/login")
    String login(Model model) {
        return "login";
    }

   /* @GetMapping("/login-error")
    String loginError(HttpServletRequest request, Model model){
        HttpSession session = request.getSession(false);
        String errorMessage = null;
        if(session != null){
            AuthenticationException ex = (AuthenticationException) session.
                    getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
            if(ex != null){
                errorMessage = ex.getMessage();
            }
        }
        model.addAttribute("errorMessage", errorMessage);
        return "login";
    }*/
    @GetMapping("/login-error")
    String loginError(Model model){
        model.addAttribute("failedLogin", true);
        return "login";
    }


    // Everything related to Account controller
    @GetMapping("/account")
    String accountpage(Model model) {
        Long id = accService.getAccountId();
        model.addAttribute("accountId", id);
        model.addAttribute("account", accountRepo.findById(id).get().firstName);
        model.addAttribute("task", taskRepo.findAllByAccountId(id));
        model.addAttribute("bookedTask", taskRepo.findAllByBookedId(id));
        return "accountpage2";
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


    //Registration Controllers
    @GetMapping("/register")
    String register(Model model) {
        model.addAttribute("account", new Account());
        return "register";
    }

   /* @PostMapping("/register")
    String registerUser(@RequestParam String firstname, @RequestParam String lastname, @RequestParam String username, @RequestParam String password, @RequestParam String passwordControll, @RequestParam String email, @RequestParam String phonenumber, @RequestParam String address, @RequestParam String cardnumber) {
        if (password.equals(passwordControll)) {
            Account account = new Account(firstname, lastname, username, passEnco.encode(password), phonenumber, email, address, cardnumber);
            return accService.addUser(account);
        }
        return "register";


    }*/
    @PostMapping("/register")
    String registerUser(@Valid Account account, BindingResult bindingResult, @RequestParam String passwordControll, RedirectAttributes ra) {
            return accService.addUser(account, bindingResult, passwordControll, ra);
    }

// FAQ controller
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
        return "redirect:/task/" + id2;

    }
    @PostMapping("/decline")
    String declineOffer(@RequestParam Long id){
        Task task = taskRepo.findById(id).get();
        task.setBookedId(null);
        taskRepo.save(task);
        return "redirect:/account";
    }

    // chat controller
    @GetMapping("/task/{id}/chat")
    String chatt(Model model, @PathVariable Long id) {
        model.addAttribute("task", taskRepo.findById(id).get());
        model.addAttribute("accountid", accService.getAccountId());
        return "hello";
    }
    // payment controller
    @GetMapping("/task/{id}/payment")
    String payment(Model model, @PathVariable Long id) {
        model.addAttribute("task", taskRepo.findById(id).get());
        return "payment";
    }


}
