package com.enigmacamp.livecodeloan.service;

import com.enigmacamp.livecodeloan.model.dto.req.ApproveTransactionByAdminDto;
import com.enigmacamp.livecodeloan.model.dto.req.TransactionalRequestLoanDto;
import com.enigmacamp.livecodeloan.model.dto.res.TransactionalResponseLoanDto;

public interface LoanTransactionService {
  TransactionalResponseLoanDto createTrxLoan(TransactionalRequestLoanDto transactionalRequestLoanDto);
  TransactionalResponseLoanDto getTrxLoanById(String id);
  TransactionalResponseLoanDto ApproveTrxByAdmin(String adminId, ApproveTransactionByAdminDto approveTransactionByAdminDto);
}
