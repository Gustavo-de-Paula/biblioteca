package br.com.gustavodepaula.biblioteca.controller.form;

import br.com.gustavodepaula.biblioteca.model.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class UsuarioForm {
    @NotBlank @Length(min = 1)
    private String nome;

    public Usuario converter() {
        return new Usuario(nome.toUpperCase());
    }

    public String getNome() {
        return nome;
    }
}
