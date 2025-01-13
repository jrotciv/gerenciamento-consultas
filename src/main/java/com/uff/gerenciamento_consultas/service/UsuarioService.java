package com.uff.gerenciamento_consultas.service;

import java.time.Duration;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.uff.gerenciamento_consultas.dto.AuthTokenDTO;
import com.uff.gerenciamento_consultas.dto.UsuarioDTO;
import com.uff.gerenciamento_consultas.model.Medico;
import com.uff.gerenciamento_consultas.model.Paciente;
import com.uff.gerenciamento_consultas.model.Usuario;
import com.uff.gerenciamento_consultas.repository.MedicoRepository;
import com.uff.gerenciamento_consultas.repository.PacienteRepository;
import com.uff.gerenciamento_consultas.repository.UsuarioRepository;

import jakarta.transaction.Transactional;


@Service
public class UsuarioService {
  
  @Value("${security.token.secret}")
  private String secret;

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Autowired
  private MedicoRepository medicoRepository;

  @Autowired
  private PacienteRepository pacienteRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Transactional
  public void save(UsuarioDTO usuario) {
      if(usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
          throw new RuntimeException("Usuário já cadastrado");
      }

      var password = passwordEncoder.encode(usuario.getSenha());
      usuario.setSenha(password);

      if (usuario.getCrm() != null) {
        Medico medico = new Medico();
        populateUsuario(medico, usuario);
        medico.setCrm(usuario.getCrm());
        medico.setEspecialidade(usuario.getEspecialidade());
    
        medicoRepository.save(medico);
    } else if (usuario.getConvenio() != null) {
        Paciente paciente = new Paciente();
        populateUsuario(paciente, usuario);
        paciente.setConvenio(usuario.getConvenio());
        paciente.setNroConvenio(usuario.getNroConvenio());
    
        pacienteRepository.save(paciente);
    }else {
        throw new RuntimeException("Tipo de usuário inválido! Preencha os campos corretamente para médico ou paciente");
      }
  }

  public AuthTokenDTO login(Usuario usuario) {
      var usuarioEncontrado = usuarioRepository.findByEmail(usuario.getEmail())
              .orElseThrow(() -> new RuntimeException("Credenciais inválidas"));

      if(!this.passwordEncoder.matches(usuario.getSenha(), usuarioEncontrado.getSenha())) {
          throw new RuntimeException("Credenciais inválidas");
      }

      Algorithm algorithm = Algorithm.HMAC256(secret);
      var token = JWT.create().withIssuer(secret).withExpiresAt(Instant.now().plus(Duration.ofHours(2))).withSubject(usuario.getEmail()).sign(algorithm);

      return new AuthTokenDTO(token);
  }

  private void populateUsuario(Usuario usuario, UsuarioDTO usuarioDTO) {
      usuario.setCpf(usuarioDTO.getCpf());
      usuario.setNome(usuarioDTO.getNome());
      usuario.setEmail(usuarioDTO.getEmail());
      usuario.setSenha(usuarioDTO.getSenha());
      usuario.setContato(usuarioDTO.getContato());
      usuario.setDataNascimento(usuarioDTO.getDataNascimento());
  }
}
