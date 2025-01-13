package br.com.oak.libraryapi.infrastructure.repository;

import br.com.oak.libraryapi.domain.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
}
