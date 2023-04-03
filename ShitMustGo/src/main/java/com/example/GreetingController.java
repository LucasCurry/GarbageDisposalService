package com.example;

import com.example.repos.AccountRepo;
import com.example.repos.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {

@Autowired
Account account;

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")

    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Greeting (": " + HtmlUtils.htmlEscape(message.getName()));
    }

}
