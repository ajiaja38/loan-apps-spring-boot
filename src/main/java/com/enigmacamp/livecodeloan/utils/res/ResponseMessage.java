package com.enigmacamp.livecodeloan.utils.res;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMessage {
  
  private int code;
  private String message;

}
