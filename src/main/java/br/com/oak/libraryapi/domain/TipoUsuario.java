package br.com.oak.libraryapi.domain;

import static br.com.oak.libraryapi.Constantes.MAXIMO_TEMPO_EMPRESTIMO_EM_DIAS;
import static br.com.oak.libraryapi.Constantes.MAXIMO_NUMERO_EMPRESTIMOS_ATIVOS;
import static java.util.Objects.isNull;

public enum TipoUsuario {
  PADRAO {
    @Override
    boolean permiteTempoEmprestimo(Integer prazoEntregaEmDias) {
      if (isNull(prazoEntregaEmDias)){
        return false;
      }
      return prazoEntregaEmDias >= 1 && prazoEntregaEmDias <= MAXIMO_TEMPO_EMPRESTIMO_EM_DIAS;
    }

    @Override
    protected boolean permiteNovoEmprestimo(long numeroEmprestimosAtivos) {
      return numeroEmprestimosAtivos < MAXIMO_NUMERO_EMPRESTIMOS_ATIVOS;
    }

  }, PESQUISADOR {
    @Override
    boolean permiteTempoEmprestimo(Integer prazoEntregaEmDias) {
      return true;
    }

    @Override
    protected boolean permiteNovoEmprestimo(long numeroEmprestimosAtivos) {
      return true;
    }
  };

  abstract boolean permiteTempoEmprestimo(Integer prazoEntregaEmDias);

  protected abstract boolean permiteNovoEmprestimo(long numeroEmprestimosAtivos);
}
