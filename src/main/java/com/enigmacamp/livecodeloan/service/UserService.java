package com.enigmacamp.livecodeloan.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.enigmacamp.livecodeloan.model.dto.res.UserResponse;
import com.enigmacamp.livecodeloan.model.entity.AppUser;

public interface UserService extends UserDetailsService {
  AppUser loadUserByUserId(String id);
  UserResponse getUserById(String id);
}
