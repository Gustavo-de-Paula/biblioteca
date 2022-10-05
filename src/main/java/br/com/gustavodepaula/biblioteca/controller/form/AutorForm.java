package br.com.gustavodepaula.biblioteca.controller.form;

import br.com.gustavodepaula.biblioteca.model.Autor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class AutorForm {
    @NotBlank @Length(min = 10)
    private String nome;

    public Autor converter() {
        return new Autor(nome.toUpperCase());
    }

    public String getNome() {
        return nome;
    }
}
