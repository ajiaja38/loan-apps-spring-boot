package com.enigmacamp.livecodeloan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enigmacamp.livecodeloan.model.dto.req.AuthRequestDto;
import com.enigmacamp.livecodeloan.model.dto.res.LoginResponseDto;
import com.enigmacamp.livecodeloan.model.dto.res.UserResponse;
import com.enigmacamp.livecodeloan.service.AuthService;
import com.enigmacamp.livecodeloan.utils.constant.ApiPathConstant;
import com.enigmacamp.livecodeloan.utils.res.Response;

@RestController
@RequestMapping(
  ApiPathConstant.API +
  ApiPathConstant.VERSION +
  ApiPathConstant.AUTH
)
public class AuthController {

  @Autowired
  private AuthService authService;

  @PostMapping("signup")
  public ResponseEntity<Response<UserResponse>> registerUserCustomerHandler(
    @RequestBody AuthRequestDto authRequestDto
  ) {
    return ResponseEntity
    .status(HttpStatus.CREATED)
    .body(
      new Response<UserResponse>(
        "Berhasil Registrasi User dan Customer",
        this.authService.registerCustomer(authRequestDto)
      )
    );
  }
  
  @PostMapping("signup/admin-staff")
  public ResponseEntity<Response<UserResponse>> registerHandler(
    @RequestBody AuthRequestDto authRequestDto
  ) {
    return ResponseEntity
    .status(HttpStatus.CREATED)
    .body(
      new Response<UserResponse>(
        "Berhasil Registrasi Admin / Staff",
        this.authService.registerAdminStaf(authRequestDto)
      )
    );
  }

  @PostMapping("signin")
  public ResponseEntity<Response<LoginResponseDto>> loginHandler(
    @RequestBody AuthRequestDto authRequestDto
  ) {
    return ResponseEntity
    .status(HttpStatus.OK)
    .body(
      new Response<LoginResponseDto>(
        "Berhasil Login",
        this.authService.loginUser(authRequestDto)
      )
    );
  }

}
