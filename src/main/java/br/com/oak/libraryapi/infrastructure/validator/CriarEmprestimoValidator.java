package br.com.oak.libraryapi.infrastructure.validator;

import br.com.oak.libraryapi.infrastructure.controller.request.CriarEmprestimoRequest;
import br.com.oak.libraryapi.infrastructure.repository.LivroRepository;
import br.com.oak.libraryapi.infrastructure.repository.UsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;
import java.time.Clock;
import java.util.Map;
import java.util.Objects;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.HandlerMapping;

@Component
public class CriarEmprestimoValidator implements Validator {

  private final HttpServletRequest request;
  private final Clock clock;
  private final LivroRepository livroRepository;
  private final UsuarioRepository usuarioRepository;

  public CriarEmprestimoValidator(HttpServletRequest request,
      Clock clock,
      LivroRepository livroRepository,
      UsuarioRepository usuarioRepository){
    this.request = request;
    this.clock = clock;
    this.livroRepository = livroRepository;
    this.usuarioRepository = usuarioRepository;
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return CriarEmprestimoRequest.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) { //7
    if (errors.hasErrors()) { //1
      return;
    }
    var request = (CriarEmprestimoRequest) target; //1

    var usuario = usuarioRepository.findById(
        request.usuarioId()).orElse(null); //1
    Assert.state(Objects.nonNull(usuario),"Usuario nao encontrado");

    if (!usuario.podeSolicitarEmprestimo()) { //1
      errors.rejectValue(null,
          "criarEmprestimoRequest.emprestimoNaoPermitido.numeroExemplares",
          "");
    }

    if (!usuario.prazoEntregaValidoParaUsuario(request.prazoEntregaEmDias())) { //1
      errors.rejectValue("prazoEntregaEmDias",
          "criarEmprestimoRequest.prazoEntregaEmDias.prazoEntregaInvalido",
          "");
    }

    var livro = livroRepository.findByIsbn(getIsbnFromRequest()).orElse(null); //1
    Assert.state(Objects.nonNull(livro),"Livro nao encontrado");

    new EmprestimoLivroValidator(clock, errors).validate(livro, usuario); //1
  }

  private String getIsbnFromRequest() {
    Map<String, String> variaveisUrl = (Map<String, String>) request
        .getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
    return variaveisUrl.get("isbn");
  }
}
