package com.example.springbootsecurity.account.domain;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class CustomUser extends User {

    private Accounts accounts;

    public CustomUser(Accounts accounts, Collection<? extends GrantedAuthority> authorities) {
        super(accounts.getUsername(), accounts.getPassword(), authorities);
        this.accounts = accounts;
    }
}
