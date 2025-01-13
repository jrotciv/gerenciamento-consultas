package com.uff.gerenciamento_consultas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.uff.gerenciamento_consultas.dto.ConsultaDTO;
import com.uff.gerenciamento_consultas.service.ConsultaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/consulta")
public class ConsultaController {
  
  @Autowired
  private ConsultaService consultaService;

  @PostMapping("/agendar")
  public ResponseEntity<Object> agendarConsulta(@RequestBody ConsultaDTO consultaDTO) {
    try {
      return ResponseEntity.ok(consultaService.criar(consultaDTO));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

  @GetMapping("/listar")
  public ResponseEntity<Object> listarConsultas(@RequestParam String status) {
    try {
      return ResponseEntity.ok(consultaService.listar(status));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }
  
}
