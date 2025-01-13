package com.uff.gerenciamento_consultas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uff.gerenciamento_consultas.dto.UsuarioDTO;
import com.uff.gerenciamento_consultas.model.Usuario;
import com.uff.gerenciamento_consultas.service.UsuarioService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

  @Autowired
  private UsuarioService usuarioService;
  
  @GetMapping("/status")
  public ResponseEntity<String> status() {
      return ResponseEntity.ok("Usuário está ativo");
  }

  @PostMapping("/cadastrar")
  public ResponseEntity<Object> cadastrar(@RequestBody UsuarioDTO usuario) {
      try {
          usuarioService.save(usuario);
          return ResponseEntity.status(HttpStatus.CREATED).body("Usuário cadastrado com sucesso!");
      } catch (Exception e) {
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
      }
  }

  @PostMapping("/login")
  public ResponseEntity<Object> login(@RequestBody Usuario usuario) {
      try {
          return ResponseEntity.ok(usuarioService.login(usuario));
      } catch (Exception e) {
          return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
      }
  }
  
  
}
