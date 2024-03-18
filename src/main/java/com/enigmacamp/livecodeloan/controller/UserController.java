package com.enigmacamp.livecodeloan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enigmacamp.livecodeloan.model.dto.res.UserResponse;
import com.enigmacamp.livecodeloan.service.UserService;
import com.enigmacamp.livecodeloan.utils.constant.ApiPathConstant;
import com.enigmacamp.livecodeloan.utils.res.Response;

@RestController
@RequestMapping(
  ApiPathConstant.API +
  ApiPathConstant.VERSION +
  ApiPathConstant.USER
)
public class UserController {
  
  @Autowired
  private UserService userService;

  @GetMapping("{id}")
  public ResponseEntity<Response<UserResponse>> getUserByIdHandler(
    @PathVariable String id
  ) {
    return ResponseEntity
    .status(HttpStatus.OK)
    .body(
      new Response<UserResponse>(
        "Berhasil Get User By Id",
        this.userService.getUserById(id)
      )
    );
  }

}
