package com.enigmacamp.livecodeloan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enigmacamp.livecodeloan.model.dto.req.ApproveTransactionByAdminDto;
import com.enigmacamp.livecodeloan.model.dto.req.TransactionalRequestLoanDto;
import com.enigmacamp.livecodeloan.model.dto.res.TransactionalResponseLoanDto;
import com.enigmacamp.livecodeloan.service.LoanTransactionService;
import com.enigmacamp.livecodeloan.utils.constant.ApiPathConstant;
import com.enigmacamp.livecodeloan.utils.res.Response;

@RestController
@RequestMapping(
  ApiPathConstant.API +
  ApiPathConstant.VERSION +
  ApiPathConstant.TRANSACTION
)
public class TransactionalRequestLoanController {
  
  @Autowired
  private LoanTransactionService loanTransactionService;

  @PostMapping
  @PreAuthorize("hasRole('ROLE_CUSTOMER')")
  public ResponseEntity<Response<TransactionalResponseLoanDto>> requestTransactionalLoanHandler(
    @RequestBody
    TransactionalRequestLoanDto transactionalRequestLoanDto
  ) {
    return ResponseEntity
    .status(HttpStatus.CREATED)
    .body(
      new Response<TransactionalResponseLoanDto>(
        "Berhasil create request transaction loan",
        this.loanTransactionService.createTrxLoan(transactionalRequestLoanDto)
      )
    );
  }

  @GetMapping("{id}")
  public ResponseEntity<Response<TransactionalResponseLoanDto>> getTransactionalLoanHandler(
    @PathVariable String id
  ) {
    return ResponseEntity
    .status(HttpStatus.OK)
    .body(
      new Response<TransactionalResponseLoanDto>(
        "Berhasil Get Transaction Load",
        this.loanTransactionService.getTrxLoanById(id)
      )
    );
  }

  @PutMapping("{id}/approve")
  @PreAuthorize("hasAnyRole('ROLE_STAFF', 'ROLE_ADMIN')")
  public ResponseEntity<Response<TransactionalResponseLoanDto>> ApprovalStatusTrxLoanByAdmin(
    @PathVariable String id,
    @RequestBody ApproveTransactionByAdminDto approveTransactionByAdminDto
  ) {
    return ResponseEntity
    .status(HttpStatus.CREATED)
    .body(
      new Response<TransactionalResponseLoanDto>(
        "Berhasil Approve Transaction Loan oleh admin",
        this.loanTransactionService.ApproveTrxByAdmin(id, approveTransactionByAdminDto)
      )
    );
  }
}
