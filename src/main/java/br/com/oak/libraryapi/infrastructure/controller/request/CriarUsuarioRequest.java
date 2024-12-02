package br.com.oak.libraryapi.infrastructure.controller.request;

import br.com.oak.libraryapi.application.usuario.UsuarioInput;
import br.com.oak.libraryapi.domain.TipoUsuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CriarUsuarioRequest(

    @NotBlank
    String email,

    @NotNull
    TipoUsuario tipoUsuario
) implements UsuarioInput {

}
