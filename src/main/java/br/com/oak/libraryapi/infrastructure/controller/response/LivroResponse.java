package br.com.oak.libraryapi.infrastructure.controller.response;

import br.com.oak.libraryapi.domain.Livro;
import java.math.BigDecimal;

public record LivroResponse(
    Long id,
    String titulo,
    BigDecimal preco,
    String isbn
) {

  public static LivroResponse of(Livro livro) {
    return new LivroResponse(livro.getId(),
        livro.getTitulo(),
        livro.getPreco(),
        livro.getIsbn());
  }
}
