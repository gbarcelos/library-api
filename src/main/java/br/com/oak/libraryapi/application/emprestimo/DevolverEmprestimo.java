package br.com.oak.libraryapi.application.emprestimo;

import br.com.oak.libraryapi.infrastructure.repository.EmprestimoRepository;
import br.com.oak.libraryapi.infrastructure.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class DevolverEmprestimo {

  private final EmprestimoRepository emprestimoRepository;
  private final UsuarioRepository usuarioRepository;

  public DevolverEmprestimo(EmprestimoRepository emprestimoRepository,
      UsuarioRepository usuarioRepository){
    this.emprestimoRepository = emprestimoRepository;
    this.usuarioRepository = usuarioRepository;
  }

  @Transactional
  public void execute(@Valid DevolverEmprestimoInput input) {

    var usuarioOptional = usuarioRepository.findById(input.usuarioId());
    Assert.state(usuarioOptional.isPresent(),"O usuario precisa existir para devolver um emprestimo");

    var emprestimoOptional = emprestimoRepository.findById(input.emprestimoId());
    Assert.state(emprestimoOptional.isPresent(),"O empréstimo precisa existir para ser devolvido");

    var emprestimo = emprestimoOptional.get();
    Assert.state(emprestimo.podeSerDevolvido(usuarioOptional.get()),"O empréstimo nao pode ser devolvido");

    emprestimo.devolver();
  }
}
