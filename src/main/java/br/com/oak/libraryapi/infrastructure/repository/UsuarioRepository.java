package br.com.oak.libraryapi.infrastructure.repository;

import br.com.oak.libraryapi.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository  extends JpaRepository<Usuario, Long> {

}
