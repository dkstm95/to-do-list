package com.seungilahn.todolist.configuration;

import com.seungilahn.todolist.auth.application.port.out.TokenProviderPort;
import com.seungilahn.todolist.user.application.port.out.LoadUserPort;
import com.seungilahn.todolist.user.domain.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final LoadUserPort loadUserPort;
    private final TokenProviderPort tokenProviderPort;

    JwtAuthenticationFilter(LoadUserPort loadUserPort, TokenProviderPort tokenProviderPort) {
        this.loadUserPort = loadUserPort;
        this.tokenProviderPort = tokenProviderPort;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().contains("/api/v1/auth")) {
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String jwt = authHeader.substring(7);
        String emailFromToken = tokenProviderPort.extractEmail(jwt);
        if (emailFromToken != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            User user = loadUserPort.loadUser(emailFromToken);
            if (tokenProviderPort.isValidToken(jwt, user.getEmail())) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        user,
                        null,
                        user.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }

}
