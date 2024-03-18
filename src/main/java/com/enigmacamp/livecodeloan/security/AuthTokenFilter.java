package com.enigmacamp.livecodeloan.security;

import java.io.IOException;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.enigmacamp.livecodeloan.service.UserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthTokenFilter extends OncePerRequestFilter {

  private final JwtUtils jwtUtils;
  private final UserService userService;

  @Override
  protected void doFilterInternal(
    HttpServletRequest request,
    HttpServletResponse response,
    FilterChain filterChain
  )
    throws ServletException, IOException {
    
    try {
      String headerAuth = request.getHeader(HttpHeaders.AUTHORIZATION);
      String token = null;

      if (headerAuth != null && headerAuth.startsWith("Bearer ")) {
        token = headerAuth.substring(7);
      }

      if (token != null && jwtUtils.verifyJwtToken(token)) {
        Map<String, String> userInfo = jwtUtils.getUserInfoByToken(token);
        UserDetails user = this.userService.loadUserByUserId(userInfo.get("userId"));

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        
        authenticationToken.setDetails(new WebAuthenticationDetailsSource());
        
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
      }
      
    } catch (Exception e) {
      e.getMessage();
    }

    filterChain.doFilter(request, response);
      
  }
  
}
