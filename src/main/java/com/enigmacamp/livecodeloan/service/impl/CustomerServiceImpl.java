package com.enigmacamp.livecodeloan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.enigmacamp.livecodeloan.model.dto.req.UpdateCustomerDto;
import com.enigmacamp.livecodeloan.model.entity.Customer;
import com.enigmacamp.livecodeloan.repository.CustomerRepository;
import com.enigmacamp.livecodeloan.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

  @Autowired
  private CustomerRepository customerRepository;

  @Override
  public List<Customer> getAllCustomer() {
    return this.customerRepository.findByStatusTrue();
  }

  @Override
  public Customer getCustomerById(String id) {
    return this.customerRepository.findByStatusTrueAndIdIs(id).orElseThrow(
      () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Loan Type Not Found")
    );
  }

  @Override
  public Customer updateCustomerById(String id, UpdateCustomerDto updateCustomerDto) {
    Customer existingCustomer = this.getCustomerById(id);
    
    existingCustomer.setFirstName(updateCustomerDto.getFirstName());
    existingCustomer.setLastName(updateCustomerDto.getLastName());
    existingCustomer.setDateOfBirth(updateCustomerDto.getDateOfBirth());
    existingCustomer.setPhone(updateCustomerDto.getPhone());

    return this.customerRepository.save(existingCustomer);
  }

  @Override
  public void deleteCustomerById(String id) {
    this.getCustomerById(id);
    this.customerRepository.deleteById(id);
  }
  
}
