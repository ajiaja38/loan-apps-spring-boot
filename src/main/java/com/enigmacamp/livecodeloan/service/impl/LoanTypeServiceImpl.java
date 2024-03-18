package com.enigmacamp.livecodeloan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.enigmacamp.livecodeloan.model.dto.req.CreateLoanTypeDto;
import com.enigmacamp.livecodeloan.model.dto.req.UpdateLoadTypeDto;
import com.enigmacamp.livecodeloan.model.entity.LoanType;
import com.enigmacamp.livecodeloan.repository.LoanTypeRepository;
import com.enigmacamp.livecodeloan.service.LoanTypeService;

@Service
public class LoanTypeServiceImpl implements LoanTypeService {

  @Autowired
  private LoanTypeRepository loanTypeRepository;
  
  @Override
  public LoanType createLoanType(CreateLoanTypeDto createLoanTypeDto) {
    return this.loanTypeRepository.save(
      LoanType.builder()
      .type(createLoanTypeDto.getType())
      .maxLoan(createLoanTypeDto.getMaxloan())
      .build()
    );
  }

  @Override
  public List<LoanType> getAllLoanType() {
    return this.loanTypeRepository.findAll();
  }

  @Override
  public LoanType getLoanTypeById(String id) {
    return this.loanTypeRepository.findById(id).orElseThrow(
      () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Loan Type Not Found")
    );
  }

  @Override
  public LoanType updateLoanType(UpdateLoadTypeDto updateLoadTypeDto) {
    LoanType loanType = this.getLoanTypeById(updateLoadTypeDto.getId());

    loanType.setType(updateLoadTypeDto.getType());
    loanType.setMaxLoan(updateLoadTypeDto.getMaxloan());

    return this.loanTypeRepository.save(loanType);
  }

  @Override
  public void deleteLoanTypeById(String id) {
    this.getLoanTypeById(id);
    this.loanTypeRepository.deleteById(id);
  }
  
}
