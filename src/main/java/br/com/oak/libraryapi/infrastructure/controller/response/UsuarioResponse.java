package br.com.oak.libraryapi.infrastructure.controller.response;

import br.com.oak.libraryapi.domain.Usuario;

public record UsuarioResponse(Long id) {

  public static UsuarioResponse of(Usuario usuario) {
    return new UsuarioResponse(usuario.getId());
  }
}
