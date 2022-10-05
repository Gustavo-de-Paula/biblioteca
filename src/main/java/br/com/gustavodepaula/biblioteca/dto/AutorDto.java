package br.com.gustavodepaula.biblioteca.dto;

import br.com.gustavodepaula.biblioteca.model.Autor;
import br.com.gustavodepaula.biblioteca.model.Livro;

import java.util.List;
import java.util.stream.Collectors;

public class AutorDto {
    private Long id;
    private String nome;

    public AutorDto(Autor autor) {
        this.id = autor.getId();
        this.nome = autor.getNome();
    }

    public static List<AutorDto> converter(List<Autor> autores){
        return autores.stream().map(AutorDto::new).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
}
