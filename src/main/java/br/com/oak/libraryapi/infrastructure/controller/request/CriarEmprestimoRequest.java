package br.com.oak.libraryapi.infrastructure.controller.request;

import br.com.oak.libraryapi.application.emprestimo.EmprestimoInput;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;

public record CriarEmprestimoRequest(
    @NotNull
    Long usuarioId,

    @Max(value = 60)
    Integer prazoEntregaEmDias

) implements EmprestimoInput {

}
