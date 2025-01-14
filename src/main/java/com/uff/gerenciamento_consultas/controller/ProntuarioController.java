package com.uff.gerenciamento_consultas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uff.gerenciamento_consultas.dto.ProntuarioDTO;
import com.uff.gerenciamento_consultas.dto.ResponseDTO;
import com.uff.gerenciamento_consultas.service.ProntuarioService;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;




@Controller
@RequestMapping("/prontuarios")
public class ProntuarioController {
  
  @Autowired
  private ProntuarioService prontuarioService;

  @PostMapping("")
  public ResponseEntity<Object> criar(@RequestBody ProntuarioDTO prontuario) {
    try {
      return ResponseEntity.ok(prontuarioService.criar(prontuario));
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(new ResponseDTO(e.getMessage()));
    }
  }

  @GetMapping("/{cpf}")
  public ResponseEntity<Object> visualizar(@PathVariable String cpf) {
    try {
      return ResponseEntity.ok(prontuarioService.visualizar(cpf));
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(new ResponseDTO(e.getMessage()));
    }
  }
  
  @PutMapping("/{cpf}")
  public ResponseEntity<Object> atualizar(@PathVariable String cpf, @RequestBody ProntuarioDTO prontuario) {
    try {
      return ResponseEntity.ok(prontuarioService.atualizar(cpf, prontuario));
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(new ResponseDTO(e.getMessage()));
    }
  }
}
