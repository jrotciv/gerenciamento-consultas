package com.uff.gerenciamento_consultas.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Prontuario {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String diagnostico;
  private List<String> medicamentos;
  private List<String> tratamentos;
  private List<String> observacoes;

  @CreatedDate
  @Column(updatable = false)
  private LocalDate criadoEm;

  @LastModifiedDate
  private LocalDate atualizadoEm;
}
