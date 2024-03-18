package com.enigmacamp.livecodeloan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enigmacamp.livecodeloan.security.JwtUtils;

@Service
public class HeaderService {

  @Autowired
  private JwtUtils jwtUtils;

  public String userIdByToken(String token) {
    return this.jwtUtils.getUserIdByToken(token);
  }
}
