package br.com.gustavodepaula.biblioteca.dto;

import br.com.gustavodepaula.biblioteca.model.Autor;
import org.springframework.data.domain.Page;

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

    public static Page<AutorDto> converter(Page<Autor> autores){
        return autores.map(AutorDto::new);
    }

    public Long getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
}
