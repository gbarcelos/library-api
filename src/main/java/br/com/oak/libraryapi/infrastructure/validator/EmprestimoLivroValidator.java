package br.com.oak.libraryapi.infrastructure.validator;

import br.com.oak.libraryapi.domain.Livro;
import br.com.oak.libraryapi.domain.Usuario;
import org.springframework.validation.Errors;

public class EmprestimoLivroValidator {

  public void valida(Livro livro, Usuario usuario, Errors errors) {

    if (!livro.permiteSerEmprestado(usuario)) { //1
      errors.rejectValue("", "criarEmprestimoRequest.emprestimoNaoPermitido.exemplarRestrito", "");
    }

    if (!livro.disponivelParaEmprestimo(usuario)) { //1
      errors.rejectValue("", "criarEmprestimoRequest.emprestimoNaoPermitido.exemplarIndisponivel", "");
    }
  }
}
