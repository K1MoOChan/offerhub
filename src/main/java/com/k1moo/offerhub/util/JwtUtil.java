package com.k1moo.offerhub.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    // 满足HS256最低32位长度密钥
    private static final String SECRET = "k1moo_offerhub_secret_key_123456789abcdefghijk";
    private static final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());
    // 过期时间1天
    private static final long EXPIRATION = 1000 * 60 * 60 * 24;

    // 生成token
    public static String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // 安全解析token，异常返回null
    public static String parseToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject();
        } catch (ExpiredJwtException e) {
            System.out.println("token已过期");
            return null;
        } catch (JwtException e) {
            System.out.println("token非法/篡改");
            return null;
        }
    }
}