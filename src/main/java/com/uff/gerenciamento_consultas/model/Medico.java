package com.uff.gerenciamento_consultas.model;

import jakarta.persistence.Entity;

@Entity
public class Medico extends Usuario {
  private String crm;
  private String especialidade;
}
