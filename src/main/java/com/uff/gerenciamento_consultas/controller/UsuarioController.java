package com.uff.gerenciamento_consultas.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
  
  @GetMapping("/status")
  public ResponseEntity<String> status() {
      return ResponseEntity.ok("Usuário está ativo");
  }
}
