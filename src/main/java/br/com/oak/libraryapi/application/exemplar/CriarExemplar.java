package br.com.oak.libraryapi.application.exemplar;

import br.com.oak.libraryapi.domain.Exemplar;
import br.com.oak.libraryapi.domain.Livro;
import br.com.oak.libraryapi.infrastructure.repository.ExemplarRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class CriarExemplar {

  private final ExemplarRepository exemplarRepository;

  public CriarExemplar(ExemplarRepository exemplarRepository) {
    this.exemplarRepository = exemplarRepository;
  }

  @Transactional
  public Exemplar execute(Livro livro, @Valid ExemplarInput input) {
    return exemplarRepository.save(input.toModel(livro));
  }
}
