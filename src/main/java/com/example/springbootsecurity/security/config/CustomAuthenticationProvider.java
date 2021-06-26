package com.example.springbootsecurity.security.config;

import com.example.springbootsecurity.account.domain.CustomUser;
import com.example.springbootsecurity.account.domain.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("CustomAuthenticationProvider start >>>>>>>>");
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        log.info("username = {}", username);
        log.info("password = {}", password);

        CustomUser customUser = (CustomUser) userDetailsService.loadUserByUsername(username);

        if(!passwordEncoder.matches(password, customUser.getPassword())) {
            throw new BadCredentialsException("BadCredentialsException");
        }

        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(
                        customUser.getUsername(), null, customUser.getAuthorities());

        return authenticationToken;

    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(aClass);
    }
}
