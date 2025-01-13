package com.uff.gerenciamento_consultas.repository;

import com.uff.gerenciamento_consultas.model.Medico;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, String> {
  Medico findByCrm(String crm);
}
