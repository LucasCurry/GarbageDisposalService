package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/")
    String home(Model model) {
        model.addAttribute("tasks", );

        return "Home";
    }

    @GetMapping("/secret")
    String secret() {
        return "Secret";
    }
}
