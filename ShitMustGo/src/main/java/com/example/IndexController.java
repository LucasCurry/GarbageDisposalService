package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public class IndexController {

    @GetMapping("/index")
    public String main(Model model) {
        model.addAttribute("rating", new Rating());
        return "index";
    }

    @PostMapping("/index")
    public String save(Rating rating, Model model) {
        model.addAttribute("rating", rating);
        return "saved";
    }
}