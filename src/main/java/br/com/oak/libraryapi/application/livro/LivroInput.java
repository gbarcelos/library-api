package br.com.oak.libraryapi.application.livro;

import br.com.oak.libraryapi.domain.Livro;
import java.math.BigDecimal;

public interface LivroInput {

  String titulo();

  BigDecimal preco();

  String isbn();

  default Livro toModel() {
    return new Livro(titulo(),
        preco(),
        isbn());
  }
}
