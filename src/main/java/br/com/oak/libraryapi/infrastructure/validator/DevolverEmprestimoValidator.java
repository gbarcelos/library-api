package br.com.oak.libraryapi.infrastructure.validator;

import br.com.oak.libraryapi.infrastructure.controller.request.DevolverEmprestimoRequest;
import br.com.oak.libraryapi.infrastructure.repository.EmprestimoRepository;
import br.com.oak.libraryapi.infrastructure.repository.UsuarioRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class DevolverEmprestimoValidator implements Validator {

  private final EmprestimoRepository emprestimoRepository;
  private final UsuarioRepository usuarioRepository;

  public DevolverEmprestimoValidator(EmprestimoRepository emprestimoRepository,
      UsuarioRepository usuarioRepository){
    this.emprestimoRepository = emprestimoRepository;
    this.usuarioRepository = usuarioRepository;
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return DevolverEmprestimoRequest.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    if (errors.hasErrors()) { //1
      return;
    }
    var request = (DevolverEmprestimoRequest) target; //1

    var emprestimoOptional = emprestimoRepository.findById(request.emprestimoId()); //1
    Assert.state(emprestimoOptional.isPresent(),"O empréstimo precisa existir para ser devolvido");

    var emprestimo = emprestimoOptional.get();

    if (emprestimo.foiEntregue()){ //1
      errors.rejectValue(null,
          "devolverEmprestimoRequest.devolucaoNaoPermitido.exemplarDevolvido",
          "");
    }

    var usuarioOptional = usuarioRepository.findById(request.usuarioId()); //1
    Assert.state(usuarioOptional.isPresent(),"Usuario nao encontrado");

    if (!emprestimo.emprestimoPertenceAoUsuario(usuarioOptional.get())){ //1
      errors.rejectValue(null,
          "devolverEmprestimoRequest.devolucaoNaoPermitido.emprestimoNaoPerteceAoUsuario",
          "");
    }
  }
}
