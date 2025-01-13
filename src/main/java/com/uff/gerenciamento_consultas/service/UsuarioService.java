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
import com.uff.gerenciamento_consultas.model.Usuario;
import com.uff.gerenciamento_consultas.repository.UsuarioRepository;

import jakarta.transaction.Transactional;


@Service
public class UsuarioService {
  
  @Value("${security.token.secret}")
  private String secret;

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Transactional
  public Usuario save(Usuario usuario) {
      if(usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
          throw new RuntimeException("Usuário já cadastrado");
      }

      var password = passwordEncoder.encode(usuario.getSenha());
      usuario.setSenha(password);

      this.usuarioRepository.save(usuario);
      return usuario;
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
}
