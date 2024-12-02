package br.com.oak.libraryapi.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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

  public Usuario(String email, TipoUsuario tipoUsuario) {
    this.email = email;
    this.tipoUsuario = tipoUsuario;
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
}
