package br.com.oak.libraryapi.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Usuario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  private String email;

  @NotNull
  @Enumerated(EnumType.STRING)
  private TipoUsuario tipoUsuario;

  @OneToMany(mappedBy = "usuario")
  private List<Emprestimo> emprestimos = new ArrayList<>();

  @Deprecated
  public Usuario() {
  }

  public Usuario(String email, TipoUsuario tipoUsuario) {
    this.email = email;
    this.tipoUsuario = tipoUsuario;
  }

  public boolean podeSolicitarEmprestimo(){
    long numeroEmprestimosAtivos = this.emprestimos.stream().filter(emprestimo -> !emprestimo.foiEntregue()).count();
    return tipoUsuario.permiteNovoEmprestimo(numeroEmprestimosAtivos);
  }

  public boolean prazoEntregaValidoParaUsuario(Integer prazoEntregaEmDias){
    return tipoUsuario.permiteTempoEmprestimo(prazoEntregaEmDias);
  }

  public Long getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  public TipoUsuario getTipoUsuario() {
    return tipoUsuario;
  }

  public List<Emprestimo> getEmprestimos() {
    return emprestimos;
  }
}
