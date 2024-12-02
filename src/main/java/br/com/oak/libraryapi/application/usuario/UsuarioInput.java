package br.com.oak.libraryapi.application.usuario;

import br.com.oak.libraryapi.domain.TipoUsuario;
import br.com.oak.libraryapi.domain.Usuario;

public interface UsuarioInput {

  String email();
  TipoUsuario tipoUsuario();

  default Usuario toModel(){
    return new Usuario(email(), tipoUsuario());
  }
}
