package com.enigmacamp.livecodeloan.service;

import com.enigmacamp.livecodeloan.model.dto.req.AuthRequestDto;
import com.enigmacamp.livecodeloan.model.dto.res.LoginResponseDto;
import com.enigmacamp.livecodeloan.model.dto.res.UserResponse;

public interface AuthService {
  UserResponse registerAdminStaf(AuthRequestDto authRequestDto);
  UserResponse registerCustomer(AuthRequestDto authRequestDto);
  LoginResponseDto loginUser(AuthRequestDto authRequestDto);
}
