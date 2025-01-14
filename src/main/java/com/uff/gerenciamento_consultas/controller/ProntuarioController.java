package com.uff.gerenciamento_consultas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uff.gerenciamento_consultas.dto.ProntuarioDTO;
import com.uff.gerenciamento_consultas.dto.ResponseDTO;
import com.uff.gerenciamento_consultas.service.ProntuarioService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


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
  
}
