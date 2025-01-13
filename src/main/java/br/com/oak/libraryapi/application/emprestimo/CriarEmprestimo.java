package br.com.oak.libraryapi.application.emprestimo;

import static br.com.oak.libraryapi.Constantes.MAXIMO_TEMPO_EMPRESTIMO_EM_DIAS;

import br.com.oak.libraryapi.domain.Emprestimo;
import br.com.oak.libraryapi.infrastructure.repository.EmprestimoRepository;
import br.com.oak.libraryapi.infrastructure.repository.LivroRepository;
import br.com.oak.libraryapi.infrastructure.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class CriarEmprestimo {

  private final EmprestimoRepository emprestimoRepository;
  private final LivroRepository livroRepository;
  private final UsuarioRepository usuarioRepository;

  public CriarEmprestimo(EmprestimoRepository emprestimoRepository,
      LivroRepository livroRepository,
      UsuarioRepository usuarioRepository) {
    this.emprestimoRepository = emprestimoRepository;
    this.livroRepository = livroRepository;
    this.usuarioRepository = usuarioRepository;
  }

  @Transactional
  public Emprestimo execute(String isbn, @Valid EmprestimoInput input) {

    var livroOptional = livroRepository.findByIsbn(isbn);
    Assert.state(livroOptional.isPresent(),"O livro precisa existir para criar um emprestimo");

    var usuarioOptional = usuarioRepository.findById(input.usuarioId());
    Assert.state(usuarioOptional.isPresent(),"O usuario precisa existir para criar um emprestimo");

    Assert.state(usuarioOptional.get().prazoEntregaValidoParaUsuario(input.prazoEntregaEmDias()),
        "O usuario está tentando criar emprestimo com tempo inválido");

    var livro = livroOptional.get();
    var usuario = usuarioOptional.get();

    var exemplarOptional = livro.buscaExemplarDisponivel(usuario);
    Assert.state(exemplarOptional.isPresent(),
        "A busca pelo exemplar disponível do livro deveria retornar algum item");

    var exemplar = exemplarOptional.get();
    var tempoEmprestimoEmDias  = Optional.ofNullable(input.prazoEntregaEmDias()).orElse(
        MAXIMO_TEMPO_EMPRESTIMO_EM_DIAS);

    var emprestimo = exemplar.criarEmprestimo(usuario, tempoEmprestimoEmDias);
    return emprestimoRepository.save(emprestimo);
  }
}
