package com.example.repos;

import com.example.Account;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.naming.Binding;

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

    public String addUser(@Valid Account account, BindingResult bindingResult, String passwordControll, RedirectAttributes ra){

        if(!account.getPassword().equals(passwordControll)){
            bindingResult.rejectValue("password", "error", "Inte samma lösenord.");
            ra.addFlashAttribute("FailedSignup", "Inte samma lösenord.");
            return "register";
        }
        if (bindingResult.hasErrors()){
            ra.addFlashAttribute("FailedSignup", "Något blev fel, försök igen");
            return "register";
        }
        account.setPassword(passEnco.encode(account.getPassword()));
        accRepo.save(account);
        return "login";
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
