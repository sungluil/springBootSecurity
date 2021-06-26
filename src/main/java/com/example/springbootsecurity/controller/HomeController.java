package com.example.springbootsecurity.controller;

import com.example.springbootsecurity.account.domain.Accounts;
import com.example.springbootsecurity.account.dto.AccountsDto;
import com.example.springbootsecurity.account.dto.LoginDto;
import com.example.springbootsecurity.account.service.AccountsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.internal.Errors;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    private final AccountsService accountsService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/accounts")
    public String accounts(Model model) {
        model.addAttribute(new AccountsDto());
        return "user/accounts";
    }

    @PostMapping("/accounts")
    public String accountsSignIn(@Valid AccountsDto accountsDto, Errors errors) {
        log.info("accountsSignIn = {}", accountsDto);
        if(errors.hasErrors()) {
            return "user/accounts";
        }
        Accounts account = accountsService.회원가입(accountsDto);
        accountsService.자동로그인(account);

        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {
        return "user/login/login";
    }


}
