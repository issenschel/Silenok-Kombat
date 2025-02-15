package com.example.silenokkombat.config;

import com.example.silenokkombat.service.UserTokenService;
import com.example.silenokkombat.util.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {
    private final JwtTokenUtil jwtTokenUtil;
    private final UserTokenService userTokenService;

    @Override
    protected  void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String username;
        String jwt;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7);
            if (jwt.split("\\.").length == 3) {
                try {
                    username = jwtTokenUtil.getUsername(jwt);
                    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null &&
                            (userTokenService.findByVersion(jwtTokenUtil.getVersionToken(jwt))).isPresent()) {
                        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                                username, null, jwtTokenUtil.getRoles(jwt).stream().map(SimpleGrantedAuthority::new).toList());
                        SecurityContextHolder.getContext().setAuthentication(token);
                    }
                } catch (ExpiredJwtException e) {
                    log.debug("Вермя жизни токена вышло");
                } catch (SignatureException e) {
                    log.debug("Подпись неправильная");
                }
            } else {
                log.debug("JWT не содержит трёх частей");
            }
        }

        filterChain.doFilter(request,response);

    }
}
