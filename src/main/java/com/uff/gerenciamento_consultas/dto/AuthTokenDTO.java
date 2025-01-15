package com.uff.gerenciamento_consultas.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthTokenDTO {
  private String token;
  private String type;
}
