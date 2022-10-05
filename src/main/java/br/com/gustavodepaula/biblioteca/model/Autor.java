package br.com.gustavodepaula.biblioteca.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Autor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "autor")
    private List<Livro> livros;

    private String nome;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Autor autor = (Autor) o;
        return Objects.equals(id, autor.id)
                && Objects.equals(nome, autor.nome)
                && Objects.equals(livros, autor.livros);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, livros);
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
    public List<Livro> getLivros() {
        return livros;
    }
    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }
}
