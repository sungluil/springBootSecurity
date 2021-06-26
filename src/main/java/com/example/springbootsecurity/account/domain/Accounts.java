package com.example.springbootsecurity.account.domain;

import com.example.springbootsecurity.account.dto.RoleUser;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter @Getter
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
public class Accounts {
    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private String password;

    private String email;

    private String age;

    private LocalDateTime createTime;

    private String deleYn;

    @Enumerated(EnumType.STRING)
    private RoleUser roleUser;
}
