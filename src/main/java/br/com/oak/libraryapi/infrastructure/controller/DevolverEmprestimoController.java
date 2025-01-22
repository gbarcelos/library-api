package br.com.oak.libraryapi.infrastructure.controller;

import br.com.oak.libraryapi.application.emprestimo.DevolverEmprestimo;
import br.com.oak.libraryapi.infrastructure.controller.request.DevolverEmprestimoRequest;
import br.com.oak.libraryapi.infrastructure.validator.DevolverEmprestimoValidator;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DevolverEmprestimoController {

  private final DevolverEmprestimo devolverEmprestimo; //1
  private final DevolverEmprestimoValidator devolverEmprestimoValidator;

  public DevolverEmprestimoController(DevolverEmprestimo devolverEmprestimo,
      DevolverEmprestimoValidator devolverEmprestimoValidator){
    this.devolverEmprestimo = devolverEmprestimo;
    this.devolverEmprestimoValidator = devolverEmprestimoValidator;
  }

  @InitBinder
  public void init(WebDataBinder binder) {
    binder.addValidators(devolverEmprestimoValidator);
  }

  @PostMapping(value = "/livros/devolver")
  public ResponseEntity<?> devolverEmprestimo(@RequestBody @Valid DevolverEmprestimoRequest devolverEmprestimoRequest) { //1
    devolverEmprestimo.execute(devolverEmprestimoRequest);
    return ResponseEntity.ok().build();
  }
}
