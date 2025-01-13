package br.com.oak.libraryapi.domain;

import static java.util.Objects.nonNull;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;

@Entity
public class Emprestimo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @ManyToOne
  @JoinColumn(name = "usuario_id", nullable = false)
  private Usuario usuario;

  @NotNull
  @ManyToOne
  @JoinColumn(name = "exemplar_id", nullable = false)
  private Exemplar exemplar;

  private LocalDate dataEmprestimo;

  @Positive
  private Integer prazoEntregaEmDias;

  private LocalDate dataEntrega;

  @Deprecated
  public Emprestimo() {
  }

  public Emprestimo(Usuario usuario, Exemplar exemplar, Integer prazoEntregaEmDias) {
    this.usuario = usuario;
    this.exemplar = exemplar;
    this.dataEmprestimo = LocalDate.now();
    this.prazoEntregaEmDias = prazoEntregaEmDias;
  }

  public boolean foiEntregue() {
    return nonNull(dataEntrega);
  }

  public Long getId() {
    return id;
  }

  public Exemplar getExemplar() {
    return exemplar;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public LocalDate getDataEmprestimo() {
    return dataEmprestimo;
  }

  public Integer getPrazoEntregaEmDias() {
    return prazoEntregaEmDias;
  }

  public LocalDate getDataEntrega() {
    return dataEntrega;
  }
}
