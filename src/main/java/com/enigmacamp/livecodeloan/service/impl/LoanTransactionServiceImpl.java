package com.enigmacamp.livecodeloan.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.enigmacamp.livecodeloan.model.dto.req.ApproveTransactionByAdminDto;
import com.enigmacamp.livecodeloan.model.dto.req.TransactionalRequestLoanDto;
import com.enigmacamp.livecodeloan.model.dto.res.TransactionalResponseLoanDto;
import com.enigmacamp.livecodeloan.model.entity.Customer;
import com.enigmacamp.livecodeloan.model.entity.InstalmentType;
import com.enigmacamp.livecodeloan.model.entity.LoanTransaction;
import com.enigmacamp.livecodeloan.model.entity.LoanType;
import com.enigmacamp.livecodeloan.model.entity.User;
import com.enigmacamp.livecodeloan.repository.LoanTransactionRepository;
import com.enigmacamp.livecodeloan.repository.UserRepository;
import com.enigmacamp.livecodeloan.service.CustomerService;
import com.enigmacamp.livecodeloan.service.InstalmentTypeService;
import com.enigmacamp.livecodeloan.service.LoanTransactionService;
import com.enigmacamp.livecodeloan.service.LoanTypeService;
import com.enigmacamp.livecodeloan.utils.constant.ApprovalStatus;

import jakarta.transaction.Transactional;

@Service
public class LoanTransactionServiceImpl implements LoanTransactionService {
  
  @Autowired
  private LoanTransactionRepository loanTransactionRepository;

  @Autowired
  private LoanTypeService loanTypeService;

  @Autowired
  private InstalmentTypeService instalmentTypeService;

  @Autowired
  private CustomerService customerService;

  @Autowired
  private UserRepository userRepository;

  @Override
  @Transactional
  public TransactionalResponseLoanDto createTrxLoan(TransactionalRequestLoanDto transactionalRequestLoanDto) {

    LoanType loanType = this.loanTypeService.getLoanTypeById(
      transactionalRequestLoanDto
      .getLoanType()
      .getId()
    );

    InstalmentType instalmentType = this.instalmentTypeService.getInstalmentTypeById(
      transactionalRequestLoanDto
      .getInstalmentType()
      .getId()
    );

    Customer customer = this.customerService.getCustomerById(
      transactionalRequestLoanDto
      .getCustomer()
      .getId()
    );

    LoanTransaction loanTransaction = LoanTransaction.builder()
    .customer(customer)
    .loanType(loanType)
    .instalmentType(instalmentType)
    .nominal(transactionalRequestLoanDto.getNominal())
    .createdAt(new Date())
    .build();

    this.loanTransactionRepository.save(loanTransaction);

    return this.wrapperResponse(loanTransaction);
  }

  @Override
  public TransactionalResponseLoanDto getTrxLoanById(String id) {
    LoanTransaction loanTransaction =  this.loanTransactionRepository.findById(id).orElseThrow(
      () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Loan Transaction Not FoundL")
    );

    return this.wrapperResponse(loanTransaction);
  }

  @Override
  @Transactional
  public TransactionalResponseLoanDto ApproveTrxByAdmin(String adminId, ApproveTransactionByAdminDto approveTransactionByAdminDto) {
    User user = this.userRepository.findById(adminId).orElseThrow(
      () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found")
    );

    LoanTransaction loanTransaction =  this.loanTransactionRepository.findById(
      approveTransactionByAdminDto.getLoanTransactionId()).orElseThrow(
      () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Loan Transaction Not Found")
    );

    loanTransaction.setApprovedBy(user.getEmail());
    loanTransaction.setApprovedAt(new Date());
    loanTransaction.setApprovalStatus(ApprovalStatus.APPROVED);
    loanTransaction.setUpdatedAt(new Date());

    this.loanTransactionRepository.save(loanTransaction);

    return this.wrapperResponse(loanTransaction);

  }

  private TransactionalResponseLoanDto wrapperResponse(LoanTransaction loanTransaction) {
    return TransactionalResponseLoanDto.builder()
    .id(loanTransaction.getId())
    .loanTypeId(loanTransaction.getLoanType().getId())
    .instalmentTypeId(loanTransaction.getInstalmentType().getId())
    .customerId(loanTransaction.getCustomer().getId())
    .nominal(loanTransaction.getNominal())
    .approvedAt(loanTransaction.getApprovedAt())
    .approvedBy(loanTransaction.getApprovedBy())
    .approvalStatus(loanTransaction.getApprovalStatus())
    .loanTransactionDetails(loanTransaction.getLoanTransactionDetails())
    .createdAt(loanTransaction.getCreatedAt())
    .updatedAt(loanTransaction.getUpdatedAt())
    .build();
  }
  
}
