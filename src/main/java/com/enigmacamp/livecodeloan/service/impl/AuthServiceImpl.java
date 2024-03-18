package com.enigmacamp.livecodeloan.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.enigmacamp.livecodeloan.model.dto.req.AuthRequestDto;
import com.enigmacamp.livecodeloan.model.dto.res.LoginResponseDto;
import com.enigmacamp.livecodeloan.model.dto.res.UserResponse;
import com.enigmacamp.livecodeloan.model.entity.AppUser;
import com.enigmacamp.livecodeloan.model.entity.Customer;
import com.enigmacamp.livecodeloan.model.entity.Role;
import com.enigmacamp.livecodeloan.model.entity.User;
import com.enigmacamp.livecodeloan.repository.CustomerRepository;
import com.enigmacamp.livecodeloan.repository.UserRepository;
import com.enigmacamp.livecodeloan.security.JwtUtils;
import com.enigmacamp.livecodeloan.service.AuthService;
import com.enigmacamp.livecodeloan.service.RoleService;
import com.enigmacamp.livecodeloan.utils.constant.ERole;

import jakarta.transaction.Transactional;

@Service
public class AuthServiceImpl implements AuthService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private RoleService roleService;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtUtils jwtUtils;

  @Override
  @Transactional
  public UserResponse registerCustomer(AuthRequestDto authRequestDto) {
    try {
      List<ERole> roles = new ArrayList<>();
      roles.add(ERole.ROLE_CUSTOMER);

      List<Role> userRoles = new ArrayList<>();

      for (ERole role : roles) {
        Role roleResult = this.roleService.getOrSave(role);
        userRoles.add(roleResult);
      }

      User user = User.builder()
      .email(authRequestDto.getEmail())
      .password(this.passwordEncoder.encode(authRequestDto.getPassword()))
      .roles(userRoles)
      .build();
      
      this.userRepository.save(user);

      Customer customer = new Customer();
      customer.setStatus(true);
      customer.setUser(user);
      
      this.customerRepository.save(customer);

      return UserResponse.builder()
      .email(user.getEmail())
      .role(roles)
      .build();
      
    } catch (DataIntegrityViolationException e) {
      throw e;
    }
  }

  @Override
  @Transactional
  public UserResponse registerAdminStaf(AuthRequestDto authRequestDto) {
    try {
      List<ERole> roles = new ArrayList<>();
      roles.add(ERole.ROLE_ADMIN);
      roles.add(ERole.ROLE_STAFF);

      List<Role> userRoles = new ArrayList<>();

      for (ERole role : roles) {
        Role roleResult = this.roleService.getOrSave(role);
        userRoles.add(roleResult);
      }

      User user = User.builder()
      .email(authRequestDto.getEmail())
      .password(this.passwordEncoder.encode(authRequestDto.getPassword()))
      .roles(userRoles)
      .build();
      
      this.userRepository.save(user);

      return UserResponse.builder()
      .email(user.getEmail())
      .role(roles)
      .build();
      
    } catch (DataIntegrityViolationException e) {
      throw e;
    }
  }

  @Override
  public LoginResponseDto loginUser(AuthRequestDto authRequestDto) {
    try {
      Authentication authentication = this.authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
          authRequestDto.getEmail(),
          authRequestDto.getPassword()
        )
      );

      SecurityContextHolder.getContext().setAuthentication(authentication);
      AppUser appUser = (AppUser) authentication.getPrincipal();

      List<ERole> role = new ArrayList<>();

      for (Role roleResult : appUser.getRoles()) {
        role.add(roleResult.getRole());
      }

      String token = this.jwtUtils.generatedToken(appUser);

      return LoginResponseDto.builder()
      .email(appUser.getEmail())
      .role(role)
      .token(token)
      .build();
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username Atau Password tidak ditemukan!");
    }
  }
  
}
