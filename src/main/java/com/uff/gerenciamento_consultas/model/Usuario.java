package com.uff.gerenciamento_consultas.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario {
  @Id
  private String cpf;
  private String nome;
  private String email;
  private String senha;
  private String contato;
  private LocalDate dataNascimento;
}
