package br.com.oak.libraryapi.infrastructure.controller.response;

import br.com.oak.libraryapi.domain.Emprestimo;

public record EmprestimoResponse(Long id) {
  public static EmprestimoResponse of(Emprestimo emprestimo) {
    return new EmprestimoResponse(emprestimo.getId());
  }
}
