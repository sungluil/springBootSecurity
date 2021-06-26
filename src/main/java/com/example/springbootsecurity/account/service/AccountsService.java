package com.example.springbootsecurity.account.service;

import com.example.springbootsecurity.account.domain.Accounts;
import com.example.springbootsecurity.account.dto.AccountsDto;
import com.example.springbootsecurity.account.dto.RoleUser;
import com.example.springbootsecurity.account.repository.AccountsRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountsService {

    private final AccountsRepository accountsRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public Accounts 회원가입(AccountsDto accountsDto) {
        Accounts accounts = Accounts
                .builder()
                .username(accountsDto.getUsername())
                .password(passwordEncoder.encode(accountsDto.getPassword()))
                .email(accountsDto.getEmail())
                .age(accountsDto.getAge())
                .createTime(LocalDateTime.now())
                .deleYn("N")
                .roleUser(RoleUser.ROLE_USER)
                .build();

        return accountsRepository.save(accounts);
    }

    public void 자동로그인(Accounts accounts) {
        UsernamePasswordAuthenticationToken token
                = new UsernamePasswordAuthenticationToken(
                        accounts.getUsername(),
                        accounts.getPassword(),
                        List.of(new SimpleGrantedAuthority(
                                accounts.getRoleUser().toString())));
        SecurityContextHolder.getContext().setAuthentication(token);

    }
}
