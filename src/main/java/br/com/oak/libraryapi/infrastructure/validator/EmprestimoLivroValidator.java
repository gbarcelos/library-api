package br.com.oak.libraryapi.infrastructure.validator;

import br.com.oak.libraryapi.domain.Livro;
import br.com.oak.libraryapi.domain.Usuario;
import java.time.Clock;
import org.springframework.validation.Errors;

public class EmprestimoLivroValidator {

  private final Clock clock;
  private final Errors errors;

  public EmprestimoLivroValidator(Clock clock, Errors errors){
    this.clock = clock;
    this.errors = errors;
  }

  public void validate(Livro livro, Usuario usuario) {

    if(usuario.possuiEmprestimosExpirado(clock)) { //1
      errors.rejectValue("",
          "criarEmprestimoRequest.emprestimoNaoPermitido.emprestimoExpirado",
          "");
    }

    if (!livro.permiteSerEmprestado(usuario)) { //1
      errors.rejectValue("",
          "criarEmprestimoRequest.emprestimoNaoPermitido.exemplarRestrito",
          "");
    }

    if (!livro.disponivelParaEmprestimo(usuario)) { //1
      errors.rejectValue("",
          "criarEmprestimoRequest.emprestimoNaoPermitido.exemplarIndisponivel",
          "");
    }
  }
}
