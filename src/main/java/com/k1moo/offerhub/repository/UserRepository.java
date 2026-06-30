package com.k1moo.offerhub.repository;

import com.k1moo.offerhub.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // 根据邮箱查询用户
    User findByEmail(String email);
}