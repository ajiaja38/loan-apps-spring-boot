package com.enigmacamp.livecodeloan.security;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.enigmacamp.livecodeloan.model.entity.AppUser;

@Component
public class JwtUtils {
  
  @Value("${jwt.secret}")
  private String jwtSecret;
  
  @Value("${jwt.appName}")
  private String appName;
  
  @Value("${jwt.jwtExpiration}")
  private long jwtExpiration;

  public String generatedToken(AppUser appUser) {
    
    return JWT.create()
              .withIssuer(this.appName)
              .withSubject(appUser.getId())
              .withExpiresAt(Instant.now().plusSeconds(this.jwtExpiration))
              .withIssuedAt(Instant.now())
              .sign(getAlgorithm());
  }

  public String getUserIdByToken(String token) {
    JWTVerifier verifier = JWT.require(this.getAlgorithm()).build();
    DecodedJWT decodedJWT = verifier.verify(token);
    return decodedJWT.getSubject();
  }

  public boolean verifyJwtToken(String token) {
    JWTVerifier verifier = JWT.require(this.getAlgorithm()).build();
    DecodedJWT decodedJWT =  verifier.verify(token);
    return decodedJWT.getIssuer().equals(this.appName);
  }

  public Map<String, String> getUserInfoByToken(String token) {
    try {
      JWTVerifier verifier = JWT.require(this.getAlgorithm()).build();
      DecodedJWT decodedJWT =  verifier.verify(token);
      
      Map<String, String> userInfo = new HashMap<>();
      userInfo.put("userId", decodedJWT.getSubject());
      
      return userInfo;
    } catch (JWTVerificationException e) {
      throw new RuntimeException();
    }
  }

  private Algorithm getAlgorithm () {
    return Algorithm.HMAC256(this.jwtSecret.getBytes());
  }

}
