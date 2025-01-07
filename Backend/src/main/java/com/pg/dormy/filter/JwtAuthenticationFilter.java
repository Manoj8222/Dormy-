package com.pg.dormy.filter;

import com.pg.dormy.entity.User;
import com.pg.dormy.service.CustomUserDetailsService;
import com.pg.dormy.service.JwtService;
import com.pg.dormy.service.UserService;
import com.pg.dormy.service.UserServicesImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserService userService; // Depend on interface
    private static final List<String> PUBLIC_ENDPOINTS = Arrays.asList("/api/auth/login", "/api/auth/register","/api/pg/list/**","/api/rentals/**","/api/pg/search","/api/rentals/search");

    public JwtAuthenticationFilter(JwtService jwtService, UserService userService) {
        this.jwtService = jwtService;
        this.userService = userService; // Use the interface
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        // Skip authentication for public endpoints
        if (isPublicEndpoint(request.getRequestURI())) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String authHeader = request.getHeader("Authorization");

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                sendUnauthorizedError(response, "Missing or invalid Authorization header");
                return;
            }

            String token = authHeader.substring(7);
            String userPhone = jwtService.extractUserPhone(token);

            if (userPhone != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                User user = userService.loadUserByUserPhone(userPhone);
                if (user != null && jwtService.isValid(token, user)) {
                    System.out.println("Inside JWTAUTH " + user);
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    filterChain.doFilter(request, response);
                } else {
                    sendUnauthorizedError(response, "Invalid token");
                }
            } else {
                sendUnauthorizedError(response, "Invalid token");
            }
        } catch (Exception e) {
            sendUnauthorizedError(response, "Error processing token: " + e.getMessage());
        }
    }

    private void sendUnauthorizedError(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        String jsonResponse = "{\"error\": \"" + message + "\"}";
        response.getWriter().write(jsonResponse);
    }
    private boolean isPublicEndpoint(String requestURI) {
        return PUBLIC_ENDPOINTS.stream().anyMatch(requestURI::endsWith);
    }
}
