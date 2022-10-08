package br.com.gustavodepaula.biblioteca.repository;

import br.com.gustavodepaula.biblioteca.model.Livro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    Page<Livro> findByNome(String nome, Pageable pageable);
    Page<Livro> findByAutor_Nome(String autor, Pageable pageable);
}
