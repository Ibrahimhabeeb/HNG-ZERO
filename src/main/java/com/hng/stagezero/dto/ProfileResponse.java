package com.hng.stagezero.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileResponse {
  private String status;
  private User user;
  private String fact;
  private Instant timestamp;

}
