package com.enigmacamp.livecodeloan.service;

import java.util.List;

import com.enigmacamp.livecodeloan.model.dto.req.CreateLoanTypeDto;
import com.enigmacamp.livecodeloan.model.dto.req.UpdateLoadTypeDto;
import com.enigmacamp.livecodeloan.model.entity.LoanType;

public interface LoanTypeService {
  LoanType createLoanType(CreateLoanTypeDto createLoanTypeDto);
  List<LoanType> getAllLoanType();
  LoanType getLoanTypeById(String id);
  LoanType updateLoanType(UpdateLoadTypeDto updateLoadTypeDto);
  void deleteLoanTypeById(String id);
}
