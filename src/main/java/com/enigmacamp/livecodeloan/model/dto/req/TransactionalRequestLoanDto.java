package com.enigmacamp.livecodeloan.model.dto.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionalRequestLoanDto {
  
  private RequestIdTransactionalDto loanType;
  private RequestIdTransactionalDto instalmentType;
  private RequestIdTransactionalDto customer;
  private Double nominal;

}
