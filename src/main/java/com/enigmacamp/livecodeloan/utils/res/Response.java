package com.enigmacamp.livecodeloan.utils.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Response<T> {
  private String message;
  private T data;
}
