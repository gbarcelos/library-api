package br.com.oak.libraryapi.infrastructure.controller;

import br.com.oak.libraryapi.application.exemplar.CriarExemplar;
import br.com.oak.libraryapi.domain.Exemplar;
import br.com.oak.libraryapi.domain.Livro;
import br.com.oak.libraryapi.infrastructure.controller.request.CriarExemplarRequest;
import br.com.oak.libraryapi.infrastructure.controller.response.ExemplarResponse;
import br.com.oak.libraryapi.infrastructure.repository.LivroRepository;
import jakarta.validation.Valid;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CriarExemplaresController {

  private final CriarExemplar criarExemplar;
  private final LivroRepository livroRepository;

  public CriarExemplaresController(CriarExemplar criarExemplar, LivroRepository livroRepository) {
    this.criarExemplar = criarExemplar;
    this.livroRepository = livroRepository;
  }

  @PostMapping(value = "/livros/{isbn}/exemplar")
  public ResponseEntity<ExemplarResponse> criarExemplar(@PathVariable("isbn") String isbn,
      @RequestBody @Valid CriarExemplarRequest criarExemplarRequest) {
    Optional<Livro> livroOptional = livroRepository.findByIsbn(isbn);
    if (livroOptional.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    Exemplar exemplar = criarExemplar.execute(livroOptional.get(), criarExemplarRequest);
    return ResponseEntity.ok(ExemplarResponse.of(exemplar));
  }
}
