package com.abhishek.plashoeApp.PlashoeApp.configs;

import com.abhishek.plashoeApp.PlashoeApp.entities.UserEntity;
import com.abhishek.plashoeApp.PlashoeApp.services.JwtService;
import com.abhishek.plashoeApp.PlashoeApp.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

private final JwtService jwtService;
private final UserService userService;

@Autowired
@Qualifier("handlerExceptionResolver")
private HandlerExceptionResolver handlerExceptionResolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {

            final String authHeader = request.getHeader("Authorization");

            if (authHeader == null || !authHeader.startsWith("Bearer")) {
                filterChain.doFilter(request, response);
                return;
            }

            final String token = authHeader.split(" ")[1];

            Long userId = jwtService.getIdFromToken(token);

            if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserEntity userEntity = userService.getUserById(userId);

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userEntity, null, null);

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            }

            filterChain.doFilter(request, response);

        } catch(Exception e){
            handlerExceptionResolver.resolveException(request,response,null,e);
        }


    }
}
