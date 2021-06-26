package com.example.springbootsecurity.account.repository;

import com.example.springbootsecurity.account.domain.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountsRepository extends JpaRepository<Accounts, Long> {
    Accounts findByUsername(String username);
}
