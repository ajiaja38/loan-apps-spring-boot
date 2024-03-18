package com.enigmacamp.livecodeloan.service;

import java.util.List;

import com.enigmacamp.livecodeloan.model.dto.req.UpdateCustomerDto;
import com.enigmacamp.livecodeloan.model.entity.Customer;

public interface CustomerService {
  List<Customer> getAllCustomer();
  Customer getCustomerById(String id);
  Customer updateCustomerById(String id, UpdateCustomerDto updateCustomerDto);
  void deleteCustomerById(String id);
}
