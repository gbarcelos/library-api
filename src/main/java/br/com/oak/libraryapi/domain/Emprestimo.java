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
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

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

  private LocalDateTime dataEmprestimo;

  @Positive
  private Integer prazoEntregaEmDias;

  private LocalDateTime dataEntrega;

  @Deprecated
  public Emprestimo() {
  }

  public Emprestimo(Usuario usuario, Exemplar exemplar, Integer prazoEntregaEmDias) {
    this.usuario = usuario;
    this.exemplar = exemplar;
    this.dataEmprestimo = LocalDateTime.now();
    this.prazoEntregaEmDias = prazoEntregaEmDias;
  }

  public boolean expirado(Clock clock) {
    var dataEmprestimoZonedDateTime = dataEmprestimo.atZone(ZoneId.systemDefault());
    var dataDevolucao = LocalDate
        .ofInstant(dataEmprestimoZonedDateTime.toInstant(), clock.getZone()).plusDays(prazoEntregaEmDias);

    return dataDevolucao.isBefore(LocalDate.now(clock));
  }

  public boolean foiEntregue() {
    return nonNull(dataEntrega);
  }

  public boolean emprestimoPertenceAoUsuario(Usuario usuario) {
    return this.usuario.equals(usuario);
  }

  public boolean podeSerDevolvido(Usuario usuario) {
    return !foiEntregue() && emprestimoPertenceAoUsuario(usuario);
  }

  public void devolver() {
    this.dataEntrega = LocalDateTime.now();
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

  public LocalDateTime getDataEmprestimo() {
    return dataEmprestimo;
  }

  public Integer getPrazoEntregaEmDias() {
    return prazoEntregaEmDias;
  }

  public LocalDateTime getDataEntrega() {
    return dataEntrega;
  }
}
