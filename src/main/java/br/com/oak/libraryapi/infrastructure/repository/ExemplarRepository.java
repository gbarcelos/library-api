package br.com.oak.libraryapi.infrastructure.repository;

import br.com.oak.libraryapi.domain.Exemplar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExemplarRepository extends JpaRepository<Exemplar, Long> {
}
