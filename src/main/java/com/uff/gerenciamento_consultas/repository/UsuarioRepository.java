package com.uff.gerenciamento_consultas.repository;

import java.util.Optional;
import com.uff.gerenciamento_consultas.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {
  Optional<Usuario> findByEmail(String email);
}
