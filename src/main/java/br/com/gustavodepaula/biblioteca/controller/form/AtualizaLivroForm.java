package br.com.gustavodepaula.biblioteca.controller.form;

import br.com.gustavodepaula.biblioteca.model.Livro;

import javax.validation.constraints.NotBlank;

public class AtualizaLivroForm {
    @NotBlank
    private String nome;

    public Livro atualizar(Livro livro){
        livro.setNome(this.nome.toUpperCase());
        return livro;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
}
