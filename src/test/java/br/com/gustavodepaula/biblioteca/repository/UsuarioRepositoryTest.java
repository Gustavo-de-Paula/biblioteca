package br.com.gustavodepaula.biblioteca.repository;

import br.com.gustavodepaula.biblioteca.model.Usuario;
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
class UsuarioRepositoryTest {
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void CarregaUmUsuarioAoBuscarPeloNome() {
        String nomeUsuario = "james bond";
        Usuario usuario = new Usuario();
        usuario.setNome(nomeUsuario.toUpperCase());
        entityManager.persist(usuario);

        Assertions.assertNotNull(usuario);
        Assertions.assertEquals(nomeUsuario.toUpperCase(), usuario.getNome());
    }

    @Test
    public void naoCarregaUsuarioCasoNomeNaoEstejaNaDatabase() {
        String nomeUsuario = "james bond";
        Usuario usuario = repository.findByNome(nomeUsuario.toUpperCase()).orElse(null);
        Assertions.assertNull(usuario);
    }
}