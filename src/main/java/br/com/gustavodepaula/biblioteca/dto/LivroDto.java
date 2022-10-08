package br.com.gustavodepaula.biblioteca.dto;

import br.com.gustavodepaula.biblioteca.model.Livro;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class LivroDto {
    private Long id;
    private String nome;
    private String autor;

    public LivroDto(Livro livro){
        this.id = livro.getId();
        this.nome = livro.getNome();
        this.autor = livro.getAutor().getNome();
    }

    public static List<LivroDto> converter(List<Livro> livros){
        return livros.stream().map(LivroDto::new).collect(Collectors.toList());
    }

    public static Page<LivroDto> converter(Page<Livro> livros){
        return livros.map(LivroDto::new);
    }

    public Long getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public String getAutor() {return autor;}
}
