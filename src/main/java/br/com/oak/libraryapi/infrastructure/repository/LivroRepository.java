package br.com.oak.libraryapi.infrastructure.repository;

import br.com.oak.libraryapi.domain.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {

}
