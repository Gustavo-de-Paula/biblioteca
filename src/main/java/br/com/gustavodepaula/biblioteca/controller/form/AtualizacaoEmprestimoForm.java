package br.com.gustavodepaula.biblioteca.controller.form;

import br.com.gustavodepaula.biblioteca.model.Emprestimo;
import br.com.gustavodepaula.biblioteca.repository.EmprestimoRepository;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AtualizacaoEmprestimoForm {
    @NotNull
    private LocalDate dataDevolucao;

    public Emprestimo atualizar(Long id, EmprestimoRepository repository){
        Emprestimo emprestimo = repository.getReferenceById(id);
        emprestimo.setDataDevolucao(this.dataDevolucao);
        return emprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }
    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
}
