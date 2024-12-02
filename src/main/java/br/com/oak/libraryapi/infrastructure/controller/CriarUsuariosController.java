package br.com.oak.libraryapi.infrastructure.controller;

import br.com.oak.libraryapi.application.usuario.CriarUsuario;
import br.com.oak.libraryapi.domain.Usuario;
import br.com.oak.libraryapi.infrastructure.controller.request.CriarUsuarioRequest;
import br.com.oak.libraryapi.infrastructure.controller.response.UsuarioResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CriarUsuariosController {

  private final CriarUsuario criarUsuario;

  public CriarUsuariosController(CriarUsuario criarUsuario) {
    this.criarUsuario = criarUsuario;
  }

  @PostMapping(value = "/usuarios")
  public ResponseEntity<UsuarioResponse> criarUsuario(
      @RequestBody @Valid CriarUsuarioRequest criarUsuarioRequest) {
    Usuario usuario = criarUsuario.execute(criarUsuarioRequest);
    return ResponseEntity.ok(UsuarioResponse.of(usuario));
  }

}
