package br.com.gustavodepaula.biblioteca.dto;

import br.com.gustavodepaula.biblioteca.model.Livro;

public class LivroDto {
    private Long id;
    private String nome;
    private String genero;

    public LivroDto(Livro livro){
        this.id = livro.getId();
        this.nome = livro.getNome();
        this.genero = livro.getGenero().toString();
    }

    public Long getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public String getGenero() {
        return genero;
    }
}
