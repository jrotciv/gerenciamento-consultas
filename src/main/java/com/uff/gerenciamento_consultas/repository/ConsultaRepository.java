package com.uff.gerenciamento_consultas.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uff.gerenciamento_consultas.model.Consulta;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import com.uff.gerenciamento_consultas.model.Medico;
import com.uff.gerenciamento_consultas.model.Paciente;


@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long>{
  Optional<Consulta> findByMedicoAndDataAndHorario(Medico medico, LocalDate data, LocalTime horario);

  List<Consulta> findByMedicoCpfAndStatus(String cpf, String status);
  List<Consulta> findByPacienteCpfAndStatus(String cpf, String status);
}
