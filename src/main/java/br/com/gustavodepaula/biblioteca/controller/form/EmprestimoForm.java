package br.com.gustavodepaula.biblioteca.controller.form;

import br.com.gustavodepaula.biblioteca.model.Emprestimo;
import br.com.gustavodepaula.biblioteca.model.Usuario;
import br.com.gustavodepaula.biblioteca.repository.UsuarioRepository;

public class EmprestimoForm {
    private Long idUsuario;

    public Emprestimo converter(UsuarioRepository repository) {
        Usuario usuario = repository.getReferenceById(idUsuario);
        return new Emprestimo(usuario);
    }

    public Long getIdUsuario() {
        return idUsuario;
    }
}
