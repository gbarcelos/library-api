package br.com.oak.libraryapi.application.usuario;

import br.com.oak.libraryapi.domain.Usuario;
import br.com.oak.libraryapi.infrastructure.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class CriarUsuario {

  private final UsuarioRepository repository;

  public CriarUsuario(UsuarioRepository usuarioRepository) {
    this.repository = usuarioRepository;
  }

  @Transactional
  public Usuario execute(@Valid UsuarioInput input) {
    return repository.save(input.toModel());
  }
}
