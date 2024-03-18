package com.enigmacamp.livecodeloan.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.enigmacamp.livecodeloan.model.dto.res.UserResponse;
import com.enigmacamp.livecodeloan.model.entity.AppUser;
import com.enigmacamp.livecodeloan.model.entity.Role;
import com.enigmacamp.livecodeloan.model.entity.User;
import com.enigmacamp.livecodeloan.repository.UserRepository;
import com.enigmacamp.livecodeloan.service.UserService;
import com.enigmacamp.livecodeloan.utils.constant.ERole;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;
  
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = this.userRepository.findByEmail(username).orElseThrow(
      () -> new UsernameNotFoundException("Invalid Credentials user")
    );

    return AppUser.builder()
                  .id(user.getId())
                  .email(user.getEmail())
                  .password(user.getPassword())
                  .roles(user.getRoles())
                  .build();
  }

  @Override
  public AppUser loadUserByUserId(String id) {
    User user = this.userRepository.findById(id).orElseThrow(
      () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found")
    );

    return AppUser.builder()
                  .id(user.getId())
                  .email(user.getEmail())
                  .password(user.getPassword())
                  .roles(user.getRoles())
                  .build();
  }

  @Override
  public UserResponse getUserById(String id) {
    User user = this.userRepository.findById(id).orElseThrow(
      () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found")
    );

    List<ERole> role = new ArrayList<>();

    for (Role roleResult : user.getRoles()) {
      role.add(roleResult.getRole());
    }

    return UserResponse.builder()
    .email(user.getEmail())
    .role(role)
    .build();
  }
  
}
