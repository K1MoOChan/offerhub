package com.k1moo.offerhub.service.impl;

import com.k1moo.offerhub.entity.User;
import com.k1moo.offerhub.repository.UserRepository;
import com.k1moo.offerhub.service.UserService;
import com.k1moo.offerhub.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String register(User user) {
        String encryptPwd = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptPwd);
        userRepository.save(user);
        return "注册成功";
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public String login(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return "用户不存在";
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            return "密码错误";
        }
        return JwtUtil.generateToken(user.getEmail());
    }
}