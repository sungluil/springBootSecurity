package com.example.springbootsecurity.account.domain;

import com.example.springbootsecurity.account.repository.AccountsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userDetailsService")
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final AccountsRepository accountsRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("UserDetailsService start >>>>>");
        Accounts account = accountsRepository.findByUsername(username);

        if(account == null) {
            throw new UsernameNotFoundException("not found username");
        }
        log.info("로그인 성공 아이디 = {}", account.getUsername());

        List<GrantedAuthority> role = new ArrayList<>();
        role.add(new SimpleGrantedAuthority(account.getRoleUser().toString()));

        CustomUser customUser = new CustomUser(account, role);

        return customUser;
    }
}
