package com.uff.gerenciamento_consultas.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.uff.gerenciamento_consultas.dto.ConsultaDTO;
import com.uff.gerenciamento_consultas.dto.ResponseDTO;
import com.uff.gerenciamento_consultas.model.Consulta;
import com.uff.gerenciamento_consultas.model.Medico;
import com.uff.gerenciamento_consultas.model.Paciente;
import com.uff.gerenciamento_consultas.model.Usuario;
import com.uff.gerenciamento_consultas.repository.ConsultaRepository;
import com.uff.gerenciamento_consultas.repository.MedicoRepository;
import com.uff.gerenciamento_consultas.repository.PacienteRepository;
import com.uff.gerenciamento_consultas.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class ConsultaService {
  
  @Autowired
  private ConsultaRepository consultaRepository;

  @Autowired
  private UsuarioRepository usuarioRepository;

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

  public List<Consulta> listar(String status) {
    String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    Usuario usuario = usuarioRepository.findByEmail(email)
      .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));


    if (usuario instanceof Medico) {
      return consultaRepository.findByMedicoCpfAndStatus(usuario.getCpf(), status);
    } else {
      return consultaRepository.findByPacienteCpfAndStatus(usuario.getCpf(), status);
    }
  }

  @Transactional
  public ResponseDTO editar(Long id, ConsultaDTO consultaDTO) {
    Consulta consulta = consultaRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));
      
    boolean consultaJaExiste = consultaRepository.findByMedicoAndDataAndHorario(consulta.getMedico(), consultaDTO.getData(), consultaDTO.getHorario()).isPresent();

    if (consultaJaExiste) {
      throw new RuntimeException("Horário indisponível, escolha outro horário");
    }

    consulta.setData(consultaDTO.getData());
    consulta.setHorario(consultaDTO.getHorario());
    consulta.setSintomas(consultaDTO.getSintomas());
    consulta.setDiagnostico(consultaDTO.getDiagnostico());
    consulta.setObservacoes(consultaDTO.getObservacoes());

    consultaRepository.save(consulta);

    return new ResponseDTO("Consulta atualizada");
  }

  @Transactional
  public ResponseDTO alterarStatus(Long id, String status) {
    Consulta consulta = consultaRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));
    
    consulta.setStatus(status);

    consultaRepository.save(consulta);

    return new ResponseDTO("Status da consulta atualizado");
  }
}
