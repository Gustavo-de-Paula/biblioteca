package br.com.gustavodepaula.biblioteca.repository;

import br.com.gustavodepaula.biblioteca.model.Livro;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class LivroRepositoryTest {
    @Autowired
    private LivroRepository repository;
    @Autowired
    private EntityManager entityManager;

    @Test
    public void carregaUmLivroAoBuscarPeloId() {
        Long id = 1L;
        Livro livro = new Livro();
        entityManager.persist(livro);

        Assertions.assertNotNull(livro);
        Assertions.assertEquals(id, livro.getId());
    }
}