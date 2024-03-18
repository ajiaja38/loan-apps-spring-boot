package com.enigmacamp.livecodeloan.model.dto.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateLoanTypeDto {

  private String type;
  private Double maxloan;
  
}
