package br.com.gustavodepaula.biblioteca.dto;

import br.com.gustavodepaula.biblioteca.model.Emprestimo;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class EmprestimoDto {
    private Long id;
    private Long idUsuario;
    private String nomeUsuario;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    public EmprestimoDto() {
    }
    public EmprestimoDto(Emprestimo emprestimo) {
        this.id = emprestimo.getId();
        this.idUsuario = emprestimo.getUsuario().getId();
        this.nomeUsuario = emprestimo.getUsuario().getNome();
        this.dataEmprestimo = emprestimo.getDataEmprestimo();
        this.dataDevolucao = emprestimo.getDataDevolucao();
    }

    public static List<EmprestimoDto> converter(List<Emprestimo> emprestimos){
        return emprestimos.stream().map(EmprestimoDto::new).collect(Collectors.toList());
    }

    public static Page<EmprestimoDto> converter(Page<Emprestimo> emprestimos){
        return emprestimos.map(EmprestimoDto::new);
    }

    public Long getId() {
        return id;
    }
    public Long getIdUsuario() {
        return idUsuario;
    }
    public String getNomeUsuario() {
        return nomeUsuario;
    }
    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }
    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }
}
