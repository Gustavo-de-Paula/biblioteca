package br.com.gustavodepaula.biblioteca.controller.form;

import br.com.gustavodepaula.biblioteca.model.Emprestimo;
import br.com.gustavodepaula.biblioteca.model.Livro;
import br.com.gustavodepaula.biblioteca.repository.EmprestimoRepository;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AtualizaLivroForm {
    @NotBlank
    private String nome;
    @NotNull
    private Long emprestimoId;

    public Livro atualizar(Livro livro, EmprestimoRepository repository){
        Emprestimo emprestimo = repository.getReferenceById(emprestimoId);

        livro.setNome(this.nome.toUpperCase());
        livro.setEmprestimo(emprestimo);

        return livro;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Long getEmprestimoId() {
        return emprestimoId;
    }
    public void setEmprestimoId(Long emprestimoId) {
        this.emprestimoId = emprestimoId;
    }
}
