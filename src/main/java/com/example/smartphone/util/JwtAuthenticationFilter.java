package com.example.smartphone.util;

import com.example.smartphone.service.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LogManager.getLogger(JwtAuthenticationFilter.class);

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            // 1. Read Authorization header
            final String authHeader = request.getHeader("Authorization");
            final String jwtToken;
            final String userEmail;

            // 2. If header is missing or doesn't start with Bearer, skip filter
            if((authHeader==null)|| !authHeader.startsWith("Bearer ")){
                logger.debug("No JWT token found in Authorization header, skipping JWT validation");
                filterChain.doFilter(request,response);
                return;
            }

            // 3. Extract token by removing "Bearer "
            jwtToken=authHeader.substring(7);

            // 4. Extract email from token
            userEmail=jwtUtil.extractUserName(jwtToken);

            /**
             * 5. If email is found and no authentication is already present in SecurityContext,
             * then validate and authenticate the user
             */
            if(userEmail!=null && SecurityContextHolder.getContext().getAuthentication()==null){

                // Load user details from DB
                UserDetails userDetails = customUserDetailsService.loadUserByUsername(userEmail);

                // Validate token
                if(jwtUtil.validateToken(jwtToken,userDetails)){
                    //Create Authentication Token
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,null,userDetails.getAuthorities());
                    // Attach request details
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    //Set Authenticated user in Spring Security
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    
                    // Debug: Print authorities
                    logger.info("User: {} authenticated with authorities: {}", userEmail, userDetails.getAuthorities());
                } else {
                    logger.warn("Token validation failed for user: {}", userEmail);
                }
            }
        } catch (Exception e) {
            // If any exception occurs during token processing, log it and continue
            logger.error("Error processing JWT token: {}", e.getMessage(), e);
        }
        
        //continue Filter chain
        filterChain.doFilter(request,response);

    }
}
