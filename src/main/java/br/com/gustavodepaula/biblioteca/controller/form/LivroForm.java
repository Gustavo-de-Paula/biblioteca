package br.com.gustavodepaula.biblioteca.controller.form;

import br.com.gustavodepaula.biblioteca.model.Autor;
import br.com.gustavodepaula.biblioteca.model.Livro;
import br.com.gustavodepaula.biblioteca.repository.AutorRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class LivroForm {
    @NotBlank @Length(min = 1)
    private String nome;
    @NotBlank @Length(min = 10)
    private String nomeAutor;

    public Livro converter(AutorRepository repository) {
        Autor autor = repository.findByNome(nomeAutor.toUpperCase());
        return new Livro(autor, nome.toUpperCase());
    }

    public String getNome() {
        return nome;
    }
    public String getNomeAutor() {
        return nomeAutor;
    }
}
