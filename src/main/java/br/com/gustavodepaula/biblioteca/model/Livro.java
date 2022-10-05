package br.com.gustavodepaula.biblioteca.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Livro {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Autor autor;
    @ManyToOne
    private Emprestimo emprestimo;

    private String nome;

    public Livro() {
    }
    public Livro(Autor autor, String nome) {
        this.autor = autor;
        this.nome = nome;
        this.emprestimo = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Livro livro = (Livro) o;
        return Objects.equals(id, livro.id)
                && Objects.equals(autor, livro.autor)
                && Objects.equals(emprestimo, livro.emprestimo)
                && Objects.equals(nome, livro.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, autor, emprestimo, nome);
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
    public Emprestimo getEmprestimo() {
        return emprestimo;
    }
    public void setEmprestimo(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
    }
}
