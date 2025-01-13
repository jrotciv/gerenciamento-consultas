package com.uff.gerenciamento_consultas.model;

import jakarta.persistence.Entity;

@Entity
public class Paciente extends Usuario {
  private String convenio;
  private String nroConvenio;
}
