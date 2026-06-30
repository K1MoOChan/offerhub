package com.k1moo.offerhub.controller;
import com.k1moo.offerhub.dto.LoginDTO;
import com.k1moo.offerhub.entity.User;
import com.k1moo.offerhub.service.UserService;
import com.k1moo.offerhub.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserController {

    // 日志对象
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result<String> register(@RequestBody User user) {
        return Result.success(userService.register(user));
    }

    @PostMapping("/login")
    public Result<String> login(@RequestBody LoginDTO loginDTO) {
        log.info("前端传入邮箱: {}, 原始密码: {}", loginDTO.getEmail(), loginDTO.getPassword());
        User dbUser = userService.getUserByEmail(loginDTO.getEmail());
        if (dbUser == null) {
            return Result.error("账号不存在");
        }
        String result = userService.login(loginDTO.getEmail(), loginDTO.getPassword());
        return Result.success(result);

    }
}