package br.com.gustavodepaula.biblioteca.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Livro {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Genero genero;

    private String nome;
    private Autor autor;
    private Emprestimo emprestimo;

    public Livro() {
    }
    public Livro(Genero genero, String nome, Autor autor) {
        this.genero = genero;
        this.nome = nome;
        this.autor = autor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Livro livro = (Livro) o;
        return Objects.equals(id, livro.id)
                && genero == livro.genero
                && Objects.equals(nome, livro.nome)
                && Objects.equals(autor, livro.autor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, genero, nome, autor);
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Genero getGenero() {
        return genero;
    }
    public void setGenero(Genero genero) {
        this.genero = genero;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Autor getAutor() {
        return autor;
    }
    public void setAutor(Autor autor) {
        this.autor = autor;
    }
}
