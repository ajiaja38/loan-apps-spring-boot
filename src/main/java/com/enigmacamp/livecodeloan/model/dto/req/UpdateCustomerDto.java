package com.enigmacamp.livecodeloan.model.dto.req;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCustomerDto {
  
  private String firstName;
  private String lastName;

  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+7")
  private Date dateOfBirth;

  private String phone;
}
