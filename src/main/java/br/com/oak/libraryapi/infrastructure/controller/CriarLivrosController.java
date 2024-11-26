package br.com.oak.libraryapi.infrastructure.controller;

import br.com.oak.libraryapi.application.livro.CriarLivro;
import br.com.oak.libraryapi.domain.Livro;
import br.com.oak.libraryapi.infrastructure.controller.request.CriarLivroRequest;
import br.com.oak.libraryapi.infrastructure.controller.response.LivroResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CriarLivrosController {

  private CriarLivro criarLivro;

  public CriarLivrosController(CriarLivro criarLivro) {
    this.criarLivro = criarLivro;
  }

  @PostMapping(value = "/livros")
  public ResponseEntity<LivroResponse> criarLivro(
      @RequestBody @Valid CriarLivroRequest criarLivroRequest) {
    Livro livro = criarLivro.execute(criarLivroRequest);
    return ResponseEntity.ok(new LivroResponse(livro));
  }
}
