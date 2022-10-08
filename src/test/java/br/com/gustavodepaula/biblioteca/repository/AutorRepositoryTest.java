package br.com.gustavodepaula.biblioteca.repository;

import br.com.gustavodepaula.biblioteca.model.Autor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class AutorRepositoryTest {
    @Autowired
    private AutorRepository repository;
    @Test
    public void CarregaUmAutorAoBuscarPeloNome() {
        String nomeAutor = "WILLIAM GIBSON";
        Autor autor =  repository.findByNome(nomeAutor).get();
        Assertions.assertNotNull(autor);
        Assertions.assertEquals(nomeAutor, autor.getNome());
    }

    @Test
    public void naoCarregaAutorCasoNomeNaoEstejaNaDatabase() {
        String nomeAutor = "H P LOVECRAFT";
        Autor autor =  repository.findByNome(nomeAutor).orElse(null);
        Assertions.assertNull(autor);
    }
}