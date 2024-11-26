package br.com.oak.libraryapi.infrastructure.controller.response;

import br.com.oak.libraryapi.domain.Livro;

public class LivroResponse {

  private Long id;

  private String titulo;

  private String preco;

  private String isbn;

  public LivroResponse(Livro livro) {
    this.id = livro.getId();
    this.titulo= livro.getTitulo();
    this.preco = livro.getPreco().toString();
    this.isbn = livro.getIsbn();
  }

  public Long getId() {
    return id;
  }

  public String getTitulo() {
    return titulo;
  }

  public String getPreco() {
    return preco;
  }

  public String getIsbn() {
    return isbn;
  }
}
