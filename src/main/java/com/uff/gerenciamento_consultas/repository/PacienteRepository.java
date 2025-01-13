package com.uff.gerenciamento_consultas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uff.gerenciamento_consultas.model.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, String> {
  Paciente findByNroConvenio(String nroConvenio);
}
