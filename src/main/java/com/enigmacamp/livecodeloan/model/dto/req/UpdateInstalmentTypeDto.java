package com.enigmacamp.livecodeloan.model.dto.req;

import com.enigmacamp.livecodeloan.utils.constant.EInstalmentType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateInstalmentTypeDto {
  
  private String id;
  private EInstalmentType instalmentType;

}
