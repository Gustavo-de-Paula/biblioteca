package br.com.gustavodepaula.biblioteca.controller.form;

import br.com.gustavodepaula.biblioteca.model.Emprestimo;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AtualizacaoEmprestimoForm {
    @NotNull
    private LocalDate dataDevolucao;

    public Emprestimo atualizar(Emprestimo emprestimo){
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
