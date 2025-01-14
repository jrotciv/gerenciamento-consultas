package com.uff.gerenciamento_consultas.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProntuarioDTO {

  private List<String> diagnostico;
  private List<String> medicamentos;
  private List<String> tratamentos;
  private List<String> observacoes;

  private String pacienteCpf;
}
