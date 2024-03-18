package com.enigmacamp.livecodeloan.model.dto.res;

import java.util.Date;
import java.util.List;

import com.enigmacamp.livecodeloan.model.entity.LoanTransactionDetail;
import com.enigmacamp.livecodeloan.utils.constant.ApprovalStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionalResponseLoanDto {
  
  private String id;
  private String loanTypeId;
  private String instalmentTypeId;
  private String customerId;
  private Double nominal;
  private Date approvedAt;
  private String approvedBy;
  private ApprovalStatus approvalStatus;
  private List<LoanTransactionDetail> loanTransactionDetails;
  private Date createdAt;
  private Date updatedAt;
  
}
