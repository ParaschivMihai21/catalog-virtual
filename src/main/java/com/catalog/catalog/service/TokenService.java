package com.catalog.catalog.service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.catalog.catalog.dto.UserDTO;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.*;

@RequiredArgsConstructor
@Component

public class TokenService {

    @Value("${security.jwt.token.secret-key:secret-key}")
    private String secretKey;



    @PostConstruct
    protected void init() {

        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(UserDTO user) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + 3600000);

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.create()
                .withSubject(user.getUserName())
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .withClaim("roleName", user.getRoleName())
                .sign(algorithm);
    }

    public Authentication validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        JWTVerifier verifier = JWT.require(algorithm)
                .build();

        DecodedJWT decoded = verifier.verify(token);

        UserDTO user = UserDTO.builder()
                .userName(decoded.getSubject())
                .roleName(decoded.getClaim("roleName").asString())
                .build();

        List<GrantedAuthority> lstAuthorities = new ArrayList<>();
        lstAuthorities.add(new SimpleGrantedAuthority(user.getRoleName()));

        return new UsernamePasswordAuthenticationToken(user, "", lstAuthorities);
    }



}
