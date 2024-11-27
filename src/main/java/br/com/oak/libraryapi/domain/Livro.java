package br.com.oak.libraryapi.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Livro {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  private String titulo;

  @NotNull
  @Positive
  @Digits(integer = 3, fraction = 2)
  private BigDecimal preco;

  @NotBlank
  private String isbn;


  @Deprecated
  public Livro() {
  }

  public Livro(Long id) {
    this.id = id;
  }

  public Livro(@NotBlank String titulo,
      @NotNull @Positive @Digits(integer = 3, fraction = 2) BigDecimal preco,
      @NotBlank String isbn) {
    this.titulo = titulo;
    this.preco = preco;
    this.isbn = isbn;
  }

  public Long getId() {
    return this.id;
  }

  public String getTitulo() {
    return this.titulo;
  }

  public BigDecimal getPreco() {
    return preco;
  }

  public String getIsbn() {
    return isbn;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Livro livro = (Livro) o;
    return Objects.equals(isbn, livro.isbn);
  }

  @Override
  public int hashCode() {
    return Objects.hash(isbn);
  }

  @Override
  public String toString() {
    return "Livro{" +
        "id=" + id +
        ", titulo='" + titulo + '\'' +
        ", preco=" + preco +
        ", isbn='" + isbn + '\'' +
        '}';
  }
}
