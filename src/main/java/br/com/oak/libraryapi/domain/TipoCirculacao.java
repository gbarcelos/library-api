package br.com.oak.libraryapi.domain;

import static br.com.oak.libraryapi.domain.TipoUsuario.PESQUISADOR;

public enum TipoCirculacao {
  RESTRITO {
    @Override
    boolean permiteSerEmprestado(Usuario usuario) {
      return PESQUISADOR == usuario.getTipoUsuario();
    }
  },

  LIVRE {
    @Override
    boolean permiteSerEmprestado(Usuario usuario) {
      return true;
    }
  };

  abstract boolean permiteSerEmprestado(Usuario usuario);
}
