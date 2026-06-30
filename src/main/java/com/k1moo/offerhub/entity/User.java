package com.k1moo.offerhub.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 用户名
    @Column(nullable = false, unique = true)
    private String username;

    // 密码
    @Column(nullable = false)
    private String password;

    // 邮箱
    @Column(nullable = false, unique = true)
    private String email;

}