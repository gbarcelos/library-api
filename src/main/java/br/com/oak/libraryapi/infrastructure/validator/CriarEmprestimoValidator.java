package br.com.oak.libraryapi.infrastructure.validator;

import br.com.oak.libraryapi.domain.Livro;
import br.com.oak.libraryapi.domain.Usuario;
import br.com.oak.libraryapi.infrastructure.controller.request.CriarEmprestimoRequest;
import br.com.oak.libraryapi.infrastructure.repository.LivroRepository;
import br.com.oak.libraryapi.infrastructure.repository.UsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.HandlerMapping;

@Component
public class CriarEmprestimoValidator implements Validator {

  @Autowired
  private HttpServletRequest request;

  @Autowired
  private LivroRepository livroRepository;

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Override
  public boolean supports(Class<?> clazz) {
    return CriarEmprestimoRequest.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    if (errors.hasErrors()) {
      return;
    }
    CriarEmprestimoRequest request = (CriarEmprestimoRequest) target; //1

    Optional<Usuario> usuarioOptional = usuarioRepository.findById(
        request.usuarioId()); //1
    if (usuarioOptional.isEmpty()) {
      errors.rejectValue("usuarioId",
          "criarEmprestimoRequest.usuarioId.notfound",
          "");
      return;
    }

    Usuario usuario = usuarioOptional.get();

    if (!usuario.podeSolicitarEmprestimo()) {
      errors.rejectValue(null,
          "criarEmprestimoRequest.emprestimoNaoPermitido.numeroExemplares",
          "");
    }

    if (!usuario.prazoEntregaValidoParaUsuario(request.prazoEntregaEmDias())) {
      errors.rejectValue("prazoEntregaEmDias",
          "criarEmprestimoRequest.prazoEntregaEmDias.prazoEntregaInvalido",
          "");
    }

    Optional<Livro> livroOptional = livroRepository.findByIsbn(getIsbnFromRequest()); //1
    if (livroOptional.isEmpty()) {
      errors.rejectValue(null,
          "criarEmprestimoRequest.livro.notfound",
          "");
      return;
    }

    new EmprestimoLivroValidator().valida(livroOptional.get(), usuario, errors); //1
  }

  private String getIsbnFromRequest() {
    Map<String, String> variaveisUrl = (Map<String, String>) request
        .getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
    return variaveisUrl.get("isbn");
  }
}
