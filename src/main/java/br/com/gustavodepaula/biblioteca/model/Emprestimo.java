package br.com.gustavodepaula.biblioteca.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
public class Emprestimo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private Usuario usuario;
    private List<Livro> livros;

    public Emprestimo() {
    }
    public Emprestimo(LocalDate dataDevolucao, Usuario usuario) {
        this.dataEmprestimo = LocalDate.now();
        this.dataDevolucao = dataDevolucao;
        this.usuario = usuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Emprestimo that = (Emprestimo) o;
        return Objects.equals(id, that.id)
                && Objects.equals(dataEmprestimo, that.dataEmprestimo)
                && Objects.equals(dataDevolucao, that.dataDevolucao)
                && Objects.equals(usuario, that.usuario)
                && Objects.equals(livros, that.livros);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dataEmprestimo, dataDevolucao, usuario, livros);
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }
    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }
    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }
    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public List<Livro> getLivros() {
        return livros;
    }
    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }
}
