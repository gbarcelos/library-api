package br.com.oak.libraryapi.infrastructure.controller.request;

import br.com.oak.libraryapi.application.exemplar.ExemplarInput;
import br.com.oak.libraryapi.domain.TipoCirculacao;
import jakarta.validation.constraints.NotNull;

public record CriarExemplarRequest(
    @NotNull
    TipoCirculacao tipoCirculacao
) implements ExemplarInput {

}
