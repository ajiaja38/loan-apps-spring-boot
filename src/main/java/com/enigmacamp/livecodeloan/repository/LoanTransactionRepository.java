package com.enigmacamp.livecodeloan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enigmacamp.livecodeloan.model.entity.LoanTransaction;

public interface LoanTransactionRepository extends JpaRepository<LoanTransaction, String> {
  
}
