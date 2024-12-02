package br.com.oak.libraryapi.application.exemplar;

import br.com.oak.libraryapi.domain.Exemplar;
import br.com.oak.libraryapi.domain.Livro;
import br.com.oak.libraryapi.domain.TipoCirculacao;

public interface ExemplarInput {

  TipoCirculacao tipoCirculacao();

  default Exemplar toModel(Livro livro){
    return new Exemplar(livro, tipoCirculacao());
  }
}
