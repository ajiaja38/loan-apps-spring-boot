package com.enigmacamp.livecodeloan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enigmacamp.livecodeloan.model.dto.req.UpdateCustomerDto;
import com.enigmacamp.livecodeloan.model.entity.Customer;
import com.enigmacamp.livecodeloan.service.CustomerService;
import com.enigmacamp.livecodeloan.service.impl.HeaderService;
import com.enigmacamp.livecodeloan.utils.constant.ApiPathConstant;
import com.enigmacamp.livecodeloan.utils.res.Response;
import com.enigmacamp.livecodeloan.utils.res.ResponseMessage;

@RestController
@RequestMapping(
  ApiPathConstant.API +
  ApiPathConstant.VERSION +
  ApiPathConstant.CUSTOMER
)
public class CustomerController {
  
  @Autowired
  private CustomerService customerService;

  @Autowired
  private HeaderService headerService;

  @GetMapping
  @PreAuthorize("hasAnyRole('ROLE_STAFF', 'ROLE_ADMIN')")
  public ResponseEntity<Response<List<Customer>>> getAllCustomerHandler() {
    return ResponseEntity
    .status(HttpStatus.OK)
    .body(
      new Response<List<Customer>>(
        "Successfully fetch customer",
        this.customerService.getAllCustomer()
      )
    );
  }

  @GetMapping("{id}")
  public ResponseEntity<Response<Customer>> getCustomerByIdHandler(@PathVariable String id) {
    return ResponseEntity
    .status(HttpStatus.OK)
    .body(
      new Response<Customer>(
        "Successfully fetch customer",
        this.customerService.getCustomerById(id)
      )
    );
  }

  @PutMapping
  public ResponseEntity<Response<Customer>> updateCustomerHandler(
    @RequestHeader(HttpHeaders.AUTHORIZATION)
    String authorizationHeader,
    @RequestBody UpdateCustomerDto updateCustomerDto
  ) {
        
    return ResponseEntity
    .status(HttpStatus.CREATED)
    .body(
      new Response<Customer>(
        "Berhasil Update Customer",
        this.customerService.updateCustomerById(
          this.headerService.userIdByToken(authorizationHeader.substring(7)),
          updateCustomerDto
        )
      )
    );
  }

  @DeleteMapping("{id}")
    public ResponseEntity<ResponseMessage> deleteCustomerHandler(@PathVariable String id) {
      this.customerService.deleteCustomerById(id);
      
      return ResponseEntity
      .status(HttpStatus.OK)
      .body(
        new ResponseMessage(
          HttpStatus.OK.value(),
          "Berhasil delete customer"
        )
      );
    }

}
