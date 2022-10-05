package br.com.gustavodepaula.biblioteca.repository;

import br.com.gustavodepaula.biblioteca.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {
}
