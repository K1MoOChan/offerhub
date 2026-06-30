package com.k1moo.offerhub.service;

import com.k1moo.offerhub.entity.User;

public interface UserService {
    // 注册
    String register(User user);
    // 根据邮箱查询用户
    User getUserByEmail(String email);
    // 登录
    String login(String email, String password);
}