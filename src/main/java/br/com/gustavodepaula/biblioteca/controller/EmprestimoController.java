package br.com.gustavodepaula.biblioteca.controller;

import br.com.gustavodepaula.biblioteca.dto.EmprestimoDto;
import br.com.gustavodepaula.biblioteca.model.Emprestimo;
import br.com.gustavodepaula.biblioteca.repository.EmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {
    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @GetMapping
    public List<EmprestimoDto> listarEmprestimos() {
        List<Emprestimo> emprestimos = emprestimoRepository.findAll();
        return EmprestimoDto.converter(emprestimos);
    }

    @GetMapping("/{id}")
    public EmprestimoDto buscarPorId(@PathVariable Long id) {
        Emprestimo emprestimo = emprestimoRepository.getReferenceById(id);
        return new EmprestimoDto(emprestimo);
    }

    @GetMapping("/usuario/id/{usuarioId}")
    public List<EmprestimoDto> buscarPorIdUsuario(@PathVariable Long usuarioId) {
        List<Emprestimo> emprestimos = emprestimoRepository.findByUsuario_Id(usuarioId);
        return EmprestimoDto.converter(emprestimos);
    }

    @GetMapping("/usuario/nome/{usuarioNome}")
    public List<EmprestimoDto> buscarPorNomeUsuario(@PathVariable String usuarioNome) {
        List<Emprestimo> emprestimos = emprestimoRepository.findByUsuario_Nome(usuarioNome.toUpperCase());
        return EmprestimoDto.converter(emprestimos);
    }
}
