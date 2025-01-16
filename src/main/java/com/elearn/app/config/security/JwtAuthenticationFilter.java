package com.elearn.app.config.security;

import java.io.IOException;

import javax.naming.AuthenticationException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private JwtUtil jwtUtil;

    private UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, 
                                    HttpServletResponse response, 
                                    FilterChain filterChain)
            throws ServletException, IOException {
        
                //Authorization : Bearer token
                String authorizationHeader = request.getHeader("Authorization");
                System.out.println("Header " + authorizationHeader);
                String username = null;
                String jwtToken = null;
                if(authorizationHeader != null && authorizationHeader.startsWith("Bearer")){
                    //sb kuch sahi hai
                    jwtToken = authorizationHeader.substring(7);
                    username = jwtUtil.extractUsername(jwtToken);

                    if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
                        //validate
                        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                        if(jwtToken != null && jwtUtil.validateToken(jwtToken, userDetails.getUsername())){
                            
                            //authentication to security
                            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                        }
                    }
                } else{
                    try {
                        throw new AuthenticationException("Token not present!");
                    } catch (AuthenticationException ex) {
                        System.out.println(ex);
                    }
                }

                filterChain.doFilter(request, response);
    }
    

}
