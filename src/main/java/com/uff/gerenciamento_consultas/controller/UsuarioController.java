package com.uff.gerenciamento_consultas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
  
  @GetMapping("/status")
  public String status() {
      return "Usuário está ativo";
  }
}
