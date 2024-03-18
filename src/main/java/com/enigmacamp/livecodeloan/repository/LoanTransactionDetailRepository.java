package com.enigmacamp.livecodeloan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enigmacamp.livecodeloan.model.entity.LoanTransactionDetail;

public interface LoanTransactionDetailRepository extends JpaRepository<LoanTransactionDetail, String> {
  
}
