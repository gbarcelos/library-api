package br.com.oak.libraryapi.infrastructure.controller;

import br.com.oak.libraryapi.application.emprestimo.CriarEmprestimo;
import br.com.oak.libraryapi.infrastructure.controller.request.CriarEmprestimoRequest;
import br.com.oak.libraryapi.infrastructure.controller.response.EmprestimoResponse;
import br.com.oak.libraryapi.infrastructure.validator.CriarEmprestimoValidator;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CriarEmprestimosController {

  private final CriarEmprestimoValidator criarEmprestimoValidator; //1
  private final CriarEmprestimo criarEmprestimo; //1

  public CriarEmprestimosController(CriarEmprestimoValidator criarEmprestimoValidator, CriarEmprestimo criarEmprestimo) {
    this.criarEmprestimoValidator = criarEmprestimoValidator;
    this.criarEmprestimo = criarEmprestimo;
  }

  @InitBinder
  public void init(WebDataBinder binder) {
    binder.addValidators(criarEmprestimoValidator);
  }

  @PostMapping(value = "/livros/{isbn}/emprestar")
  public ResponseEntity<EmprestimoResponse> criarEmprestimo(@PathVariable("isbn") String isbn,
      @RequestBody @Valid CriarEmprestimoRequest criarEmprestimoRequest) { //1
    return ResponseEntity.ok(EmprestimoResponse.of(
            criarEmprestimo.execute(isbn, criarEmprestimoRequest)
        )
    );
  }
}
