package com.enigmacamp.livecodeloan.model.dto.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApproveTransactionByAdminDto {
  
  private String loanTransactionId;
  private int interestRates;
  
}
