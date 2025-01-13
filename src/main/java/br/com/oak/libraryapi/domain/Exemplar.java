package br.com.oak.libraryapi.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

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

  @OneToMany(mappedBy = "exemplar")
  private List<Emprestimo> emprestimos = new ArrayList<>();

  @Version
  private int versao;

  @Deprecated
  public Exemplar() {
  }

  public Exemplar(Livro livro, TipoCirculacao tipoCirculacao) {
    this.livro = livro;
    this.tipoCirculacao = tipoCirculacao;
  }

  public boolean disponivelParaUsuario(Usuario usuario) {
    return permiteSerEmprestadoPara(usuario) && disponivelParaEmprestimo();
  }

  public boolean disponivelParaEmprestimo() {
    return this.emprestimos.isEmpty() || this.emprestimos.stream().allMatch(Emprestimo::foiEntregue);
  }

  public boolean permiteSerEmprestadoPara(Usuario usuario) {
    return tipoCirculacao.permiteSerEmprestado(usuario);
  }

  public Emprestimo criarEmprestimo(Usuario usuario, Integer tempoEmprestimoEmDias) {
    var emprestimo = new Emprestimo(usuario, this, tempoEmprestimoEmDias);
    emprestimos.add(emprestimo);
    versao++;
    return emprestimo;
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

  public List<Emprestimo> getEmprestimos() {
    return emprestimos;
  }
}
