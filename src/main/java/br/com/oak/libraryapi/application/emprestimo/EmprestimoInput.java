package br.com.oak.libraryapi.application.emprestimo;

import static br.com.oak.libraryapi.Constantes.MAXIMO_TEMPO_EMPRESTIMO_EM_DIAS;

import br.com.oak.libraryapi.domain.Emprestimo;
import br.com.oak.libraryapi.domain.Exemplar;
import br.com.oak.libraryapi.domain.Usuario;
import java.util.Optional;

public interface EmprestimoInput {

  Long usuarioId();
  Integer prazoEntregaEmDias();

  default Emprestimo toModel(Usuario usuario, Exemplar exemplar){
    Integer tempoEmprestimoEmDias  = Optional.ofNullable(prazoEntregaEmDias()).orElse(
        MAXIMO_TEMPO_EMPRESTIMO_EM_DIAS);
    return new Emprestimo(usuario, exemplar, tempoEmprestimoEmDias);
  }
}
