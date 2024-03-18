package com.enigmacamp.livecodeloan.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enigmacamp.livecodeloan.model.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {
  List<Customer> findByStatusTrue();
  Optional<Customer> findByStatusTrueAndIdIs(String id);
  Optional<Customer> findByUserId(String id);
}
