package br.com.oak.libraryapi.infrastructure.controller.request;

import br.com.oak.libraryapi.application.emprestimo.DevolverEmprestimoInput;
import br.com.oak.libraryapi.domain.Emprestimo;
import br.com.oak.libraryapi.domain.Usuario;
import br.com.oak.libraryapi.infrastructure.annotation.ExistsValue;
import jakarta.validation.constraints.NotNull;

public record DevolverEmprestimoRequest(
    @NotNull
    @ExistsValue(domainClass = Usuario.class)
    Long usuarioId,

    @NotNull
    @ExistsValue(domainClass = Emprestimo.class)
    Long emprestimoId
) implements DevolverEmprestimoInput {
}
