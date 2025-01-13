package com.uff.gerenciamento_consultas.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Consulta {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private LocalDate data;
  private LocalTime horario;
  private String status;
  private List<String> sintomas;
  private List<String> diagnostico;
  private List<String> observacoes;

  @CreatedDate
  @Column(updatable = false)
  private LocalDate criadaEm;

  @ManyToOne
  private Medico medico;

  @ManyToOne
  private Paciente paciente;
}
