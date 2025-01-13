package com.uff.gerenciamento_consultas.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsultaDTO {

  private LocalDate data;
  private LocalTime horario;

  //Apenas para editar consulta
  private String status;
  private List<String> sintomas;
  private List<String> diagnostico;
  private List<String> observacoes;

  //Apenas para criar consulta
  private String medico_cpf;
  private String paciente_cpf;
}
