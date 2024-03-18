package com.enigmacamp.livecodeloan.model.dto.res;

import java.util.List;

import com.enigmacamp.livecodeloan.utils.constant.ERole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserResponse {
  private String email;
  private List<ERole> role;
}
