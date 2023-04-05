package com.example.repos;

import com.example.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class AccountService {

    @Autowired
    AccountRepo accRepo;
    @Autowired
    PasswordEncoder passEnco;

    public String addUser(Account account){
        if (accRepo.findByUsername(account.getUsername()) == null){
            if (accRepo.findByEmail(account.getEmail()) == null){
                    System.out.println(account);
                    accRepo.save(account);
                    return "redirect:/login";
            }
        }
        return "register";
    }
    public String deleteUser(Account account) {
        accRepo.delete(account);
        return "redirect:/";
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

    public void createTaskModelGen(Model model, String title, String description, int price, String image, Long id) {
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("price", price);
        model.addAttribute("image", image);
        model.addAttribute("accountId", id);
    }

    public String getUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName(); // get current username
    }

}
