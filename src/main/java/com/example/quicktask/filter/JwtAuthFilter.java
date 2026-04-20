package com.example.quicktask.filter;

import com.example.quicktask.security.CustomUserDetailsService;
import com.example.quicktask.security.JwtService;
import com.example.quicktask.security.SecurityConfig;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
        throws ServletException, IOException {
        // extracting Authentication header from request
        String authHeader = request.getHeader("Authentication");

        // if request does not have auth header then it should be for login/registration
        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        // extracting token
        String token = authHeader.substring(7);
        // extracting email
        String email = jwtService.extractEmail(token);
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = userDetailsService.loadUserByUsername(email);

            if(jwtService.isTokenValid(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response); // always pass forward at end
    }
}

//Incoming request
//      ↓
//Has "Authorization: Bearer <token>" header?
//        ├── NO  → pass forward (public route, Spring handles it)
//      └── YES
//            ↓
//Extract token (remove "Bearer ")
//            ↓
//Extract email from token (JwtService)
//            ↓
//Load user from DB by email (CustomUserDetailsService)
//            ↓
//Is token valid + not expired? (JwtService)
//        ├── NO  → skip, pass forward → Spring rejects it (401)
//            └── YES
//                  ↓
//Put user in SecurityContextHolder
//        ↓
//Pass forward → Controller runs