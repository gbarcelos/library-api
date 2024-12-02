package br.com.oak.libraryapi.infrastructure.controller.response;

import br.com.oak.libraryapi.domain.Exemplar;

public record ExemplarResponse(Long id) {

  public static ExemplarResponse of(Exemplar exemplar) {
    return new ExemplarResponse(exemplar.getId());
  }
}
