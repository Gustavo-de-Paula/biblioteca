package br.com.gustavodepaula.biblioteca.repository;

import br.com.gustavodepaula.biblioteca.model.Autor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class AutorRepositoryTest {
    @Autowired
    private AutorRepository repository;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void CarregaUmAutorAoBuscarPeloNome() {
        String nomeAutor = "william gibson";
        Autor autor =  new Autor();
        autor.setNome(nomeAutor.toUpperCase());
        entityManager.persist(autor);

        Assertions.assertNotNull(autor);
        Assertions.assertEquals(nomeAutor.toUpperCase(), autor.getNome());
    }

    @Test
    public void naoCarregaAutorCasoNomeNaoEstejaNaDatabase() {
        String nomeAutor = "william gibson";
        Autor autor =  repository.findByNome(nomeAutor.toUpperCase()).orElse(null);
        Assertions.assertNull(autor);
    }
}