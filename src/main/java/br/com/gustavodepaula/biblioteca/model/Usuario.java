package br.com.gustavodepaula.biblioteca.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;
import java.util.Objects;

@Entity
public class Usuario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private List<Emprestimo> emprestimos;

    public Usuario() {
    }
    public Usuario(String nome, String email, String telefone) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id)
                && Objects.equals(nome, usuario.nome)
                && Objects.equals(emprestimos, usuario.emprestimos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, emprestimos);
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
    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }
    public void setEmprestimos(List<Emprestimo> emprestimos) {
        this.emprestimos = emprestimos;
    }
}
