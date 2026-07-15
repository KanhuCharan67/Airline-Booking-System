package com.kanhu.config;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.*;

@Service
@RequiredArgsConstructor
public class JwtProvider {

    private final SecretKey key = Keys.hmacShaKeyFor(
            JwtConstant.SECRET_KEY.getBytes()
    );

    public final String generteToken(Authentication auth, Long userId){
        Collection<? extends GrantedAuthority> authorities= auth.getAuthorities();
        String roles = populateAuterities(authorities);

        String  jwt = Jwts.builder()
                .issuedAt(new Date())
                .issuedAt(new Date(System.currentTimeMillis() + 86400000))
                .claim("email", auth.getName())
                .claim("authorities",roles)
                .claim("userId",userId)
                .signWith(key)
                .compact();
        return jwt;
    }

    private String populateAuterities(Collection<? extends GrantedAuthority> authorities) {

        Set<String> auths=new HashSet<>();
        for(GrantedAuthority authority: authorities){
            auths.add(authority.getAuthority());
        }
        return String.join(",",auths);
    }
}
