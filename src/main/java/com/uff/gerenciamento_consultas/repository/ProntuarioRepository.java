package com.uff.gerenciamento_consultas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uff.gerenciamento_consultas.model.Prontuario;

public interface ProntuarioRepository extends JpaRepository<Prontuario, Long> {
  Optional<Prontuario> findByPacienteCpf(String cpf);
}
