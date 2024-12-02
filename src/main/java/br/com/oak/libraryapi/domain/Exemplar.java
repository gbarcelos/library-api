package br.com.oak.libraryapi.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class Exemplar {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Enumerated(EnumType.STRING)
  private TipoCirculacao tipoCirculacao;

  @NotNull
  @ManyToOne
  @JoinColumn(name = "livro_id", nullable = false)
  private Livro livro;

  public Exemplar(Livro livro, TipoCirculacao tipoCirculacao) {
    this.livro = livro;
    this.tipoCirculacao = tipoCirculacao;
  }

  public Long getId() {
    return id;
  }

  public TipoCirculacao getTipoCirculacao() {
    return tipoCirculacao;
  }

  public Livro getLivro() {
    return livro;
  }
}
