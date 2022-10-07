package br.com.gustavodepaula.biblioteca.repository;

import br.com.gustavodepaula.biblioteca.model.Emprestimo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
    Page<Emprestimo> findByUsuario_Id(Long usuarioId, Pageable pageable);
    Page<Emprestimo> findByUsuario_Nome(String usuarioNome, Pageable pageable);
}
