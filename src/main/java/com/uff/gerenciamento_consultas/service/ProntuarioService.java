package com.uff.gerenciamento_consultas.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uff.gerenciamento_consultas.dto.ProntuarioDTO;
import com.uff.gerenciamento_consultas.dto.ResponseDTO;
import com.uff.gerenciamento_consultas.model.Paciente;
import com.uff.gerenciamento_consultas.model.Prontuario;
import com.uff.gerenciamento_consultas.repository.PacienteRepository;
import com.uff.gerenciamento_consultas.repository.ProntuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class ProntuarioService {
  
  @Autowired
  private ProntuarioRepository prontuarioRepository;

  @Autowired
  private PacienteRepository pacienteRepository;

  @Transactional
  public ResponseDTO criar(ProntuarioDTO prontuarioDTO) {
    if (prontuarioRepository.findByPacienteCpf(prontuarioDTO.getPacienteCpf()).isPresent()) {
      throw new RuntimeException("O paciente já possui um prontuário");
    }

    Paciente paciente = pacienteRepository.findById(prontuarioDTO.getPacienteCpf())
      .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

    Prontuario prontuario = new Prontuario();
    prontuario.setDiagnostico(prontuarioDTO.getDiagnostico());
    prontuario.setMedicamentos(prontuarioDTO.getMedicamentos());
    prontuario.setTratamentos(prontuarioDTO.getTratamentos());
    prontuario.setObservacoes(prontuarioDTO.getObservacoes());
    prontuario.setPaciente(paciente);
    prontuario.setCriadoEm(LocalDate.now());

    prontuarioRepository.save(prontuario);

    return new ResponseDTO("Prontuário criado com sucesso");
  }

  public Prontuario visualizar(String cpf) {
    return prontuarioRepository.findByPacienteCpf(cpf)
      .orElseThrow(() -> new RuntimeException("Prontuário não encontrado"));
  }
}
