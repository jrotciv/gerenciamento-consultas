package com.uff.gerenciamento_consultas.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {
  private String cpf;
  private String nome;
  private String email;
  private String senha;
  private String contato;
  private LocalDate dataNascimento;

  //Medico
  private String crm;
  private String especialidade;

  //Paciente
  private String convenio;
  private String nroConvenio;
}
