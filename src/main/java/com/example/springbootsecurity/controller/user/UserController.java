package com.example.springbootsecurity.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/mypages")
    public String mypages() {
        return "user/mypages";
    }
}
