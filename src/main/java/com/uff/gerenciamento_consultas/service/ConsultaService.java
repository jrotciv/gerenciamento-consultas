package com.uff.gerenciamento_consultas.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uff.gerenciamento_consultas.dto.ConsultaDTO;
import com.uff.gerenciamento_consultas.dto.ResponseDTO;
import com.uff.gerenciamento_consultas.model.Consulta;
import com.uff.gerenciamento_consultas.model.Medico;
import com.uff.gerenciamento_consultas.model.Paciente;
import com.uff.gerenciamento_consultas.repository.ConsultaRepository;
import com.uff.gerenciamento_consultas.repository.MedicoRepository;
import com.uff.gerenciamento_consultas.repository.PacienteRepository;

import jakarta.transaction.Transactional;

@Service
public class ConsultaService {
  
  @Autowired
  private ConsultaRepository consultaRepository;

  @Autowired
  private MedicoRepository medicoRepository;

  @Autowired
  private PacienteRepository pacienteRepository;

  @Transactional
  public ResponseDTO criar(ConsultaDTO consultaDTO) {
    Medico medico = medicoRepository.findById(consultaDTO.getMedico_cpf())
      .orElseThrow(() -> new RuntimeException("Médico não encontrado"));
    
    Paciente paciente = pacienteRepository.findById(consultaDTO.getPaciente_cpf())
      .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

    boolean consultaJaExiste = consultaRepository.findByMedicoAndDataAndHorario(medico, consultaDTO.getData(), consultaDTO.getHorario()).isPresent();

    if (consultaJaExiste) {
      throw new RuntimeException("Horário indisponível, escolha outro horário");
    }

    Consulta consulta = new Consulta();
    consulta.setData(consultaDTO.getData());
    consulta.setHorario(consultaDTO.getHorario());
    consulta.setStatus("Pendente");
    consulta.setMedico(medico);
    consulta.setPaciente(paciente);
    consulta.setCriadaEm(LocalDate.now());

    consultaRepository.save(consulta);

    return new ResponseDTO("Consulta agendada com sucesso");
  }
}
